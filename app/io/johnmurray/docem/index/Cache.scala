package io.johnmurray.docem.index

import io.johnmurray.docem.model.{ProjectVersion, Project}

/**
 * Contains the cache for the current index.
 */
object Cache {

  /*
   * Maps the project name to the project entity
   */
  @volatile var project = Map.empty[String, Project]

  /*
   * Maps the project name to the map of version -> version object
   */
  @volatile var versions = Map.empty[String, Map[String, ProjectVersion]]

}
