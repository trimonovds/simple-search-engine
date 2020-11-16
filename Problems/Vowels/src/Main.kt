data class CharInfo(val index: Int, val isVowel: Boolean)

var index = 1
fun nextChangeInfo(isVowel: Boolean): CharInfo {
    val res = CharInfo(index, isVowel)
    index++
    return res
}

fun main() {
    val alphabet = mutableMapOf<String, CharInfo>(
            "a" to nextChangeInfo(true),
            "b" to nextChangeInfo(false),
            "c" to nextChangeInfo(false),
            "d" to nextChangeInfo(false),
            "e" to nextChangeInfo(true),
            "f" to nextChangeInfo(false),
            "g" to nextChangeInfo(false),
            "h" to nextChangeInfo(false),
            "i" to nextChangeInfo(true),
            "j" to nextChangeInfo(false),
            "k" to nextChangeInfo(false),
            "l" to nextChangeInfo(false),
            "m" to nextChangeInfo(false),
            "n" to nextChangeInfo(false),
            "o" to nextChangeInfo(true),
            "p" to nextChangeInfo(false),
            "q" to nextChangeInfo(false),
            "r" to nextChangeInfo(false),
            "s" to nextChangeInfo(false),
            "t" to nextChangeInfo(false),
            "u" to nextChangeInfo(true),
            "v" to nextChangeInfo(false),
            "w" to nextChangeInfo(false),
            "x" to nextChangeInfo(false),
            "y" to nextChangeInfo(false),
            "z" to nextChangeInfo(false)
    )
    val letter = readLine()!!
    val info = alphabet[letter.toLowerCase()]
    val out = if (info != null) {
        if (info.isVowel) info.index else 0
    } else 0
    println(out)
}