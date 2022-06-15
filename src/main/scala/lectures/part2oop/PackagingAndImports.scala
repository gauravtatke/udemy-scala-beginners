package lectures.part2oop

import lectures.playground.{Cinderella, PrinceCharming}
//import lectures.playground._
//import lectures.playground.{Cinderella => Princess, PrinceCharming}

object PackagingAndImports extends App {
    // package members are accessible by their simple names
    val writer = new Writer("Daniel", "RockTheJVM", 2018)

    // import the package
    val princess = new Cinderella
    // packages are imported by hierarchy
    // matching folder structure
    // package objects - for constants and fields that reside outside of the package. Universal Constants are set here
    // from IDE create a package object. Every package can have only one package object
    // name of package object file is package.scala. Rarely used but quite handy
    sayHello
    println(SPEED_OF_LIGHT)

    // importing
    // multiple entities can be imported using {}
    // everything in a package or file using `_`.
    // name aliasing can be done using =>
    val prince = new PrinceCharming
//    val anotherPrincess = new Princess

    // default imports
    // java.lang - String, Object, Exception
    // scala - Int, Nothing, Function
    // scala predefx - println, ???
}
