package io.johnmurray.docem.repo.memory

import io.johnmurray.docem.model.{ProjectVersion, Project, ProjectProfile}
import io.johnmurray.docem.repo.ProjectRepo
import scala.collection.breakOut

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
  var profileMemoryDb = Map.empty[Long, ProjectProfile]
  var versionMemoryDb = Map.empty[Long, ProjectVersion]

  override def getAll(): List[(Project, Option[ProjectProfile])] = {
    projectMemoryDb.values.map(v => (v, v.id.flatMap(profileMemoryDb.get)))(breakOut)
  }

  override def get(id: Long) = {
    projectMemoryDb.get(id).map(p => p -> profileMemoryDb.get(p.id.get))
  }

  override def store(project: Project): Long = {
    val id = nextId
    val projectWithId = project.copy(id = Some(id))
    projectMemoryDb += id -> projectWithId
    id
  }

  override def store(projectProfile: ProjectProfile): Unit = {
    profileMemoryDb += projectProfile.projectId -> projectProfile
  }

  override def edit(id: Long, project: Project, projectProfile: Option[ProjectProfile]) = {
    projectMemoryDb += id -> project
    projectProfile.foreach(p => profileMemoryDb += p.projectId -> p)
  }

  override def delete(id: Long): Unit = projectMemoryDb -= id
  

  override def getVersions(ids: List[Long]): List[ProjectVersion] = versionMemoryDb.values.filter(v => ids.contains(v.id)).toList

  override def storeVersion(version: ProjectVersion) = versionMemoryDb += version.id -> version

  override def edit(id: Long, version: ProjectVersion) = versionMemoryDb += id -> version

  override def deleteVersion(id: Long) = versionMemoryDb -= id

  override def getVersion(id: Long): Option[ProjectVersion] = versionMemoryDb.get(id)
}
