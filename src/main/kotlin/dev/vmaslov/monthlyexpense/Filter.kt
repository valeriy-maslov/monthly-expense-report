package dev.vmaslov.monthlyexpense

interface Filter<T> {

    fun items(): List<T>
}