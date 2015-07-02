package cc.kevinlee.example.elastic4s

import com.sksamuel.elastic4s.ElasticClient
import com.sksamuel.elastic4s.ElasticDsl._

import scala.util.{Failure, Success}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-07-02
 */
object ElasticSearchExample extends App {

  val client = ElasticClient.local

  client.execute {
    index into "bands" / "artists" fields "name" -> "coldplay"
  }.onComplete {
    case Success(_) =>
      client.execute {
        search in "bands" / "artists" query "coldplay"
      }.onComplete {
        case Success(result) =>
          println(result)
        case Failure(error) =>
          println(error)
      }
    case Failure(error) =>
      println(error)
  }
}
