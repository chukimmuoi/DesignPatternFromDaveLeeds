package pattern.decorator

import java.util.*

enum class Clock {
    Default;

    fun now(): String {
        return "21h28'"
    }
}

// Component
interface Logger {
    fun log(message: String)
}

// Concrete Component
class ConsoleLogger : Logger {
    override fun log(message: String) {
        println(message)
    }
}

// Concrete Decorator
class UniqueIdLogger(private val logger: Logger) : Logger {
    override fun log(message: String) = logger.log("[${UUID.randomUUID()}] $message")
}

// Concrete Decorator
class ThreadNameLogger(private val logger: Logger) : Logger {
    override fun log(message: String) = logger.log("$message on ${Thread.currentThread().name} thread")
}

// Concrete Decorator
class DateTimeLogger(private val logger: Logger, private val clock: Clock = Clock.Default) : Logger {
    override fun log(message: String) = logger.log("[${clock.now()}] $message")
}

fun main() {
    val logger = UniqueIdLogger(ThreadNameLogger(DateTimeLogger(ConsoleLogger())))

    logger.log("Application init")
}