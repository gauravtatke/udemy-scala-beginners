package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
    def factorial(n: Int): Int =
        if (n <= 1) 1
        else {
            println(s"Computing value of $n - I first need factorial of " + (n-1))
            val result = n * factorial(n-1)
            println("Computed factorial of " + n)
            result
        }

    println(factorial(10))
//    println(factorial(5000)) // gives stack overflow error

    def anotherFactorial(n: Int): BigInt = {
        @tailrec // this annotation tells compiler that this func is supposed to be tail recursive. compiler throws error if it is not a tail recursive func.
        def factHelper(x: Int, accumulator: BigInt): BigInt =
            if (x <= 1) accumulator
            else factHelper(x-1, x * accumulator) // TAIL RECURSION - use recursive call as the LAST expression
        factHelper(n, 1)
    }
    /*
    anotherFactorial(10) = factHelper(10, 1)
        = factHelper(9, 10 * 1)
        = factHelper(8, 9*10*1)
        = factHelper(7, 8*9*10)
        = ....
        = factHelper(1, 1*2*....8*9*10*1)
        = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10
     */

//    println(anotherFactorial(5000))
    // WHEN YOU USE NEED LOOPS, USE _TAIL_RECURSION.

    /* Excercises */
    // 1. concatenate string n times
    def strConcat(s: String, n: Int): String = {
        @tailrec
        def concatHelper(st: String, times: Int, accumStr: String): String =
            if (times < 1) accumStr
            else concatHelper(st, times-1, st + accumStr)
        concatHelper(s, n, "")
    }
    println(strConcat("hello", 3))

    // 2. isPrime function in tail recursion
    def isPrime(n: Int): Boolean = {
        @tailrec
        def helper(num: Int, divisor: Int): Boolean  =
            if (divisor <= 1) true
            else (num % divisor != 0) && helper(num, divisor-1)
        helper(n, n/2)
    }
    println(isPrime(13))
    println(isPrime(23))
    println(isPrime(10))

   def anotherIsPrime(n: Int): Boolean = {
       @tailrec
       def helper(t: Int, isStillPrime: Boolean): Boolean = {
           if (!isStillPrime) false
           else if (t <= 1) true
           else helper(t-1, n%t != 0 && isStillPrime)
       }

       helper(n/2, true)
   }
    println(anotherIsPrime(2003)) // true
    println(anotherIsPrime(629)) // false

    // 3. fibonacci with tail recursion
    // number of recursive calls should be equal to number of accumulator variables generally
    def fibonacci(n: Int): Int = {
        @tailrec
        def helper(num: Int, last: Int, nextToLast: Int): Int = {
            if (num >= n) last
            else helper(num+1, last+nextToLast, last)
        }
        if (n <= 2) 1
        else helper(2, 1, 1)
    }
    println(fibonacci(3)) // 1 1 2 3 5 8 13 21
    println(fibonacci(8))
}
