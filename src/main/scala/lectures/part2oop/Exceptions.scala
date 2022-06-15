package lectures.part2oop

object Exceptions extends App {
    val x: String = null
//    println(x.length)
//    this ^^ will crash the program

//    val aWeirdValue = throw new NullPointerException // a weird value
    // throwable classes extend the Throwable class.
    // Exception and Error are the 2 major throwable subtypes.
    // Exception are used when there is problem with program like nullpointer
    // Error is used when there is problem with system like stack overflow problem with jvm.

    // 2. catch exception
    def getInt(withException: Boolean): Int =
        if (withException) throw new RuntimeException("no int for you")
        else 42

    // like everything in scala, try-catch-finally is also an expression
    // and can be used for assignments
    val potentialFail = try { // return type is AnyVal bcuz getInt return Int but catch return Nothing
        getInt(true)
    } catch {
        case e: RuntimeException => println("caught RTE")
    } finally {
        // code that will run regardless of exception or not
        // optional. DOES NOT influence return type of this expression
        // used only for side effects
        println("finally")
    }

    println(potentialFail)

    // how to define own exception
    class MyException extends Exception
    val exception = new MyException
    throw exception

}
