package lectures.part3fp

object WhatsAFunction extends App {
    val doubler = new MyFunction[Int, Int] { // instance of function-like trait with apply method
        override def apply(element: Int): Int = element * 2
    }

    // doubler instance can be called bcuz it has apply method.
    println(doubler(2)) // prints 4

    // scala supports function-types out of the box
    // function types = Function1, Function2, Function3 ... Function22
    // Function1[A,B], Function[A,B,C] ... are function-like traits starting with 1 param and 1 return type to 22 param and 1 return type
    val stringToIntConverter = new Function1[String, Int] {
        override def apply(elem: String): Int = elem.toInt
    }
    println(stringToIntConverter("3") + 4) // prints 7

    val adder1: Function2[Int, Int, Int] = new Function2[Int, Int, Int] { // with complete return type
        override def apply(elem1: Int, elem2: Int): Int = elem1 + elem2
    }

    val adder2: ((Int, Int) => Int) = new Function2[Int, Int, Int] { // with return type syntactic sugar
        override def apply(elem1: Int, elem2: Int): Int = elem1 + elem2
    }

    // Function types Function2[A, B] === (A, B) => R (can written as)
    val adder3: (Int, Int) => Int = (elem1: Int, elem2: Int) => elem1 + elem2 // even more concise form with syntactic sugar
    println(adder1(2,3))
    println(adder2(2,3))
    println(adder3(2,3))

    // ALL SCALA FUNCTIONS ARE INSTANCE OF CLASSES THAT EXTEND THE FUNCTION TYPE TRAITS.
    // THIS IS HOW SCALA LAID THE FOUNDATION OF FUNCTIONAL PROGRAMMING IN JVM WHICH DID NOT HAVE FP CONSTRUCTS

    // Exercise
    val stringConcat: (String, String) => String = (str1: String, str2: String) => str1+str2
    println(stringConcat("hello", "gaurav"))
    // function that takes an int and return a function that takes int and return an int
    // this is type of higher-order function - that either returns a function or takes a function as param or both
    val multiplier: (Int => (Int => Int)) = new Function1[Int, Function1[Int, Int]] {
        override def apply(elem1: Int): Function1[Int, Int] = new Function1[Int, Int] {
            override def apply(elem2: Int): Int = elem1 * elem2
        }
    }
    val multiplier9 = multiplier(9)
    println(multiplier9(7)) // prints 9 * 7
    // or we can call like below
    println(multiplier(9)(7)) // called curried function
}

trait MyFunction[A, B] { // this is function-like trait
    def apply(element: A): B
}
