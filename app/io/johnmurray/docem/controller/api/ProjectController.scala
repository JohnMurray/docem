package io.johnmurray.docem.controller.api

import io.johnmurray.docem.model.Project
import io.johnmurray.docem.repo.ProjectRepo
import io.johnmurray.docem.service.ProjectService
import play.api.Logger
import play.api.libs.json.{JsSuccess, Json}
import play.api.mvc._
import scaldi.{Injectable, Injector}

/**
 * API endpoint for managing Projects
 */
class ProjectController(implicit inj: Injector) extends Controller with Injectable {

  //val projectRepo = inject[ProjectRepo]
  val service = inject [ProjectService]

  def getAllProjects = Action {
    Ok(Json.toJson(service.fetchAll))
  }

  def createProject = Action(parse.tolerantJson) { request =>
    request.body.validate[Project] match {
      case JsSuccess(project, _) =>
        val projectId = service.create(project)
        Ok(Json.obj("id" -> projectId))
      case error =>
        Logger.debug(error.toString)
        BadRequest(Json.obj("error" -> "Could not parse the request. Please provide all required fields."))
    }
  }


  def editProject(id: Long) = Action(parse.tolerantJson) { request =>
    request.body.validate[Project] match {
      case JsSuccess(project, _) =>
        service.edit(id, project)
        service.fetch(id) match {
          case Some(p) => Ok(Json.toJson(p))
          case _       => BadRequest(Json.obj("error" -> s"Could not find project by id '$id' to update"))
        }
      case error =>
        Logger.debug(error.toString)
        BadRequest(Json.obj("error" -> "Could not parse the request. Please provide all required fields."))
    }
  }


  def deleteProject(id: Long) = Action {
    service.delete(id)
    Ok("")
  }

}
