package io.johnmurray.docem.util

import java.io.File

import io.johnmurray.docem.model.ProjectVersion

/**
 * Helps with building and working with [[io.johnmurray.docem.model.ProjectVersion]] models
 */
object ProjectVersionUtil {

  def buildFromMeta(metaFile: File): ProjectVersion = {
    // todo: implement method
    ProjectVersion("version", "basePath")
  }

}
