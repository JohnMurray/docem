package io.johnmurray.docem.model

import play.api.libs.json.Json

/**
 * Contains project information that we do not want to share with the outside world (mostly because
 * it is not important). This may include information such as location on disk.
 */
case class ProjectProfile(projectId: Long, rootDocDir: String)

object ProjectProfile {
  implicit val format = Json.format[ProjectProfile]
}
