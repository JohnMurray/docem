package io.johnmurray.docem.service

import io.johnmurray.docem.model.Project
import io.johnmurray.docem.repo.ProjectRepo

/**
 * Describes business logic you can perform on projects
 */
trait ProjectService {

  val repo: ProjectRepo

  def fetchAll: Seq[Project]

  def create(project: Project): Long

}
