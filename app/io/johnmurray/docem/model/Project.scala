package io.johnmurray.docem.model

import play.api.libs.json.Json

/**
 * Represents a grouping of documents. A project will have multiple versions defined
 * underneath of it.
 */
case class Project(id: Long, name: String, description: String, shortName: String, versionIds: List[Long], currentVersionId: Long)

object Project {
  implicit val format = Json.format[Project]
}
