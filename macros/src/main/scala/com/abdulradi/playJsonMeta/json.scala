package com.abdulradi.playJsonMeta

import scala.meta._

object json {
  inline def apply()(defn: Any) = meta {
    val q"..$mods object $name extends { ..$early } with ..$base { $self => ..$stats }" = defn
    val statsWithFmts = stats.zipWithIndex.flatMap {
      case (q"class $tname[..$tparams] (...$paramss) extends $template", i) =>
        val term = Pat.Var.Term(Term.Name(s"fmt$i"))
        Seq(
          q"case class $tname[..$tparams] (...$paramss) extends $template",
          q"implicit val $term = play.api.libs.json.Json.format[$tname]"
        )
      case (other, _
        ) =>
        Seq(other)
    }
    q"..$mods object $name extends { ..$early } with ..$base { $self => ..$statsWithFmts }"
  }
}
