package search

import java.io.File
import java.util.*

enum class SearchStrategy {
    ALL, ANY, NONE
}

val scanner = Scanner(System.`in`)
var invertedIndex: MutableMap<String, MutableSet<Int>> = mutableMapOf()

fun main(args: Array<String>) {
    val argName = "--data"
    val path = args[1 + args.indexOf("$argName")]
    val dataFile = File(path)
    val people: List<String> = dataFile.readLines()

    for (personLine in people.withIndex()) {
        var words = personLine.value.split(' ').map { it.toLowerCase() }
        for (word in words) {
            val set: MutableSet<Int> = invertedIndex[word] ?: mutableSetOf()
            set.add(personLine.index)
            invertedIndex[word] = set
        }
    }

    while(true) {
        prettyPrintHeader("Menu")
        println("1. Find a person")
        println("2. Print all people")
        println("0. Exit")

        when (scanner.nextLine().toInt()) {
            1 -> onFindPersonSelected(people)
            2 -> printAllPeople(people)
            0 -> break
            else -> {
                println("Incorrect option! Try again.")
            }
        }
    }

    println("Bye!")
}

private fun printAllPeople(people: List<String>) {
    prettyPrintHeader("List of people")
    for (p in people) {
        println(p)
    }
}

private fun prettyPrintHeader(text: String) {
    println("=== $text ===")
}

private fun onFindPersonSelected(people: List<String>) {
    val searchStrategies = SearchStrategy.values().joinToString(", ") { it.name }
    println("Select a matching strategy: $searchStrategies")
    val strategy = SearchStrategy.valueOf(scanner.nextLine())
    findPerson(people, strategy)
}

private fun findPerson(people: List<String>, strategy: SearchStrategy) {
    println("Enter a name or email to search all suitable people.")
    val query = scanner.nextLine().toLowerCase().trim()
    val queryWords = query.split(' ')
    when (strategy) {
        SearchStrategy.ALL -> {
            var indexes: Set<Int> = (people.indices).toSet()
            for (word in queryWords) {
                val wordIndexes = invertedIndex[word]
                if (wordIndexes != null) {
                    indexes = indexes.intersect(wordIndexes)
                }
            }
            printPeopleAtIndexes(indexes, people)
        }
        SearchStrategy.ANY -> {
            var indexes: Set<Int> = setOf()
            for (word in queryWords) {
                val wordIndexes = invertedIndex[word]
                if (wordIndexes != null) {
                    indexes = indexes.union(wordIndexes)
                }
            }
            printPeopleAtIndexes(indexes, people)
        }
        SearchStrategy.NONE -> {
            var indexes: Set<Int> = (people.indices).toSet()
            for (word in queryWords) {
                val wordIndexes = invertedIndex[word]
                if (wordIndexes != null) {
                    indexes = indexes.subtract(wordIndexes)
                }
            }
            printPeopleAtIndexes(indexes, people)
        }
    }
}

private fun printPeopleAtIndexes(indexes: Set<Int>, people: List<String>) {
    if (indexes.isEmpty()) {
        println("No matching people found.")
    } else {
        for (index in indexes) {
            println(people[index])
        }
    }
}
