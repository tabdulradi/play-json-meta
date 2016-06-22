import com.abdulradi.playJsonMeta._
import play.api.libs.json.Json

@json object Model {
  case class Id(underlying: String) extends AnyVal
  case class Person(id: Id, name: String)
}

object Test extends App {
  import Model._

  println(Json.toJson(Person(Id("123"), "Tamer")))
}
