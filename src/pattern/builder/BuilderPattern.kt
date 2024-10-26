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

fun main() {
    createNotificationSettings()
}

fun createNotificationSettings(): NotificationSetting {
    return NotificationSetting(
        enable = true,
        subscriptions = listOf(
            Subscription(
                EmailAddress("example@example.com"),
                Subscription.Topic.ANALYTICS,
                Subscription.Frequency.DAILY
            ),
            Subscription(
                EmailAddress("example@example.com"),
                Subscription.Topic.NEWS,
                Subscription.Frequency.WEEKLY
            ),
            Subscription(
                PhoneNumber("1-555-555-5555"),
                Subscription.Topic.SECURITY_ALERTS,
                Subscription.Frequency.IMMEDIATELY
            )
        )
    )
}
