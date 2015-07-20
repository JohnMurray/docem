package io.johnmurray.docem.service

import io.johnmurray.docem.model.Project
import io.johnmurray.docem.repo.ProjectRepo
import scaldi.{Injector, Injectable}

/**
 * Implementation of [[ProjectRepo]]
 */
class ProjectServiceImpl(implicit inj: Injector) extends  ProjectService with Injectable {

  override val repo = inject [ProjectRepo]

  override def fetchAll: Seq[Project] = repo.getAll().map(_._1)

  override def create(project: Project): Long = {
    repo.store(project)
  }
}
