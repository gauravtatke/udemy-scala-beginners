package lectures.part1basics

object Expressions extends App {
    val x = 1 + 3 // RHS is an expression
    println(x)
    println( 2 + 3 * 4) // 14
    // + - * / & | ^ << >> >>> (RHS with a zero expression)

    println(1 == x) // relational expressions
    // == != < <= >=

    println(!(1==x))
    // ! && ||

    var aVariable = 2
    aVariable += 3 // also works with -= *= /= ... side effects

    // instructions (do something) vs expressions (has value or a type)
    // if expression
    val aCondition = true
    val aConditionedValue = if (aCondition) 5 else 3
    println(aConditionedValue)
    println(if (aCondition) "true" else "false")

    // loops, are discouraged in scala. they are not functional style programming
    var i = 0
    while (i < 10) {
        println(i)
        i += 1
    }

    // EVERYTHING in SCALA is an EXPRESSION

    val aWierdValue = (aVariable = 3) // Unit === Void in other languages
    println(aWierdValue)

    // side effects: println(), loops that don't return anything, reassigning a var, anything that returns Unit
    // side effects are remnants of imperative programming. they are instructions, but in scala, they are still
    // expressions with Unit return type

    // Code Blocks
    val aCodeBlock = {
        val y = 2 // this is not visible outside of the code block
        val z = y + 1
        if (z > 2) "heloww" else "good" // return value is value of the last expressions, string in this case
    }

    println(aCodeBlock)
}
