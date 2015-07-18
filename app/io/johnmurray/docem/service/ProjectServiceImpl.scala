package io.johnmurray.docem.service

import io.johnmurray.docem.model.Project
import io.johnmurray.docem.repo.ProjectRepo

/**
 * Implementation of [[ProjectRepo]]
 */
class ProjectServiceImpl(override val repo: ProjectRepo) extends  ProjectService {

  override def fetchAll: Seq[Project] = repo.getAll().map(_._1)

  override def create(project: Project): Long = {
    repo.store(project)
  }
}
