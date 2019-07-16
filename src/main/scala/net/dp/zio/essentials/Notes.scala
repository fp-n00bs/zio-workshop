package net.dp.zio.essentials
import net.dp.zio.essentials.zio_operations._
import scalaz.zio._

object Notes extends DefaultRuntime {

  def main(args: Array[String]): Unit = {

    //zio_operations ------------------------------------------------------
    unsafeRun(
      IO.succeed(42).map(_.toString)
    ).ensuring(res => res == "42")

    unsafeRun(
      IO.fail(42)
        .mapError(_.toString)
        .fold(
          x => x,
          y => y
        )
    ).ensuring(res => res == "42")

    unsafeRun(
      for {
        i1 <- IO.succeed(14)
        i2 <- IO.succeed(16)
      } yield (i1 + i2)
    ).ensuring(res => res == 30)

    unsafeRun(factorialIO(4))
      .ensuring(res => res == 24)

    unsafeRun(factorialTailIO(5))
      .ensuring(res => res == 120)

    unsafeRun(
      UIO.succeed(2).zipWith(UIO.succeed(40))(_ + _)
    ).ensuring(res => res == 42)

    unsafeRun(
      ZIO.foreach(List(1, 2, 3))(x => UIO(x * 2))
    ).ensuring(res => res == List(2, 4, 6))

    unsafeRun(
      ZIO.collectAll(List(UIO("1"), UIO("2"), UIO("3")))
    ).ensuring(res => res == List("1", "2", "3"))

    //unsafeRun(playGame2)
  }

}
