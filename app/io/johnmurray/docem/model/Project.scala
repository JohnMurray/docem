package io.johnmurray.docem.model

/**
 * Represents a grouping of documents. A project will have multiple versions defined
 * underneath of it.
 */
case class Project(name: String, versions: Seq[ProjectVersion], basePath: String)
