package lectures.part1basics

object CBNvsCBV extends App {
    def calledByValue(x: Long) = {
        println("called by value: " + x)
        println("called by value: " + x)
    }

    def calledByName(x: => Long) = {
        println("called by name: " + x)
        println("called by name: " + x)
    }

    // prints the same nano time twice
    calledByValue(System.nanoTime())

    // prints 2 separate nano time as if x is replaced by function returning nano time
    calledByName(System.nanoTime()) // by name param delays the evaluation of x until it is used

    def infinite(): Int = 1 + infinite() // infinite recursion
    def printFirst(x: Int, y: => Int) = println(x)

    // below causes infinite recursion and crashes
//    printFirst(infinite(), 34)

    // below works fine because evaluation of y does not happen as it is not used
    printFirst(34, infinite())
}
