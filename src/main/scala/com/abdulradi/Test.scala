import com.abdulradi.playJsonMeta._
import play.api.libs.json.Json

@json object Model {
  case class Person(id: String, name: String)
}

object Test extends App {
  import Model._

  println(Json.toJson(Person("123", "Tamer")))
}
