package com.abdulradi.playJsonMeta

import scala.meta._

object json {
  inline def apply()(defn: Any) = meta {
    val q"..$mods object $name extends { ..$early } with ..$base { $self => ..$stats }" = defn
    val statsWithFmts = stats.zipWithIndex.flatMap {
      case (q"class $tname[..$tparams] (...$paramss) extends $template", i) =>
        // val name = scala.meta.Term.Name(s"fmt$i")
        // val term = q"$name"
        Seq(
          q"case class $tname[..$tparams] (...$paramss) extends $template",
          q"implicit val _ = play.api.libs.json.Json.format[$tname]" // TODO: Generate random val name
        )
      case (other, _
        ) =>
        Seq(other)
    }
    q"..$mods object $name extends { ..$early } with ..$base { $self => ..$statsWithFmts }"
  }
}
