package io.johnmurray.docem

import java.io.File

import play.api._

/**
 * Override some global hooks for the application
 */
object Global extends GlobalSettings {

  var projectDirectory : String = ""
  var allowUnVersionedProjects : Boolean = false

  override def onStart(app: Application): Unit = {
    projectDirectory = app.configuration.getString("docem.project-directory").get
    allowUnVersionedProjects = app.configuration.getBoolean("docem.allow-unversioned-projects").get
  }
}
