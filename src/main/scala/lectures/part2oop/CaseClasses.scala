package lectures.part2oop

object CaseClasses extends App {
    /*
    Case classes create some basic boilerplate for lightweight classes
    such as equals, toString, hashCode etc.
    mostly used for lightweight classes
     */

    class RegularPerson(name: String, age: Int)
    case class Person(name: String, age: Int)

    //1. class params are automatically promoted to class fields
    val jim = new Person("Jim", 34)
    println(jim.name) // jim.name is valid here

    //2. sensible toString implementation
    // print(instance) is automatically delegated to print(instance.toString)
    // java has this syntactic sugar by default but scala doesn't. Case classes enables it
    println(jim.toString) // without case class it would print very cryptic toString which is the default implementation
    println(jim)

    //3. equals and hashCode are implemented.
    // specially useful if classes are used in collections
    val jim2 = new Person("Jim", 34)
    println(jim == jim2) // without case classes this return false bcuz 2 separate objects. With case class values are compared

    //4. CCs have handy copy method
    val jim3 = jim.copy(age = 43)
    println(jim3)

    //5. CCs have companion objects
    val thePerson = Person // thePerson is valid companion object
    val mary = Person("Mary", 25) // calls Person objects apply method.
    println(thePerson)
    println(mary)

    // 6. CCs are serializable
    // specially useful in Akka

    //7. CCs have extractor pattern = can be used in PATTERN MATCHING

    // there are case objects too. they have similar properties as case classes except companion object
    // because case objects are companion objects to themselves
    case object UnitedKingdom {
        def name: String = "The UK of GB and NI"
    }
}
