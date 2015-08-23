package io.johnmurray.docem.controller

import io.johnmurray.docem.model.{ProjectVersion, Project}
import io.johnmurray.docem.service.ProjectService
import play.api._
import play.api.mvc._

import io.johnmurray.docem.view
import io.johnmurray.docem.index
import scaldi.{Injector, Injectable}

import scala.collection.breakOut

/**
 * The main landing page. This is the typical "web-view" style controller
 */
class IndexController(implicit inj: Injector) extends Controller with Injectable {

  val service = inject[ProjectService]

  def mainPage = Action {
    val projects: List[(Project, Option[ProjectVersion])] = service.fetchAll.map(p =>
      p -> service.fetchAllVersions(p.id.get).find(p => p.current)
    )(breakOut)
    service.fetchAll.map(p => p.id)
    Ok(view.html.index(projects))
  }

  def projectPage(project: String) = Action {
    (
      for {
        _project <- index.Cache.project.get(project)
        versions <- index.Cache.versions.get(project)

      } yield Ok(view.html.all_versions(_project.name, project, versions))
    ).getOrElse {
      NotFound(view.html.not_found())
    }
  }

}
