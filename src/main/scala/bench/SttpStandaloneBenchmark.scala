package bench

import sttp.client3.HttpURLConnectionBackend

object SttpStandaloneBenchmark extends scala.App {
  import org.asynchttpclient.{AsyncHttpClientConfig, DefaultAsyncHttpClientConfig}
  import sttp.client3.{UriContext, basicRequest}
//  import sttp.client3.quick._

  val rootContext = scala.util.Properties.envOrElse("ROOT_CONTEXT", "localhost:7777")
  val N = scala.util.Properties.envOrElse("NUM_REQUESTS", "3000").toInt

  val config: AsyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder().build()
  val grequest                      = basicRequest.get(uri"http://$rootContext/get")
  val prequest                      = basicRequest.get(uri"http://$rootContext/post").body("Sample content")

  implicit val backend = HttpURLConnectionBackend()

  1 to N foreach   { _ => grequest.send(backend).statusText }

  val startTimeGet = System.nanoTime()
  //  val statusGet = grequest.send(backend).statusText
  1 to N foreach  { _ => grequest.send(backend).statusText}
  val durationGet = System.nanoTime() - startTimeGet

  println(s"\nSTTP : GET $N requests --- ${(N * 1000 / durationGet)} requests/sec ")

  val startTimePost = System.nanoTime()
  1 to N foreach { _ => prequest.send(backend).statusText }
  val durationPost = System.nanoTime() - startTimeGet

  println(s"\nSTTP : POST $N requests --- ${(N * 1000 / durationPost)} requests/sec ")

}