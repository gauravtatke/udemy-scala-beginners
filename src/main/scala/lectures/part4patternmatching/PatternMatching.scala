package lectures.part4patternmatching

import scala.util.Random

object PatternMatching extends App {
    // switch on steroids
    val random = new Random()
    val x = random.nextInt(10) // random int between 0-10
    val description = x match {
        case 1 => "the ONE"
        case 2 => "double or nothing"
        case 3 => "third time is the charm"
        case _ => "something else" // default case. _ is wildcard
    }

    println(x)
    println(description)

    // 1. Decompose values (with case classes)
    case class Person(name: String, age: Int)

    val bob = Person("Bob", 20)
    val greeting = bob match {
        case Person(n, a) if a < 21 => s"Hi my name is $n, and I can't drink US" // if guard
        case Person(n, a) => s"Hi my name is $n, and my age is $a"
        case _ => "Who am I?"
    }
    println(greeting)

    /*
    1. cases are matched in order
    2. if no cases match, MatchError is thrown. therefore use _ to match any value to cover all bases
    3. type of match expression is unified type of all case branches.
    4. PM really works well with case classes bcuz they have extractor pattern enabled by default
    normal classes do not have that and it takes some advance concept to match against them in match expression
     */

    // PM on sealed hierarchies
    sealed class Animal

    case class Dog(breed: String) extends Animal

    case class Parrot(greeting: String) extends Animal

    val animal: Animal = Dog("Terra Nova")
    animal match {
        case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
    }

    // exercise - human readable form of expression
    trait Expr

    case class Number(n: Int) extends Expr

    case class Sum(e1: Expr, e2: Expr) extends Expr

    case class Prod(e1: Expr, e2: Expr) extends Expr

    // sum(Number(1), Number(2)) = 2 + 3
    // Sum(Prod(Number(2), Number(4)), Number(5)) = 2 * 4 + 5

    def showExpr(expr: Expr): String = {
        def showParenthesis(e: Expr): String = {
            // may be show parenthesis for Prod if Prod is with sum of 2 numbers
            e match {
                case Prod(_, _) => showExpr(e)
                case Number(_) => showExpr(e)
                case _ => "(" + showExpr(e) + ")"
            }
        }

        expr match {
            case Number(n) => s"$n"
            case Sum(e1, e2) => showExpr(e1) + " + " + showExpr(e2)
            case Prod(e1, e2) => showParenthesis(e1) + " * " + showParenthesis(e2)
            case _ => ""
        }
    }

    val expr1 = Number(2)
    val expr2 = Sum(Number(2), Number(3))
    val expr3 = Prod(Number(2), Number(3))
    val exprStr1 = showExpr(expr1)
    val exprStr2 = showExpr(expr2)
    val exprStr3 = showExpr(expr3)
    println(expr3)
    println(exprStr3)
    val compositeExpr1 = Sum(Prod(Number(2), Number(5)), Number(9)) // no parens in this case
    val compositeExpr2 = Prod(Sum(Number(3), Number(4)), Sum(Number(10), Number(2)))
    println(showExpr(compositeExpr1))
    println(showExpr(compositeExpr2))
}