package lectures.part1basics

object StringOps extends App {
    val str: String = "Hello, I am learning Scala"

    println(str.charAt(2))
    println(str.substring(7, 11))
    println(str.split(" ").toList)
    println(str.startsWith("Hello"))
    println(str.replace(" ", "-"))

    val aNumberString = "445"
    val aNumber = aNumberString.toInt
    println('a' +: aNumberString :+ 'z') // prepending and appending
    println(str.reverse)
    println(str.take(5))

    // scala specific string interpolation
    // S-interpolation
    val name = "David"
    val age = 12
    println(s"hello my name is $name, i am $age years old")
    println(s"I will be turning ${age + 1}")

    // F-interpolators
    val speed = 1.2f
    val myth = f"$name%s can eat $speed%2.2f burgers per minute" // printf like format string
    println(myth)

    // raw-interpolator
    println(raw"this is a \n newline") // newline char is printed literally

    val rawStr = "this a \n newline char"
    println(raw"$rawStr") // in an injected string, newline char is expanded to its actual meaning
}
