package lectures.part3fp

object MapFlatmapFilterFor extends App {
    val list = List(1,2,3)
    println(list)
    println(list.head)
    println(list.tail)

    // map
    println(list.map(_ + 1)) // prints 2,3,4
    println(list.map(_ + " is a number"))

    //filter
    println(list.filter(_ % 2 == 0))

    // flatMap
    val toPair = (x: Int) => List(x, x * 2)
    println(list.flatMap(toPair))

    // print all combination of 2 lists
    val numbers = List(1,2,3,4)
    val chars = List('a', 'b', 'c', 'd')
    val combination = chars.flatMap(c => numbers.map(n => s"$c$n"))
    println(combination)

    // foreach
    list.foreach(println) // similar to map but receives function returning Unit

    // for-comprehensions - substitute for lot of maps, flatmaps in some scenarios
    val forCombinations = for {
        n <- numbers if n % 2 == 0 // if guard in comprehension, acting as filters
        c <- chars
    } yield s"$c-$n"
    println(forCombinations)

    for {n <- numbers} println(n)

    // syntax overload
    val doubleList = list.map { x =>
        x * 2
    }
    println(doubleList)

    // exercises
    // a small collection of most one item - MayBe[+T]
    abstract class MayBe[+T] {
        val value: T
        def map[T, X](f: T => X): List[X]
    } // in a separate file
}
