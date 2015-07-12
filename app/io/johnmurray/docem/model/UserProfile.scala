package io.johnmurray.docem.model

import play.api.libs.json.Json

/**
 * Contains user information that we do not want to share with the outside world. This could be
 * things like passwords, activity logs, etc.
 */
case class UserProfile(userId: Long, password: String, apiUser: Boolean)

object UserProfile {
  implicit val format = Json.format[UserProfile]
}

