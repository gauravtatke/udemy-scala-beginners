package lectures.part3fp

object HOFsCurries extends App {
    // a super complex function defined
    val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

    // map, flatMap, filter in MyList are HOFs

    // function that applies a function n times over to a value x
    // nTimes(f, n, x)
    // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
    // nTimes(f, n, x) = nTimes(f, n-1, f(x))
    def nTimes(f: Int => Int, n: Int, x: Int): Int =
        if (n <= 0) x
        else nTimes(f, n-1, f(x))

    val plusOne = (x: Int) => x + 1
    println(nTimes(plusOne, 10, 1))

    // instead of return the value we can return the function which is
    // application of f on any value x n times
    def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
        if (n <= 0) (x: Int) => x // return identity function, returns the same value
        else (x: Int) => nTimesBetter(f, n-1)(f(x))

    val plus10 = nTimesBetter(plusOne, 10) // returns a function that applies plusOne 10 times to whichever value is passes
    println(plus10(1)) // return 11

    // curried functions
    val superAdder = (x: Int) => (y: Int) => x + y
    val add3 = superAdder(3) // y => y + 3
    println(add3(10))
    println(superAdder(3)(10))

    // functions with multiple parameter lists acting as a curried functions
    def curriedFormatter(c: String)(x: Double): String = c.format(x)

    val standardFormat: Double => String = curriedFormatter("%4.2f") // return type in such cases are mandatory
    val preciseFormat: Double => String = curriedFormatter("%10.8f") // otherwise jvm wont know what does format method return

    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))

    // exercise
    def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = x => y => f(x, y) // takes f(x, y) => z and return curried f(x)(y) => z
    def superAdder2: (Int => Int => Int) = toCurry((x, y) => x + y)
    val add4 = superAdder2(4)
    println(add4(17)) // prints 21

    def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = (x, y) => f(x)(y)
    def simpleAdder = fromCurry(superAdder)
    println(simpleAdder(4, 17))

    // compose(f, g) => x => f(g(x))
    // andThen(f, g) => x => g(f(x))
    def compose[A, B, C](f: A => B, g: C => A): C => B = x => f(g(x))
    def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

    val add2 = (x: Int) => x + 2
    val times3 = (x: Int) => x * 3

    val composed = compose(add2, times3)
    println(composed(4))

    val ordered = andThen(add2, times3)
    println(ordered(4))
}
