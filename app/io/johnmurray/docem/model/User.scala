package io.johnmurray.docem.model

import play.api.libs.json.Json

/**
 * A registered / authenticated user. These types of users are mainly for CRUD operations on
 * projects and their versions. It's also used as a security mechanism for projects to allow
 * access to multiple people to edit a project.
 */
case class User(id: Long, email: String, firstName: String, lastName: String)

object User {
  implicit val format = Json.format[User]
}
