package lectures.part4patternmatching

object PatternEverywhere extends App {
    // big idea #1
    try {
        // some code
    } catch {
        case e: RuntimeException => ""
        case npe: NullPointerException => ""
        case _ => ""
    }
    // catch are actually MATCHES

    // big idea #2
    val list = List(1, 2, 3, 4)
    val evenOnes = for {
        x <- list if x % 2 == 0 // these kind of generators are also based on pattern matches
    } yield 10 * x

    val tupleList = List((1,2), (3,4))
    val filterTuples = for {
        (first, second) <- tupleList
    } yield first * second
    // case classes, :: operator etc use pattern matches

    // big idea #3
    val tuple = (1, 2, 3)
    val (a, b, c) = tuple // again using name binding property of pattern matching
    println(a)
    println(b)
    // ALL the POWER is AVAILABLE
    val head :: tail = list
    println(s"head $head")
    println(s"tail $tail")

    // big idea #4
    // partial functions are based on pattern matching
    val mappedList1 = list.map {
        case v if v % 2 == 0 => s"$v is even"
        case 1 => "THE ONE"
        case _ => "something else"
    } // partial function literal
    // equivalent to below
    val mappedList2 = list.map(x => x match {
        case v if v % 2 == 0 => s"$v is even"
        case 1 => "THE ONE"
        case _ => "something else"
    })
    println(mappedList1)
    println(mappedList2)
}
