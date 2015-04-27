package io.johnmurray.docem.model

import org.joda.time.DateTime

/**
 * A specific instance of a [[Project]] at a particular version
 */
case class ProjectVersion(version: String, name: String, date: DateTime)
