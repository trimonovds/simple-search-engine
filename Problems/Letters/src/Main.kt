import java.util.*

fun main() {
    val letters = mutableMapOf<Int, String>()
    val scanner = Scanner(System.`in`)
    var index = 1

    while (true) {
        val letter = scanner.nextLine()
        letters[index] = letter
        index++
        if (letter == "z") {
            break
        }
    }
    print(letters[4])
}