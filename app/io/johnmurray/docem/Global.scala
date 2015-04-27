package io.johnmurray.docem

import java.io.File

import io.johnmurray.docem.model.ProjectVersion
import org.joda.time.DateTime
import play.api._
import play.api.mvc.Results._
import play.api.mvc.{Result, RequestHeader}

import scala.concurrent.Future

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
    Cache.versions += "test" -> Map(
      "0.0.1" -> ProjectVersion("0.0.1", "Funky Walrus", DateTime.now()),
      "0.0.0" -> ProjectVersion("0.0.0", "Overweight Chicken", DateTime.now().minusDays(2)))

    Cache.project += "fg-framework" -> model.Project("Fireglass Framework",
      """
        |A simple framework built on top of Play for building web-services within AppNexus. The "framework"
        |is mainly a collection of libraries aimed toward building AppNexus web services easier by integrating
        |with core services and conforming to company standards.
      """.stripMargin, "fg-framework", "0.0.30")
    Cache.versions += "fg-framework" -> Map(
      "0.0.30" -> ProjectVersion("0.0.30", "Time Traveling Ninja", DateTime.now),
      "0.0.29" -> ProjectVersion("0.0.29", "Super Insedious", DateTime.now.minusDays(1)))

  }

  /**
   * Override the main handler for missing routes to display a custom 404 page. However in development
   * let's use the default mechanism.
   */
  override def onHandlerNotFound(request: RequestHeader): Future[Result] = {
    Future.successful(NotFound {
      val mode = Play.maybeApplication.map(_.mode).getOrElse(Mode.Dev)
      mode match {
        case Mode.Prod => view.html.not_found()
        case _ => views.html.defaultpages.devNotFound.f(request, Play.maybeApplication.flatMap(_.routes))
      }
    })
  }

  /**
   * Override the main handler for when something goes horribly wrong that we didn't expect. However if we're
   * in development mode then just dump out the error using the default mechanism.
   */
  override def onError(request: RequestHeader, ex: Throwable): Future[Result] = {
    val mode = Play.maybeApplication.map(_.mode).getOrElse(Mode.Dev)
    mode match {
      case Mode.Prod => Future.successful(InternalServerError(view.html.error_500()))
      case _ => super.onError(request, ex)
    }
  }

}
