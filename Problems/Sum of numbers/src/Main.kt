fun solution(numbers: List<Int>): Int {
    return numbers.reduce { acc, i -> acc + i }
}