package io.johnmurray.docem.repo.memory

import io.johnmurray.docem.model.{ProjectVersion, Project, ProjectProfile}
import io.johnmurray.docem.repo.ProjectRepo

/**
 * An implementation of the project repository, but in memory. This is useful for testing or working in
 * a development mode where no DB is available.
 */
class MemoryProjectRepo extends ProjectRepo {

  var projectMemoryDb = Map.empty[Long, (Project, ProjectProfile)]
  var versionMemoryDb = Map.empty[Long, ProjectVersion]

  override def getAll(): List[(Project, ProjectProfile)] = projectMemoryDb.values.toList

  override def get(id: Long) = projectMemoryDb.get(id)

  override def store(project: Project, projectProfile: ProjectProfile) = projectMemoryDb += project.id -> (project, projectProfile)

  override def edit(id: Long, project: Project, projectProfile: ProjectProfile) = projectMemoryDb += id -> (project, projectProfile)

  override def delete(id: Long): Unit = projectMemoryDb -= id
  

  override def getVersions(ids: List[Long]): List[ProjectVersion] = versionMemoryDb.values.filter(v => ids.contains(v.id)).toList

  override def storeVersion(version: ProjectVersion) = versionMemoryDb += version.id -> version

  override def edit(id: Long, version: ProjectVersion) = versionMemoryDb += id -> version

  override def deleteVersion(id: Long) = versionMemoryDb -= id

  override def getVersion(id: Long): Option[ProjectVersion] = versionMemoryDb.get(id)
}
