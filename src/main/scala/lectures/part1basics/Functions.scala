package lectures.part1basics

object Functions extends App {
    def aFunction(a: String, b: Int): String = a + " " + b
    println(aFunction("hello", 3))

    def aParameterlessFunction(): Int = 42
    println(aParameterlessFunction()) // with parenthesis
    println(aParameterlessFunction) // without parenthesis

    def aRecursiveFunc(aString: String, b: Int): String = {
        if (b == 1) aString
        else aString + aRecursiveFunc(aString, b-1)
    }

    println(aRecursiveFunc("hello", 3))

    // WHEN YOU NEED LOOPS, USE RECURSION
    def aBigFunction(n: Int): Int = {
        def aSmallerFunction(a: Int, b: Int) = a + b
        aSmallerFunction(n, n-1)
    }
    println(aBigFunction(10))
}
