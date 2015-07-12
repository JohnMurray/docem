package io.johnmurray.docem.model

import org.joda.time.DateTime
import play.api.libs.json._

/**
 * A specific instance of a [[Project]] at a particular version
 */
case class ProjectVersion(id: Long, version: String, name: Option[String], date: DateTime)

object ProjectVersion {
  implicit val format = Json.format[ProjectVersion]
}