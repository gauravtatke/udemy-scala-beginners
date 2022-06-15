package lectures.part2oop

object OOBasics extends App {
    val person = new Person
    println(person)

    val anotherPerson = new AnotherPerson("john", 26)
    // anotherPerson.name gives error because its a constructor param not a class field.
//    println(anotherPerson.age)
    val oneMorePerson = new OneMorePerson("david", 42)
    println(oneMorePerson.name)
    oneMorePerson.greet("gaurav")

    val author = new Writer("charles", "dickens", 1812)
    val novel = new Novel("great expectation", 1861, author)
    val imposter = new Writer("charles", "dickens", 1812)
    println(novel.authorAge)
    println(novel.isWrittenBy(imposter))
}

class Person

// this is just a constructor, object.name and object.age give
class AnotherPerson(name: String, age: Int)

// all val and var in constructor param AND class body become class field. So `.name` and `.age` is valid in this case
class OneMorePerson(val name: String, val age: Int) {
    val x = 3
    // class body can contain any expression just like a normal code block
    // below gets executed when object is instantiated
    println(1+5)

    def greet(name: String): Unit = println(s"${this.name} says: hello $name")
    def greet(): Unit = println(s"I am $name") // here name is bound to this.name because there is no ambiguity

    // multiple constructors
    def this(name: String) = this(name, 0)
    def this() = this("john doe")
    // auxiliary constructors can only contain other constructors as a body.
    // this limitation causes these constructors pretty useless. they can be used to make some parameters default
    // but default params ARE supported in class definition as well so no need to define aux constructors for that
}

/* Excercises */
//1.
class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
    def fullName(): String = s"$firstName $lastName"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
    def authorAge: Int = yearOfRelease - author.yearOfBirth
    def isWrittenBy(author: Writer) = this.author == author
    def copy(year: Int) = new Novel(this.name, year, this.author)
}

//2.
class Counter(val count: Int) {
    def currentCount = this.count
    def increment = new Counter(this.count + 1)
    def increment(incVal: Int) = new Counter(this.count + incVal)
    def decrement = new Counter(this.count - 1)
    def decrement(decrVal: Int) = new Counter(this.count - decrVal)
}
