package io.johnmurray.docem.repo

import io.johnmurray.docem.model.{ProjectVersion, ProjectProfile, Project}

/**
 * Repository layer for working with projects and project-profiles
 */
trait ProjectRepo {

  def getAll: List[Project]

  def get(id: Long): Option[Project]

  def store(project: Project): Long

  def edit(id: Long, project: Project): Unit

  def delete(id: Long): Unit



  def getVersions(ids: List[Long]): List[ProjectVersion]

  def getVersion(id: Long): Option[ProjectVersion]

  def getVersionsForProject(projectId: Long): List[ProjectVersion]

  def storeVersion(version: ProjectVersion): Unit

  def edit(id: Long, version: ProjectVersion): Unit

  def deleteVersion(id: Long): Unit

}
