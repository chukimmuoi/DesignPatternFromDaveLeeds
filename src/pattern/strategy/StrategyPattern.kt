package pattern.strategy

/**
 * @author: My Project
 * @Skype: chukimmuoi
 * @Mobile : +84 167 367 2505
 * @Email: chukimmuoi@gmail.com
 * @Website: https://github.com/chukimmuoi
 * @Project: DesignPatternFromDaveLeeds
 * Created by chukimmuoi on 26/10/24.
 */
// Strategy
fun interface Validator {
    fun isValid(value: String): Boolean
}

// Concrete Strategy
val emailValidator = Validator { it.contains("@") && it.contains(".") }
val usernameValidator = Validator { it.isNotEmpty() }
val passwordValidator = Validator { it.length >= 8 }

// Context
class FormField(
    val name: String,
    val value: String,
    private val validator: Validator
) {
    fun isValid(): Boolean {
        return validator.isValid(value)
    }
}

fun main() {
    val emailField = FormField("email", "test@example.com", emailValidator)
    println("Email validation: ${emailField.isValid()}")

    val usernameField = FormField("username", "user123", usernameValidator)
    println("Username validation: ${usernameField.isValid()}")

    val passwordField = FormField("password", "password123", passwordValidator)
    println("Password validation: ${passwordField.isValid()}")
}