package lectures.part4patternmatching

object AllThePattern extends App {
    // 1 - constants
    val x: Any = "Scala"
    val constants = x match {
        case 1 => "a number"
        case "Scala" => "The SCALA"
        case true => "The Truth"
        case AllThePattern => "A singleton object"
    }

    // 2 - match anything
    // 2.1 wildcard
    val matchAnything = x match {
        case _ => ""
    }

    // 2.2 variable
    val matchAVariable = x match {
        case something => s"I've found $something"
    }

    // 3 - tuples
    val aTuple = (1, 2)
    aTuple match {
        case (1, 1) => ""
        case (something, 2) => s"found $something"
    }

    // nested tuples
    (1, (4,5)) match {
        case (1, tup) => s"inside $tup"
    }

    // list patterns
    val aList = List(1,2,3, 42)
    aList match {
        case List(1, _, _, _) => "" // extractor pattern - advanced
        case List(1, _*) => "" // list of arbitrary pattern - advanced
        case 1 :: List(2,_,_) => "" // infix pattern
        case List(1,2,3) :+ 42 => "" // also an infix pattern
    }

    val unknown: Any = 2
    unknown match {
        case list: List[Int] => ""// explicit type specifier
        case _ => ""
    }

    // name binding
    // one type of name binding happens when we use variable to match a pattern.
    // below is explicit name binding
    val nestedTuple = (1, (2, (5,6,7), (99, 100)))
    nestedTuple match {
        case nonEmptyTuple @ (1, _) => ""// this name can be used later
        case (1, (2, rest @ _, _)) => ""
    }

    // multi-patterns - joining patters if the action is same
    nestedTuple match {
        case (_) | (1, _) => "" // single value tuple or starts with 1
    }

    // if-guard
    nestedTuple match {
        case (1, (second,_, _)) if second % 2 == 0 => ""
    }

    // JVM trick question
    val numbers = List(1, 2, 3)
    val matchNum = numbers match {
        case listOfStrings: List[String] => "string"
        case listOfInts: List[Int] => "ints"
    }
    println(matchNum) // should ideally print "ints" but actually prints "string"
    // this is because jvm does type erasure after the compilation
    // both cases just become List, List or List[Object], List[Object]
    // the first one is matched and printed.
    // this is also shown in IDE message if you hover over the case List[String] branch


}

