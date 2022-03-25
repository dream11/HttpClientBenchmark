package bench

import java.util.concurrent.{Executors, TimeUnit}
import cats.implicits._
import org.http4s.{Method, Uri}

case object Http4sBenchmark extends BaseBenchmark {

  import cats.effect._
  import org.http4s.client._
  import org.http4s.Request

  def run[F[_]: cats.effect.Async: ContextShift](n: Int): F[Unit] = {
    import scala.concurrent.ExecutionContext

    val gurl = s"http://$rootContext/get"
    val getRequest = Request[F](Method.GET,Uri.unsafeFromString(gurl))
    val purl = s"http://$rootContext/post"
    val postRequest = Request[F](Method.POST,Uri.unsafeFromString(purl))

    val blockingEC = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(5))
    val httpClient: Client[F] = JavaNetClientBuilder[F](blockingEC).create

    for {
      //    _ <- ZIO(println(s"STTP CLIENT: Warming up the server with 2000 requests $grequest"))
      _  <- httpClient.status(getRequest).replicateA(2000)

      startTime <- Async[F].delay(System.nanoTime())
      _  <- httpClient.status(getRequest).replicateA(n)
      duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)
      _        <- Async[F].delay( println(s"\nHttp4s : GET $n requests --- ${(n * 1000 / duration)} requests/sec "))

      startTime <- Async[F].delay(System.nanoTime())
      _  <- httpClient.status(postRequest).replicateA(n)
      duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)
      _        <- Async[F].delay( println(s"Http4s : POST $n requests --- ${(n * 1000 / duration)} requests/sec "))

    } yield ()
  }
}
