package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {
    class Person(val name: String, favMovie: String, val age: Int = 0) {
        def likes(movie: String): Boolean = movie == favMovie
        // very permissible method names
        def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
        def unary_! : String = s"${this.name}, what the heck!"
        def isAlive: Boolean = true
        def apply(): String = s"Hi, my name is $name and I like $favMovie"

        /* exercises */
        def +(nickname: String) = new Person(s"$name ($nickname)", this.favMovie)
        def unary_+ : Person = new Person(this.name, this.favMovie, this.age+1)
        def learns(lang: String) = s"$name learns $lang"
        def learnsScala = learns("Scala")
        def apply(times: Int): String = s"$name watched $favMovie $times times"

    }

    val mary = new Person("Mary", "inception")
    println(mary.likes("inception"))
    println(mary likes "inception") // method with single param can be called in infix style notation
    // resembles natural language (syntactic sugar)
    val tom = new Person("Tom", "Fight Club")
    println(mary + tom)
    println(mary.+(tom)) // equivalent

    println(1 + 2) // all operators are methods actually
    println(1.+(3))

    // prefix notation
    val x = -1
    val y = 1.unary_- // this and above are equivalent. unary operators are also methods
    // unary_ prefix only works with - + ~ !

    println(!mary) // equivalent to "mary, what the heck!"

    // postfix notation
    println(mary.isAlive)
    println(mary isAlive) // postfix notation only used when methods have no params
    // not used much as people are more accustomed to .notation

    // apply method, special method
    println(mary.apply())
    println(mary()) // calls the apply method hence these 2 are equivalent

    // exercises
    val maryRockstar = mary + "the rockstar"
    println(maryRockstar.name)
    println(mary learns "scala")
    println(mary learnsScala)
    println(mary(3))
    println((+mary).age)
}
