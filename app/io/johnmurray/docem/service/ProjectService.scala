package io.johnmurray.docem.service

import io.johnmurray.docem.model.{ProjectVersion, Project}
import io.johnmurray.docem.repo.ProjectRepo

/**
 * Describes business logic you can perform on projects
 */
trait ProjectService {

  val repo: ProjectRepo

  def fetchAll: Seq[Project]

  def fetch(projectId: Long): Option[Project]

  def create(project: Project): Long

  def edit(id: Long, project: Project): Unit

  def fetchAllVersions(projectId: Long): Seq[ProjectVersion]

  def delete(projectId: Long): Unit

}
