package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailures extends App {
    val aSuccess = Success(3)
    val aFailure = Failure(new RuntimeException("failure failure"))
    println(aSuccess)
    println(aFailure)

    def unsafeMethod: String = throw new RuntimeException("No String")
    // use Try class
    val potentialFailure = Try(unsafeMethod)
    println(potentialFailure)

    // syntax sugar
    val anotherPotentialFailure = Try {
        // code that might throw
    }

    // utilities
    println(potentialFailure.isSuccess)
    println(potentialFailure.orElse(Try("A valid value")))

    // if you design the API
    def betterUnsafeMethod: Try[String] = Failure(new RuntimeException)
    def betterBackupMethod: Try[String] = Success("A valid result")
    val betterFallBack = betterUnsafeMethod orElse betterBackupMethod
    println(betterFallBack)

    // map, flatMap, filter
    println(aSuccess.map(_ * 2)) // prints Success(6)
    println(aSuccess.flatMap(x => Success(x * 10)))
    println(aSuccess.filter(x => x > 9))

    // exercise
    val hostname = "localhost"
    val port = "8080"
    def renderHTML(page: String) = println(page)

    class Connection {
        def get(url: String): String = {
            val random = new Random(System.nanoTime())
            if (random.nextBoolean()) "<html>...</html>"
            else throw new RuntimeException("connection interrupted")
        }
    }

    object HttpService {
        val random = new Random(System.nanoTime())

        def getConnection(host: String, port: String): Connection = {
            if (random.nextBoolean()) new Connection
            else throw new RuntimeException("port not available")
        }
    }

    // if you get the html page from connection, print it on console
    println("chained methods")
    Try(HttpService.getConnection(hostname, port))
      .flatMap(c => Try(c.get("url")))
      .foreach(renderHTML)

    println("for comprehension")
    for {
        connection <- Try(HttpService.getConnection(hostname, port))
        page <- Try(connection.get("url"))
    } renderHTML(page)
}
