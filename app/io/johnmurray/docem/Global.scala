package io.johnmurray.docem

import io.johnmurray.docem.model.ProjectVersion
import org.joda.time.DateTime
import play.api._
import play.api.mvc.Results._
import play.api.mvc.{RequestHeader, Result}

import scala.concurrent.Future

/**
 * Override some global hooks for the application
 */
object Global {

  lazy val app = Play.current
  lazy val projectDirectory : String = app.configuration.getString("docem.project-directory").get
  lazy val allowUnVersionedProjects : Boolean = app.configuration.getBoolean("docem.allow-unversioned-projects").get

  // some simple setup for testing
  import io.johnmurray.docem.index._
  Cache.project += "test" -> model.Project(Some(1L), "Test Project", "Some test project that does stuff", "test")
  Cache.versions += "test" -> Map(
    "0.0.1" -> ProjectVersion(1L, 1L, " 0.0.1", current = true, Some("Funky Walrus"), DateTime.now()),
    "0.0.0" -> ProjectVersion(2L, 1L, "0.0.0", current = false, Some("Overweight Chicken"), DateTime.now().minusDays(2)))

  Cache.project += "fg-framework" -> model.Project(Some(2L), "Fireglass Framework",
    """
      |A simple framework built on top of Play for building web-services within AppNexus. The "framework"
      |is mainly a collection of libraries aimed toward building AppNexus web services easier by integrating
      |with core services and conforming to company standards.
    """.stripMargin, "fg-framework")
  Cache.versions += "fg-framework" -> Map(
    "0.0.30" -> ProjectVersion(3L, 2L, "0.0.28", current = false, Some("Time Traveling Ninja"), DateTime.now),
    "0.0.29" -> ProjectVersion(4L, 2L, "0.0.29", current = true, Some("Super Insidious"), DateTime.now.minusDays(1)))


  // todo: refactor to use non-global implementation

  /**
   * Override the main handler for missing routes to display a custom 404 page. However in development
   * let's use the default mechanism.
   */
//  override def onHandlerNotFound(request: RequestHeader): Future[Result] = {
//    Future.successful(NotFound {
//      val mode = Play.maybeApplication.map(_.mode).getOrElse(Mode.Dev)
//      mode match {
//        case Mode.Prod => view.html.not_found()
//        case _ => views.html.defaultpages.devNotFound(request.method, request.uri, Play.maybeApplication.flatMap(_.routes))
//      }
//    })
//  }

  /**
   * Override the main handler for when something goes horribly wrong that we didn't expect. However if we're
   * in development mode then just dump out the error using the default mechanism.
   */
//  override def onError(request: RequestHeader, ex: Throwable): Future[Result] = {
//    val mode = Play.maybeApplication.map(_.mode).getOrElse(Mode.Dev)
//    mode match {
//      case Mode.Prod => Future.successful(InternalServerError(view.html.error_500()))
//      case _ => super.onError(request, ex)
//    }
//  }

}
