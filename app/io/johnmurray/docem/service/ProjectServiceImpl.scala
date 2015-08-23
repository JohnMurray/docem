package io.johnmurray.docem.service

import io.johnmurray.docem.model.{ProjectVersion, Project}
import io.johnmurray.docem.repo.ProjectRepo
import scaldi.{Injector, Injectable}

/**
 * Implementation of [[ProjectRepo]]
 */
class ProjectServiceImpl(implicit inj: Injector) extends  ProjectService with Injectable {

  override val repo = inject [ProjectRepo]

  override def fetchAll: Seq[Project] = repo.getAll

  override def fetch(projectId: Long): Option[Project] = repo.get(projectId)

  override def create(project: Project): Long = {
    repo.store(project)
  }

  override def edit(id: Long, project: Project): Unit = {
    repo.edit(id, project)
  }

  override def fetchAllVersions(projectId: Long): Seq[ProjectVersion] = {
    repo.getVersionsForProject(projectId)
  }

  override def delete(projectId: Long): Unit = {
    val versions = repo.getVersionsForProject(projectId)
    versions.foreach(v => repo.deleteVersion(v.id))
    repo.delete(projectId)
  }
}
