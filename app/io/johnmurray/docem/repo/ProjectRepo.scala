package io.johnmurray.docem.repo

import io.johnmurray.docem.model.Project

/**
 * Repository layer for working with projects
 */
trait ProjectRepo {

  def getAll(): List[Project]

  def store(project: Project): Unit

  def edit(id: Long, project: Project): Unit

  def delete(id: Long): Unit

}
