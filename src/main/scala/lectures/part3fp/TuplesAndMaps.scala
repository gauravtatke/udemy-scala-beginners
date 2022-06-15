package lectures.part3fp

object TuplesAndMaps extends App {
    // tuples = finite ordered List
    val aTuple = Tuple2[Int, String](23, "hello scala")
    val anotherTuple = (45, "hello! scala") // Tuple2[Int, String] syntactic sugar

    println(aTuple._1) // prints 23, _1 is name of param, not an index
    println(aTuple.copy(_2 = "goodboy java"))
    println(aTuple.swap)

    // Maps: key -> value
    val aMap: Map[String, Int] = Map()
    val phonebook = Map(("Jim", 432), ("Daniel", 837)) // tuples as key, value pair
    val anotherPhoneBook = Map("crystal" -> 234, "eva" -> 990).withDefaultValue(-1) // with default value if key is not present
    println(anotherPhoneBook)

    println(phonebook.contains("Jim"))
    println(phonebook("Daniel")) // gets the value
    println(anotherPhoneBook("mary")) // returns -1. if key is not present then Exception is thrown without defaults
    val newPair = "Mary" -> 638
    val newPhonebook = phonebook + newPair
    println(newPhonebook)

    // functionals on maps
    // map, flatMap, filter
    println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // makes keys lowercase
    println(phonebook.view.filterKeys(_.startsWith("J")).toMap)
    println(phonebook.view.mapValues(_ * 10).toMap) // multiplies all the values by 10
    println(phonebook.toList)
    println(List(("dylan", 234134), ("bob", 22222)).toMap)

    val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
    println(names.groupBy(name => name.charAt(0))) // groupby first character in names

    // exercise
    // tiny social network map with some methods
    def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
        network + (person -> Set[String]())
    }

    def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
        // add a and b in friend list of each other
        val friendsA = network(a)
        val friendsB = network(b)
        network + (a -> (friendsA + b)) + (b -> (friendsB + a))
    }

    def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
        // unfriend a and b
        val friendA = network(a)
        val friendB = network(b)

        network + (a -> (friendA - b)) + (b -> (friendB - a))
    }

    def getFriendCount(network: Map[String, Set[String]], person: String): Int = {
        network.getOrElse(person, Set()).size
    }

    def getPersonWithMostFriends(network: Map[String, Set[String]]): String = {
        // instructor's solution
        network.maxBy(pair => pair._2.size)._1
        // my solution
//        network
//          .map(pair => pair._1 -> pair._2.size)
//          .fold("", 0)((pair1, pair2) => if (pair1._2 > pair2._2) pair1 else pair2)
//          ._1
    }

    def getPersonWithNoFriend(network: Map[String, Set[String]]): Int = {
        network.filter(pair => pair._2.size == 0).size
//        network.count(pair => pair._2.size == 0)
//        network.count(_._2.isEmpty)
    }

    def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
        network.view
          .filterKeys(_.ne(person)).toMap // get all key/val except person
          .map(pair => pair._1 -> (pair._2 - person)) // remove person from all friend lists
          .-(person)
    }

    val empty: Map[String, Set[String]] = Map()
    val network = add(add(empty, "Bob"), "Mary")
    println(network)
    val bobMary = friend(network, "Bob", "Mary")
    println(bobMary)
    println(unfriend(bobMary, "Bob", "Mary"))
    println(remove(bobMary, "Bob"))

    // Jim, Bob, Mary
    val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
    val jimBob = friend(people, "Bob", "Jim")
    val testNet = friend(jimBob, "Bob", "Mary")
    println(testNet)
    println(getPersonWithNoFriend(testNet))
    println(getFriendCount(testNet, "Bob"))
    println(getFriendCount(testNet, "Mary"))
    println(getPersonWithMostFriends(testNet))

    def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
        // return true if a and b are friends directly or have mutual friend
        def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
            // recursive bfs function to find target in discovered people while ignoring already consideredPeople
            if (discoveredPeople.isEmpty) false
            else {
                val person = discoveredPeople.head
                if (person == target) true // found
                else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
                else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
            }
        }
        bfs(a, Set(), network(b) + b)
    }

    println(socialConnection(testNet, "Bob", "Jim")) // true
    println(socialConnection(testNet, "Mary", "Jim")) // true because they have mutual friends

    val newNet = friend(add(add(testNet, "Dan"), "Eva"), "Dan", "Eva")
    println(newNet)
    println(socialConnection(newNet, "Bob", "Eva")) // false
}
