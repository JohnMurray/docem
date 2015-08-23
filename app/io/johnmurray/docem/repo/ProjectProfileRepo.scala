package io.johnmurray.docem.repo

import io.johnmurray.docem.model.ProjectProfile

/**
 * Repository layer for working with project profiles
 */
trait ProjectProfileRepo {

  def getAll: List[ProjectProfile]

  def get(projectId: Long): Option[ProjectProfile]

  def store(projectProfile: ProjectProfile): Unit

  def edit(projectId: Long, projectProfile: ProjectProfile): Unit

  def delete(projectId: Long): Unit

}

