package io.johnmurray.docem.repo

import io.johnmurray.docem.model.Project

/**
 * DESCRIPTION
 */
class MemoryProjectRepo extends ProjectRepo {

  var projects = Map.empty[Long, Project]

  override def getAll(): List[Project] = projects.values.toList

  override def store(project: Project): Unit = projects += project.id -> project

  override def edit(id: Long, project: Project): Unit = projects += id -> project

  override def delete(id: Long): Unit = projects -= id
}
