package lectures.part3fp

object AnonymousFunctions extends App {
    val doubler1 = new Function[Int, Int] { // still a OO style bcuz we are instantiating a trait
        override def apply(elem: Int): Int = elem * 2
    }

    // anonymous function - LAMBDA
    val doubler2 = (x: Int) => x * 2
    val doubler3: Int => Int = x => x * 2

    println(doubler1(4))
    println(doubler2(4))

    val adder = (a: Int, b: Int) => a + b
    val justDoSomething: () => Int = () => 3 // no param but returns 3

    // careful about below
    println(justDoSomething) // prints the function
    println(justDoSomething()) // actually calls the function

    // curly braces with lambdas
    val stringToInt = { (str: String) =>
       str.toInt
    }

    // MOAR syntactic sugar - using _
    val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
    val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

    // exercise
    // rewrite curried function into an anonymous function
    val multiplier: Int => Int => Int = x => y => x * y
    println(multiplier(9)(7))

}
