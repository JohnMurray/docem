package io.johnmurray.docem.repo.memory

import io.johnmurray.docem.model.ProjectProfile
import io.johnmurray.docem.repo.ProjectProfileRepo

/**
 * An implementation of the project profile repository, but in memory. This is useful for testing or working in
 * a development mode where no DB is available.
 */
class MemoryProjectProfileRepo extends ProjectProfileRepo {

  var memoryDb = Map.empty[Long, ProjectProfile]

  override def getAll: List[ProjectProfile] = memoryDb.values.toList

  override def get(projectId: Long) = memoryDb.get(projectId)

  override def store(projectProfile: ProjectProfile) = memoryDb += projectProfile.projectId -> projectProfile

  override def edit(projectId: Long, projectProfile: ProjectProfile) = memoryDb += projectId -> projectProfile

  override def delete(projectId: Long): Unit = memoryDb -= projectId

}
