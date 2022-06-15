package lectures.part2oop

object Inheritance extends App {
    class Animal {
        val creatureType = "wild"
        def eat = "nom nom nom" // by default public
        private def drink = "gulp gulp" // can only be accessed in this class
        protected def chew = "chew chew" // can only be accessed in this class and all sub-classes
    }

    class Cat extends Animal {
        def crunch = {
            println(chew)
            println("crunch crunch")
        }
    }

    val cat = new Cat
    println(cat.eat)
    println(cat.crunch)

    class Person(name: String, age: Int) {
        def this(name: String) = this(name, 0)
    }

    class Adult(name: String, age: Int, idCard: String) extends Person(name, age) // calls the 2-param constructor of super class
    class Adult2(name: String, age: Int, idCard: String) extends Person(name) // calls aux constructor of superclass

    class Dog extends Animal {
        override def chew: String = "chewww choo chew choo" // made protected method a private method
        override val creatureType: String = "domestic" // val and vars can also be overriden
    }
    val dog = new Dog
    println(dog.chew)
    println(dog.creatureType)

    class AnotherDog(override val creatureType: String) extends Animal {
        // override creaturetype in class constructor as well
        override def eat: String = {
            println(super.eat) // calling parent class eat method
            "gnome gnome"
        }
    }

    val anotherDog = new AnotherDog("k9")
    println(anotherDog.eat)

    // preventing overrides
    // 1. use final on a member
    // 2. or use final on entire class
    // 3. or use sealed on a class. softer restriction. can be extended in current file but cannot be extended in another file
}
