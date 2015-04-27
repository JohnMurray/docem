package io.johnmurray.docem

import java.io.File

import io.johnmurray.docem.model.ProjectVersion
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

    // some simple setup for testing
    import io.johnmurray.docem.index._
    Cache.project += "test" -> model.Project("Test Project", "Some test project that does stuff", "test", "0.0.1")
    Cache.versions += "test" -> Map("0.0.1" -> ProjectVersion("0.0.1"), "0.0.0" -> ProjectVersion("0.0.0"))

    Cache.project += "fireglass" -> model.Project("Fireglass Framework",
      """
        |A simple framework built on top of Play for building web-services within AppNexus. The "framework"
        |is mainly a collection of libraries aimed toward building AppNexus web services easier by integrating
        |with core services and conforming to company standards.
      """.stripMargin, "fg-framework", "0.0.30")
    Cache.versions += "fg-framework" -> Map("0.0.30" -> ProjectVersion("0.0.30"), "0.0.29" -> ProjectVersion("0.0.29"))

  }
}
