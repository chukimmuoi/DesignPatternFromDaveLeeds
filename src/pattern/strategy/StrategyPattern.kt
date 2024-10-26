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
interface FromField {
    val name: String
    val value: String
    fun isValid(): Boolean
}

class EmailField(override val value: String) : FromField {
    override val name: String = "email"
    override fun isValid(): Boolean {
        return value.contains("@") && value.contains(".")
    }
}

class UsernameField(override val value: String) : FromField {
    override val name: String = "username"
    override fun isValid(): Boolean {
        return value.isNotEmpty()
    }
}

class PasswordField(override val value: String) : FromField {
    override val name: String = "password"
    override fun isValid(): Boolean {
        return value.length >= 8
    }
}

class OptionEmailField(override val value: String = "") : FromField {
    override val name: String = "email"
    override fun isValid(): Boolean {
        return value.isEmpty() || value.contains("@") && value.contains(".")
    }
}

class OptionUsernameField(override val value: String = "") : FromField {
    override val name: String = "username"
    override fun isValid(): Boolean {
        return value.isEmpty() || value.isNotEmpty()
    }
}

class OptionPasswordField(override val value: String = "") : FromField {
    override val name: String = "password"
    override fun isValid(): Boolean {
        return value.isEmpty() || value.length >= 8
    }
}

fun main() {
        //...
}