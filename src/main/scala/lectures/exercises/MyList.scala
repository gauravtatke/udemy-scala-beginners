package lectures.exercises

abstract class MyList {
    val head: Node = null
    val tail: Node = null

    def isEmpty: Boolean = true

    def add(value: Int): MyList

    def toString: String


}

case class Node(val value: Int, val next: Node, val prev: Node) {
    def this(value: Int, next: Node) = this(value, next, null)

    def this(value: Int) = this(value, null)
}

case class SinglyLinkedList(override val head: Node, override val tail: Node) extends MyList {
    def this() = this(null, null)

    override def isEmpty: Boolean = this.head == this.tail && this.head == null

    def add(value: Int): SinglyLinkedList = {
        if (this.isEmpty) {
            val newNode = new Node(value)
            new SinglyLinkedList(newNode, newNode)
        } else {
            val newNode = new Node(value, this.head)
            new SinglyLinkedList(newNode, newNode.next)
        }
    }

    override def toString: String = {
        var curr = this.head
        var strRep = "["
        while (curr != null) {
            strRep = strRep + curr.value + ","
            curr = curr.next
        }
        strRep + "]"
    }
}

abstract class GenList[+A] {
    val head: GenNode[A] = null
    val tail: GenNode[A] = null

    def isEmpty: Boolean

    def add[B >: A](value: B): GenList[B]

    def filter(predicate: MyPredicate[A]): GenList[A]

    // HOFS
    def foreach(f: GenNode[A] => Unit): Unit

    def sort(f: (A, A) => Int): GenList[A]
}

case class GenNode[+A](value: A, next: GenNode[A], prev: GenNode[A]) {
    def this(value: A, next: GenNode[A]) = this(value, next, null)

    def this(value: A) = this(value, null)

    override def toString: String = this.value.toString
}

case class GenLinkedList[+A](override val head: GenNode[A], override val tail: GenNode[A]) extends GenList[A] {
    def this() = this(null, null)
    override def isEmpty: Boolean = this.head == this.tail && this.head == null
    def add[B >: A](value: B): GenLinkedList[B] = {
        if (this.isEmpty) {
            val newNode = new GenNode(value)
            GenLinkedList(newNode, newNode)
        } else {
            val newNode = new GenNode(value, this.head)
            GenLinkedList(newNode, this.tail)
        }
    }

    override def toString: String = {
        var curr = this.head
        var strRep = "["
        while (curr != null) {
            strRep = strRep + curr.value.toString + ","
            curr = curr.next
        }
        strRep + "]"
    }

    override def filter(predicate: MyPredicate[A]): GenList[A] = {
        var curr = head
        var newList = new GenLinkedList[A]()
        while (curr != null) {
            if (predicate.test(curr.value)) {
                newList = newList.add(curr.value)
            }
            curr = curr.next
        }
        newList
    }

    // HOFs
    override def foreach(f: GenNode[A] => Unit): Unit = {
        def helper(f: GenNode[A] => Unit, node: GenNode[A]): Unit =
            if (node != null) {
                f(node)
                helper(f, node.next)
            }

        helper(f, this.head)
    }

    // NOT WORKING
    override def sort(f: (A, A) => Int): GenList[A] = {
        // f return +ve int of first param is greater, -ve if second param is greater and 0 if equal
        // f(x, y) => x-y
        def insertionSort(nodeToAdd: GenNode[A], sortedList: GenList[A]): GenList[A] = {
            // call insertion sort with current list's head and empty sorted list as starting position
            val sortedHead = sortedList.head
            val sortedTail = sortedList.tail
            if (sortedHead == sortedTail) return sortedList // single element
            if (sortedHead == null) {
                new GenLinkedList[A]().add(nodeToAdd.value)
            } else if (f(nodeToAdd.value, sortedHead.value) > 0) {
                insertionSort(nodeToAdd, GenLinkedList[A](sortedHead.next, sortedList.tail)).add(sortedHead.value)
            } else {
                sortedList.add(nodeToAdd.value)
            }
        }

        println(s"head = ${this.head}, tail = ${this.tail}")
        var sortedList: GenList[A] = null
        if (this.head != null) {
            sortedList = GenLinkedList(this.head.next, this.tail).sort(f)
        }
        insertionSort(this.head, sortedList)
    }
}

trait MyPredicate[-T] {
    def test(t: T): Boolean
}

trait MyTransformer[-A, B] {
    def transform(a: A): B
}

object MyListTest extends App {
    //    val sll: SinglyLinkedList = new SinglyLinkedList()
    //    println(sll.toString)
    //    val sll2 = sll.add(5)
    //    println(sll2)
    //    val sll3 = sll2.add(7)
    //    println(sll3)
    //    val sll4 = sll3.add(3)
    //    println(sll4)
    val genlist1 = new GenLinkedList[Int]().add(1)
      .add(2)
      .add(3)
      .add(4)
      .add(5)
      .add(6)

    //    println(genlist1)

    //    val genStr1 = new GenLinkedList[String]()
    //    val genStr2 = genStr1.add("hello")
    //    val genStr3 = genStr2.add("world")
    //    println(genStr1)
    //    println(genStr2)
    //    println(genStr3)
    //
    //    println(genlist1.filter(new MyPredicate[Int] {
    //        override def test(t: Int): Boolean = t%2 == 0
    //    }))

    //    genlist1.foreach(println)
    var unsortedGenList = new GenLinkedList[Int]()
      .add(4)
      .add(9)
      .add(1)
      .add(18)
      .add(5)
    //    unsortedGenList = unsortedGenList.add(4)
    //    println(s"head = ${unsortedGenList.head}, tail = ${unsortedGenList.tail}")
    //    unsortedGenList = unsortedGenList.add(9)
    //    println(s"head = ${unsortedGenList.head}, tail = ${unsortedGenList.tail}")
    //    unsortedGenList = unsortedGenList.add(1)
    //    println(s"head = ${unsortedGenList.head}, tail = ${unsortedGenList.tail}")
    //    unsortedGenList = unsortedGenList.add(18)
    //    println(s"head = ${unsortedGenList.head}, tail = ${unsortedGenList.tail}")
    //    unsortedGenList = unsortedGenList.add(5)
    //    println(s"head = ${unsortedGenList.head}, tail = ${unsortedGenList.tail}")
    println(unsortedGenList)
    val sortedList = unsortedGenList.sort((x: Int, y: Int) => x - y)
    println(sortedList)
}