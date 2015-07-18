package io.johnmurray.docem.controller.api

import io.johnmurray.docem.model.Project
import io.johnmurray.docem.repo.memory.MemoryProjectRepo
import io.johnmurray.docem.service.ProjectServiceImpl
import play.api.Logger
import play.api.libs.json.{JsSuccess, Json}
import play.api.mvc._

/**
 * API endpoint for Projects
 */
class ProjectController extends Controller {

  import io.johnmurray.docem.controller.api.ProjectController._

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

  // todo: use DI
  val service = new ProjectServiceImpl(new MemoryProjectRepo)

}
