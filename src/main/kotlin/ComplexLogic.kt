package org.example

import java.util.Locale

class ComplexLogic {

    fun whatIsTheMeaningOfLife(): String = "Who the f*ck cares?"

    fun willIEverBeHappy(): Boolean = false

    fun capitalize(input: String) = input.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

    fun addition(a: Int, b:Int) = a + b

    fun subtraction(a: Int, b:Int) = a-b
}