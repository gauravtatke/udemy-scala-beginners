package lectures.part2oop

object Generics extends App {
    // traits also can be defined like below
    class MyList[A] {
        // use the type A
    }

    val listOfInt = new MyList[Int]
    val listOfString = new MyList[String]

    // generic methods
    object MyList {
        def empty[A]: MyList[A] = ??? // ??? means return nothing
    }
    val emptyListOfIntegers = MyList.empty[Int]

    // variance problem
    class Animal
    class Cat extends Animal
    class Dog extends Animal

    //1. yes, List[Cat] extends List[Animal] - COVARIANCE
    class CovariantList[+A] // notice +A
    // variable return type is List[Animal] but initialized to List[Cat]
    val animal: Animal = new Cat
    val animalList: CovariantList[Animal] = new CovariantList[Cat]
    // animalList.add(new Dog) ??? // HARD QUESTION

    // 2. NO = INVARIANCE
    class InvariantList[A]
    val invariantList: InvariantList[Animal] = new InvariantList[Animal]

    // 3. Hell, no! = CONTRAVARIANT
    class ContraVariantList[-A] // notice -A
    val contraVariantList:ContraVariantList[Cat] = new ContraVariantList[Animal]
    // Above example may not make sense in current example but can make sense in some cases
    class Trainer[-A]
    // trainer of animals can also be trainer of cats. this makes sense
    val trainer: Trainer[Cat] = new Trainer[Animal]

    // bounded types
    class Cage[A <: Animal](animal: A) { //type params can be Animal subtypes only => upper bound
        // some code
    }

    val cage = new Cage(new Dog) // works
    class Car
    // val newCage = new Cage(new Car) // does not work

    class Cage2[A >: Animal](animal: Animal) // type param can only be supertypes of Animal. lower bounded

    class NewList[+A] {
        /*
        A = Cat
        B = Animal
        in a list of cat if we add a dog (supertype) then it becomes list of animals
        if we remove "[B>:A], then compiler error is thrown
        this answers the HARD QUESTION above
         */
        def add[B>:A](element: B): MyList[B] = ???

    }
}