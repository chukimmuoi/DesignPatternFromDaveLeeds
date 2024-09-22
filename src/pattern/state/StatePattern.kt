package pattern.state

import pattern.state.UserState.*

enum class UserState {
    ANONYMOUS, UNVERIFIED, AUTHENTICATED
}

class User(var email: String? = null, var state: UserState = ANONYMOUS) {

    fun signUp(email: String) {
        when (state) {
            ANONYMOUS -> {
                println("Signing up with email: $email.")
                this.email = email
                state = UNVERIFIED
            }
            UNVERIFIED -> println("You are already signed up.")
            AUTHENTICATED -> println("You are already signed up and authenticated.")
        }
    }

    fun verifyEmail(token: String) {
        when (state) {
            ANONYMOUS -> println("You must sign up before verifying email.")
            UNVERIFIED -> {
                println("Verifying email with token: $token.")
                state = AUTHENTICATED
            }
            AUTHENTICATED -> println("You are already verified.")
        }
    }

    fun viewContent() {
        when (state) {
            ANONYMOUS -> println("Viewing public content.")
            UNVERIFIED -> println("Viewing personalized content for unverified account.")
            AUTHENTICATED -> println("Viewing personalized content.")
        }
    }

    fun viewProfile() {
        when (state) {
            ANONYMOUS -> println("You must sign in to view your profile.")
            UNVERIFIED -> println("Profile: $email (Unverified account, please verify your email).")
            AUTHENTICATED -> println("Profile: $email (Fully authenticated).")
        }
    }

    fun editProfile(email: String) {
        when (state) {
            ANONYMOUS -> println("You must sign in to edit your profile.")
            UNVERIFIED -> println("Please verify your account before editing your profile.")
            AUTHENTICATED -> {
                println("Updated email from ${this.email} to $email.")
            }
        }
    }
}