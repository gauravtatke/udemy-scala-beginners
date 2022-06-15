package lectures.part2oop

object Objects extends App {
    // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY (I.E. "STATIC" VARIABLES AND METHODS)
    // Objects in scala have the class level functionality.
    // Objects in scala are distinct from "instance of class" definition
    object Person {
        val N_EYES = 2
        def apply(mother: Person, father: Person): Person = new Person("Bobbie")
    }

    // objects are defined the same way as class and can contain val, var, methods etc.
    // objects do not have parameters. That is different than classes.
    println(Person.N_EYES)

    // Scala object = SINGLETON INSTANCE
    // that means objects define their own type and their own instance.
    val mary = Person
    val john = Person
    println(mary == john) // returns true

    class Person(name: String) {
        // instance-level functionality
        // defined in the same scope

    }

    // class and object Person are called companions.
    // Object containing static type functionality and class containing instance level functionality

    val david = new Person("david")
    val susan = new Person("susan") // new keyword for class
    println(david == susan) // returns false because 2 different classes

    val bobbie = Person(susan, david) // object made callable because of apply method, looks like constructor
    // above pattern is widely used

    // SCALA APPLICATIONS (App) are Objects with main method

}
