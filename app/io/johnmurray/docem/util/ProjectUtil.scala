package io.johnmurray.docem.util

import java.io.File

import io.johnmurray.docem.model.Project

/**
 * Helps with building and working with [[io.johnmurray.docem.model.Project]] models
 */
object ProjectUtil {

  def buildFromMeta(metaFile: File): Project = {
    // todo: implement method
    Project("name")
  }

}
