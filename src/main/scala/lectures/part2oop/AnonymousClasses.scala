package lectures.part2oop

object AnonymousClasses extends App {
     abstract class Animal {
         def eat: Unit
     }

    val funnyClass: Animal = new Animal { // create anonymous class  on the fly with Animal implementation
        override def eat: Unit = println("hahahahah") // this is NOT a abstract class instantiation
    }

    // anonymous classes can extend abstract class (above example) and concrete classes as well
    println(funnyClass.getClass)

    class Person(name: String) {
        def sayHi: Unit = println("hi")
    }

    val jim = new Person("jim") {
        override def sayHi: Unit = println("hello i am jim")
    }

    // ANONYMOUS classes instantiated work for TRAITS AS WELL

}
