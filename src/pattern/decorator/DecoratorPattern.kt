package pattern.decorator

import java.util.*

enum class Clock {
    Default;

    fun now(): String {
        return "21h28'"
    }
}

// Component
fun interface Logger {
    fun log(message: String)
}

// Concrete Component
val consoleLogger = Logger { println(it) }

// Concrete Decorator
fun Logger.withUniqueId() = Logger { log("[${UUID.randomUUID()}] $it") }

// Concrete Decorator
fun Logger.withThreadName() = Logger { log("$it on ${Thread.currentThread().name} thread") }

// Concrete Decorator
fun Logger.withDateTime(clock: Clock = Clock.Default) = Logger { log("[${clock.now()}] $it") }

fun main() {
    val logger = consoleLogger.withDateTime().withThreadName().withUniqueId()

    logger.log("Application init")
}