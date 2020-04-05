package com.andymariscal.shared.inf

internal data class Node<T>(
    val data: T,
    var prev: Node<T>? = null,
    var next: Node<T>? = null
)

open class DSFlowSequence<T> {
    //https://kotlinlang.org/docs/reference/functions.html#infix-notation

    //region Global properties
    private var head: Node<T>? = null
    private var helperNode: Node<T>? = null
    private val stepTracker = mutableMapOf<Int, Node<T>>()
    //endregion

    private infix fun start(root: T): DSFlowSequence<T> = this.apply {
        if (head != null) throw IllegalStateException("[start] method have been called already in your FlowSequence object")

        head = Node(root).apply { prev = null }
        helperNode = head
    }

    infix fun followBy(next: T): DSFlowSequence<T> = this.apply {
        if (head == null) start(next) else {
            val newNode = Node(next)
            newNode.prev = helperNode
            helperNode?.next = newNode

            helperNode = newNode
        }
    }

    infix fun trackStep(key: Int): DSFlowSequence<T> = this.apply {
        helperNode?.also { stepTracker[key] = it }
    }

    fun next(): T? {
        val data = head?.data
        head = head?.next
        return data
    }

    fun moveTo(step: Int): Boolean =
        if (stepTracker.containsKey(step)) {
            head = stepTracker[step]
            true
        } else false

}
