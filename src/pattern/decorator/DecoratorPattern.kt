package pattern.decorator

import java.util.*

enum class Clock {
    Default;

    fun now(): String {
        return "21h28'"
    }
}

class Logger(
    private val includeDateTime: Boolean,
    private val includeThreadName: Boolean,
    private val includeUniqueId: Boolean,
    private val clock: Clock
) {
    fun log(message: String) {
        if (includeDateTime) print("[${clock.now()}]")
        if (includeUniqueId) print("[${UUID.randomUUID()}]")
        print(message)
        if (includeThreadName) print(" on ${Thread.currentThread().name} thread")
        println()
    }
}

fun main() {
    val logger = Logger(
        includeDateTime = true,
        includeThreadName = true,
        includeUniqueId = true,
        clock = Clock.Default
    )

    logger.log(" Application init")
}