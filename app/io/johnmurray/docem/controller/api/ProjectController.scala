package io.johnmurray.docem.controller.api

import io.johnmurray.docem.model.Project
import io.johnmurray.docem.repo.ProjectRepo
import io.johnmurray.docem.service.ProjectService
import play.api.Logger
import play.api.libs.json.{JsSuccess, Json}
import play.api.mvc._
import scaldi.{Injectable, Injector}

/**
 * API endpoint for Projects
 */
class ProjectController(implicit inj: Injector) extends Controller with Injectable {

  val projectRepo = inject[ProjectRepo]
  val service = inject [ProjectService]

  def getAllProjects = Action {
    Ok(Json.toJson(service.fetchAll))
  }

  def createProject = Action(parse.tolerantJson) { request =>
    request.body.validate[Project] match {
      case JsSuccess(project, _) =>
        val projectId = service.create(project)
        Ok(Json.obj("id" -> projectId))
      case x =>
        Logger.debug(x.toString)
        BadRequest(Json.obj("error" -> "Could not parse the request"))
    }
  }

}

/**
 * ToDo: I would really like to inject these, but I need to figure out how to make them a singleton first since
 * I dont' want them created with each controller instance. Also probably need to make the controller a singleton
 * (maybe).
 */
object ProjectController {


}
