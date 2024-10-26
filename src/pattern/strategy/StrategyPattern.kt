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
interface Validator {
    fun isValid(value: String): Boolean
}

// Concrete Strategy
class EmailValidator : Validator {
    override fun isValid(value: String): Boolean = value.contains("@") && value.contains(".")
}

class UsernameValidator : Validator {
    override fun isValid(value: String): Boolean = value.isNotEmpty()
}

class PasswordValidator : Validator {
    override fun isValid(value: String): Boolean = value.length >= 8
}

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
        //...
}