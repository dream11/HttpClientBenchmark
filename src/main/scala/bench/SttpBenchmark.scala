//package bench
//
//import sttp.client3.HttpURLConnectionBackend
//
//object SttpBenchmark extends scala.App {
//  import org.asynchttpclient.{AsyncHttpClientConfig, DefaultAsyncHttpClientConfig}
//  import sttp.client3.{UriContext, basicRequest}
////  import sttp.client3.quick._
//
//  val rootContext = scala.util.Properties.envOrElse("ROOT_CONTEXT", "localhost:7777")
//  val N = scala.util.Properties.envOrElse("NUM_REQUESTS", "3000").toInt
//
//  val config: AsyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder().build()
//  val grequest                      = basicRequest.get(uri"http://$rootContext/get")
//  val prequest                      = basicRequest.get(uri"http://$rootContext/post").body("Sample content")
//
//  implicit val backend = HttpURLConnectionBackend()
//
//  1 to N foreach   { _ => grequest.send(backend).statusText }
//
//  val startTimeGet = System.nanoTime()
//  //  val statusGet = grequest.send(backend).statusText
//  1 to N foreach  { _ => grequest.send(backend).statusText}
//  val durationGet = System.nanoTime() - startTimeGet
//
//  println(s"\nSTTP : GET $N requests --- ${(N * 1000 / durationGet)} requests/sec ")
//
//  val startTimePost = System.nanoTime()
//  1 to N foreach { _ => prequest.send(backend).statusText }
//  val durationPost = System.nanoTime() - startTimeGet
//
//  println(s"\nSTTP : POST $N requests --- ${(N * 1000 / durationPost)} requests/sec ")
//
////  run(3000)
////  def run(n: Int) = for {
////    //    _ <- ZIO(println(s"STTP CLIENT: Warming up the server with 2000 requests $grequest"))
////    _ <- backend.flatMap(backend => grequest.send(backend).map(_.statusText).repeatN(2000))
////
////    startTime <- ZIO(System.nanoTime())
////    _         <- backend.flatMap(backend => grequest.send(backend).map(_.statusText).repeatN(n))
////    duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)
////    _        = println(s"\nSTTP : GET $n requests --- ${(n * 1000 / duration)} requests/sec")
////
////    startTime <- ZIO(System.nanoTime())
////    _         <- backend.flatMap(backend => prequest.send(backend).map(_.statusText).repeatN(n))
////    duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)
////    _        = println(s"STTP : POST $n requests --- ${(n * 1000 / duration)} requests/sec \n")
////
////  } yield ()
//
//
//
//}
