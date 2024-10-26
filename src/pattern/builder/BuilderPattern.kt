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

// Concrete Builder
class NotificationSettingsBuilder: INotificationSettingsBuilder {
    override var enable: Boolean = false
    private val subscriptions = mutableListOf<Subscription>()

    override fun addSubscription(
        destination: Destination,
        topic: Subscription.Topic,
        frequency: Subscription.Frequency
    ) {
        subscriptions.add(Subscription(destination, topic, frequency))
    }

    override fun build(): NotificationSettings {
        return NotificationSettings(
            enable,
            subscriptions.toList()
        )
    }
}

// Builder
interface INotificationSettingsBuilder {
    var enable: Boolean
    fun addSubscription(
        destination: Destination,
        topic: Subscription.Topic,
        frequency: Subscription.Frequency
    )
    fun build(): NotificationSettings
}

fun main() {
    createNotificationSettings()
}

// Director
fun createNotificationSettings(
    email: EmailAddress? = null,
    phone: PhoneNumber? = null,
): NotificationSettings {
    val builder: INotificationSettingsBuilder = NotificationSettingsBuilder()

    if (email != null) builder.addSubscription(
        email,
        Subscription.Topic.ANALYTICS,
        Subscription.Frequency.DAILY
    )
    if (email != null) builder.addSubscription(
        email,
        Subscription.Topic.NEWS,
        Subscription.Frequency.WEEKLY
    )
    if (phone != null) builder.addSubscription(
        phone,
        Subscription.Topic.SECURITY_ALERTS,
        Subscription.Frequency.IMMEDIATELY
    )
    if (phone != null) Subscription.Topic.entries.forEach {
        builder.addSubscription(phone, it, Subscription.Frequency.IMMEDIATELY)
    }
    builder.enable = true

    val settings = builder.build()

    return settings
}
