package com.tsystems.javaschool.tasks.calculator

class StackImplementation {

    val elements: MutableList<Any> = mutableListOf()

    fun isEmpty() = elements.isEmpty()

    fun push(item: Any) = elements.add(item)

    fun pop(): Any? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            elements.removeAt(elements.size - 1)
        }
        return item
    }

    fun peek(): Any? = elements.lastOrNull()
    
}


