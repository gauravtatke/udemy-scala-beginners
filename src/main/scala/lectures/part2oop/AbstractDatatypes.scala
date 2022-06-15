package lectures.part2oop

object AbstractDatatypes extends App {
    // abstract
    abstract class Animal {
        val creatureType: String // no value, but can have value
        def eat: Unit // no definition, but can have definition
        val isMammal: Boolean = true // initialized value
    }

    class Dog extends Animal {
        val creatureType: String = "Canine"
        def eat: Unit = println("crunch") // override keyword not mandatory if there is no implementation or value definition in parent class
    }

    val dog = new Dog()
    dog.eat

    // traits
    trait Carnivore {
        def eat(animal: Animal): Unit // no definition again
    }

    class Crocodile extends Animal with Carnivore {
        val creatureType: String = "Reptile"
        def eat: Unit = println("nomnomnom")
        def eat(animal: Animal) = println(s"I'm a croc and I am eating ${animal.creatureType}")
        override val isMammal: Boolean = false // override keyword is mandatory because Animal has default value set
    }
    val croc = new Crocodile()
    croc.eat(dog)

    // traits vs abstract classes
    // 1 - traits do not have constructor parameter (in Scala 3, it is possible)
    // 2 - a class can implement (inherit) multiple traits
    // 3 - (style patter) traits define thing's behavior, abstract class define what the "thing"
}
