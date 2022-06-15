package lectures.part3fp

import scala.util.Random

object Sequences extends App {
    // Seq
    val aSequence = Seq(5,4,2,8,9)
    println(aSequence)
    println(aSequence.reverse)
    println(aSequence(2)) // apply methods defaults to Seq.get(2)
    println(aSequence ++ Seq(6, 7, 8))
    println(aSequence.sorted)

    // Ranges
    val aRange: Seq[Int] = 1 to 10 // 1 until 10 works too
    aRange.foreach(println)

    // lists
    val aList = List(1,2,3)
    val prepended = 43 :: aList
    println(prepended)
    val prependAppend = 23 +: aList :+ 34
    println(prependAppend)
    val apple5 = List.fill(5)("apple") // creates a list with 5 apples
    println(apple5)
    println(aList.mkString("-|-"))

    // Arrays (complete mapping to java native arrays)
    val numbers = Array(1,2,3,4)
    val threeElements = Array.ofDim[Int](3) // 3 elements of Int type
    println(threeElements) // prints obsure stuff
    threeElements.foreach(println) // print 0 0 0 that is array is initialized with default value of zero.

    // arrays are mutable
    numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
    println(numbers.mkString(", ")) // prints 1, 2, 0, 4

    // arrays and seq
    val numberSeq: Seq[Int] = numbers // implicit conversion of arrays to sequence
    println(numberSeq)

    // vectors - grow exponentially, have good read/write speed
    val vector: Vector[Int] = Vector(1,2,3)
    println(vector)

    // vector vs list
    val maxCapacity = 1000000
    val maxRuns = 1000
    def getWriteTime(collection: Seq[Int]): Double = {
        val r = new Random()
        val times = for {
            it <- 1 to maxRuns
        } yield {
            val currentTime = System.nanoTime()
            collection.updated(r.nextInt(maxCapacity), r.nextInt()) // updateds the collection at random index with random value
            System.nanoTime() - currentTime
        }
        times.sum * 1.0 / maxRuns
    }

    val numberLists = (1 to maxCapacity).toList
    val numberVector = (1 to maxCapacity).toVector
    // list keeps reference to tail so updating head is efficient
    // but update in middle is little slow
    println(getWriteTime(numberLists))
    // vector are branched trie, each branch contains 32 elements.
    // need to replace complete branch of 32 elements if element is updated
    println(getWriteTime(numberVector))
}
