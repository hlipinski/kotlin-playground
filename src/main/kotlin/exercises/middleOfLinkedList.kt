package exercises

fun main() {
    println(middleNode(generate(arrayOf(1, 2, 3, 4, 5, 6))))
}

fun middleNode(head: ListNode?): ListNode? {
    var headCounter = 0
    var node = head!!
    var nodeCache = head!!
    var counterCache = 0
    while (node.next != null) {
        headCounter++
        if (counterCache < (headCounter - counterCache)) {
            nodeCache = nodeCache.next!!
            counterCache++
        }
        node = node.next!!
    }
    return nodeCache
}

fun generate(arr: Array<Int>): ListNode? = if (arr.isEmpty()) null else ListNode(arr[0], generate(arr.sliceArray(1 until arr.size)))

class ListNode(var `val`: Int, var next: ListNode? = null)

