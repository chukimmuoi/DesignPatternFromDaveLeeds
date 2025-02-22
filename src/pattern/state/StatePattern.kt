package pattern.state

enum class UserState(
    val signUp : User.(String) -> Unit,
    val verifyEmail : User.(String) -> Unit,
    val viewContent : () -> Unit,
    val viewProfile : User.() -> Unit,
    val editProfile : User.(String) -> Unit,
) {
    ANONYMOUS(
        signUp = { println("Signing up with email: $email."); email = it; state = UNVERIFIED },
        verifyEmail = { println("You must sign up before verifying email.") },
        viewContent = { println("Viewing public content.") },
        viewProfile = { println("You must sign in to view your profile.") },
        editProfile = { println("You must sign in to edit your profile.") },
    ),
    UNVERIFIED(
        signUp = { println("You are already signed up.") },
        verifyEmail = { println("Verifying email with token: $it."); state = AUTHENTICATED },
        viewContent = { println("Viewing personalized content for unverified account.") },
        viewProfile = { println("Profile: $email (Unverified account, please verify your email).") },
        editProfile = { println("Please verify your account before editing your profile.") },
    ),
    AUTHENTICATED(
        signUp = { println("You are already signed up and authenticated.") },
        verifyEmail = { println("You are already verified.") },
        viewContent = { println("Viewing personalized content.") },
        viewProfile = { println("Profile: $email (Fully authenticated).") },
        editProfile = { println("Updated email from $email to $it."); email = it },
    )
}

class User(var email: String? = null, var state: UserState = UserState.ANONYMOUS) {
    fun signUp(email: String) = state.signUp(this, email)
    fun verifyEmail(token: String) = state.verifyEmail(this, token)
    fun viewContent() = state.viewContent()
    fun viewProfile() = state.viewProfile(this)
    fun editProfile(newEmail: String) = state.editProfile(this, newEmail)
}