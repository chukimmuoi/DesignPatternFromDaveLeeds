package pattern.builder

/**
 * @author: My Project
 * @Skype: chukimmuoi
 * @Mobile : +84 167 367 2505
 * @Email: chukimmuoi@gmail.com
 * @Website: https://github.com/chukimmuoi
 * @Project: DesignPatternFromDaveLeeds
 * Created by chukimmuoi on 26/10/24.
 */
// Product
data class NotificationSettings(
    val enable: Boolean,
    val subscriptions: List<Subscription>
)

data class Subscription(
    val destination: Destination,
    val topic: Topic,
    val frequency: Frequency
) {
    enum class Topic { NEWS, ANALYTICS, SECURITY_ALERTS }
    enum class Frequency { IMMEDIATELY, DAILY, WEEKLY }
}

sealed interface Destination
@JvmInline value class EmailAddress(val value: String): Destination
@JvmInline value class PhoneNumber(val value: String): Destination

fun notificationSettings(block: NotificationSettingsBuilder.() -> Unit) =
    NotificationSettingsBuilder().apply(block).build()

// Concrete Builder
class NotificationSettingsBuilder {
    var enable: Boolean = false
    private val subscriptions = mutableListOf<Subscription>()

    fun send(
        topicToDestination: Pair<Subscription.Topic, Destination>,
        frequency: Subscription.Frequency
    ) {
        val (topic, destination) = topicToDestination
        subscriptions.add(Subscription(destination, topic, frequency))
    }

    fun build(): NotificationSettings {
        return NotificationSettings(
            enable,
            subscriptions.toList()
        )
    }
}

fun main() {
    createNotificationSettings()
}

// Director
fun createNotificationSettings(
    email: EmailAddress? = null,
    phone: PhoneNumber? = null,
): NotificationSettings {
    val settings = notificationSettings {
        if (email != null) send(
            Subscription.Topic.ANALYTICS to email,
            Subscription.Frequency.DAILY
        )
        if (email != null) send(
            Subscription.Topic.NEWS to email,
            Subscription.Frequency.WEEKLY
        )
        if (phone != null) send(
            Subscription.Topic.SECURITY_ALERTS to phone,
            Subscription.Frequency.IMMEDIATELY
        )
        enable = true
    }
    return settings
}
