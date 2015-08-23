package io.johnmurray.docem.repo.memory

import io.johnmurray.docem.model.{ProjectVersion, Project}
import io.johnmurray.docem.repo.ProjectRepo

/**
 * An implementation of the project repository, but in memory. This is useful for testing or working in
 * a development mode where no DB is available.
 */
class MemoryProjectRepo extends ProjectRepo {

  private var _serial : Long = 0
  def nextId = this.synchronized {
    _serial += 1
    _serial
  }

  var projectMemoryDb = Map.empty[Long, Project]
  var versionMemoryDb = Map.empty[Long, ProjectVersion]

  override def getAll: List[Project] = projectMemoryDb.values.toList

  override def get(id: Long): Option[Project] = projectMemoryDb.get(id)

  override def store(project: Project): Long = {
    val id = nextId
    val projectWithId = project.copy(id = Some(id))
    projectMemoryDb += id -> projectWithId
    id
  }

  override def edit(id: Long, project: Project) = projectMemoryDb += id -> project

  override def delete(id: Long): Unit = projectMemoryDb -= id
  

  override def getVersions(ids: List[Long]): List[ProjectVersion] = versionMemoryDb.values.filter(v => ids.contains(v.id)).toList

  override def storeVersion(version: ProjectVersion) = versionMemoryDb += version.id -> version

  override def edit(id: Long, version: ProjectVersion) = versionMemoryDb += id -> version

  override def deleteVersion(id: Long) = versionMemoryDb -= id

  override def getVersion(id: Long): Option[ProjectVersion] = versionMemoryDb.get(id)

  override def getVersionsForProject(projectId: Long): List[ProjectVersion] = {
    versionMemoryDb.values.filter(_.projectId == projectId).toList
  }
}
