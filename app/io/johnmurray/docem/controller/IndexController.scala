package io.johnmurray.docem.controller

import play.api._
import play.api.mvc._

import io.johnmurray.docem.view
import io.johnmurray.docem.index

/**
 * The main landing page. This is the typical "web-view" style controller
 */
class IndexController extends Controller {

  def mainPage = Action {
    Ok(view.html.index(index.Cache.project.values.toList))
  }

  def projectPage(project: String) = Action {
    index.Cache.versions.get(project).map({vs =>
      Ok("found it")
    }).getOrElse(NotFound(view.html.not_found()))
  }

}
