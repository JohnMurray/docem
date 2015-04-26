package io.johnmurray.docem.controller

import io.johnmurray.docem.index.Cache
import play.api._
import play.api.mvc._
import play.api.libs.json._

/**
 * Takes care of all search related items. This means both displaying the search page
 * as well as exposing the API to load the search items / results.
 */
class ProjectController extends Controller {

  def getAll = Action {
    Ok(Json.toJson(Cache.project.values))
  }

}
