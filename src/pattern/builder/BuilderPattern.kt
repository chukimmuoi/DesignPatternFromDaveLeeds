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
data class NotificationSetting(
    val enable: Boolean = false,
    val subscriptions: List<Subscription> = emptyList()
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

fun main() {
    createNotificationSettings(
        EmailAddress("example@example.com"),
        PhoneNumber("1-555-555-5555")
    )
}

fun createNotificationSettings(
    email: EmailAddress?,
    phone: PhoneNumber?,
): NotificationSetting {
    return NotificationSetting(
        subscriptions = buildList {
            if (email != null) add(
                Subscription(
                    email,
                    Subscription.Topic.ANALYTICS,
                    Subscription.Frequency.DAILY
                )
            )
            if (email != null) add(
                Subscription(
                    email,
                    Subscription.Topic.NEWS,
                    Subscription.Frequency.WEEKLY
                )
            )
            if (phone != null) add(
                Subscription(
                    phone,
                    Subscription.Topic.SECURITY_ALERTS,
                    Subscription.Frequency.IMMEDIATELY
                )
            )
        },
        enable = true,
    )
}
