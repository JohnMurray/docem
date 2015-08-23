package io.johnmurray.docem.repo.memory

import io.johnmurray.docem.model.UserProfile
import io.johnmurray.docem.repo.UserProfileRepo

/**
 * An implementation of the userProfile repository, but in memory. This is useful for testing or working in
 * a development mode where no DB is available.
 */
class MemoryUserProfileRepo extends UserProfileRepo {

  private var _serial : Long = 0
  def nextId = this.synchronized {
    _serial += 1
    _serial
  }

  var memoryDb = Map.empty[Long, UserProfile]

  override def getAll: List[UserProfile] = memoryDb.values.toList

  override def get(id: Long): Option[UserProfile] = memoryDb.get(id)

  override def store(userId: Long, userProfile: UserProfile): Unit = {
    memoryDb += userId -> userProfile
  }

  override def edit(id: Long, userProfile: UserProfile) = memoryDb += id -> userProfile

  override def delete(id: Long): Unit = memoryDb -= id
}
