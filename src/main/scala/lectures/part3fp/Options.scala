package lectures.part3fp

import scala.util.Random

object Options extends App {

    val myFirstOption: Option[Int] = Some(4)
    val noOption: Option[Int] = None
    println(myFirstOption)

    // unsafe APIs
    def unsafeMethod: String = null
//    val result = Some(unsafeMethod) // WRONG - could potentially be Some(null) which defeats the purpose
    val correctResult = Option(unsafeMethod) // correct. Option companion objects take care of having correct value or None
    println(correctResult)

    // chained methods
    def backupMethod: String = "A valid value"
    def chainedResult = Option(unsafeMethod).orElse(Option(backupMethod))

    // DESIGN safe APIs
    def betterUnsafeMethod: Option[String] = None
    def betterBackupMethod: Option[String] = Some("A valid result")
    val betterChainedResult = betterUnsafeMethod orElse betterBackupMethod
    println(betterChainedResult)

    // functions on Options
    println(myFirstOption.isEmpty)
    println(myFirstOption.get) // unsafe. if None, throws nullpointer. do not use
    // map, flatMap, filter
    println(myFirstOption.map(_ * 2)) // prints Some(8)
    println(myFirstOption.filter(x => x > 10)) // prints None bcuz 4 > 10 is false
    println(myFirstOption.flatMap(x => Option(x * 10))) // prints Some(40)

    // exercise - given below class and objects, print connection if established otherwise not
    val config = Map[String, String]("host" -> "172.34.56.191", "port" -> "80") // values could be null
    class Connection {
        def connect: String = "Connected"
    }

    object Connection {
        def apply(host: String, port: String): Option[Connection] = {
            val random = new Random(System.nanoTime())
            if (random.nextBoolean()) Some(new Connection)
            else None
        }
    }

    val host = config.get("host")
    val port = config.get("port")
    val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
    val connectionStatus = connection.map(c => c.connect)
    println(connectionStatus)
    connectionStatus.foreach(println)

    // chained
    config.get("host")
      .flatMap(h => config.get("port")
        .flatMap(p => Connection(h, p)))
      .map(c => c.connect)
      .foreach(println)

    // for-comprehension
    val forConnectStatus = for {
        host <- config.get("host") // given host is available
        port <- config.get("port") // given port is available
        connection <- Connection(host, port) // given connection happens
    } yield connection.connect // then yield the connection string else None
    forConnectStatus.foreach(println)
}
