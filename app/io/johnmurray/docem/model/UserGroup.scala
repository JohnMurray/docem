package io.johnmurray.docem.model

import play.api.libs.json.Json

/**
 * A group of users. This is mainly to make authorization on projects easier to manage for groups
 * that may manage more than one project. Saves time and reduces errors for adding or removing
 * users from your multiple projects.
 */
case class UserGroup(id: Long, name: String, owner: List[Long], users: List[Long])

object UserGroup{
  implicit val format = Json.format[UserGroup]
}
