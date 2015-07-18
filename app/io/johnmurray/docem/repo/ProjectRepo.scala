package io.johnmurray.docem.repo

import io.johnmurray.docem.model.{ProjectVersion, ProjectProfile, Project}

/**
 * Repository layer for working with projects and project-profiles
 */
trait ProjectRepo {

  def getAll(): List[(Project, Option[ProjectProfile])]

  def get(id: Long): Option[(Project, Option[ProjectProfile])]

  def store(project: Project): Long

  def store(projectProfile: ProjectProfile): Unit

  def edit(id: Long, project: Project, projectProfile: Option[ProjectProfile]): Unit

  def delete(id: Long): Unit



  def getVersions(ids: List[Long]): List[ProjectVersion]

  def getVersion(id: Long): Option[ProjectVersion]

  def storeVersion(version: ProjectVersion): Unit

  def edit(id: Long, version: ProjectVersion): Unit

  def deleteVersion(id: Long): Unit

}
