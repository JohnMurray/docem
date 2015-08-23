package io.johnmurray.docem.repo.memory

import io.johnmurray.docem.model.UserGroup
import io.johnmurray.docem.repo.UserGroupRepo

/**
 * An implementation of the user group repository, but in memory. This is useful for testing or working in
 * a development mode where no DB is available.
 */
class MemoryUserGroupRepo extends UserGroupRepo {

  var memoryDb = Map.empty[Long, UserGroup]

  override def getAll: List[UserGroup] = memoryDb.values.toList

  override def get(id: Long) = memoryDb.get(id)

  override def store(userGroup: UserGroup) = memoryDb += userGroup.id -> userGroup

  override def edit(id: Long, userGroup: UserGroup) = memoryDb += id -> userGroup

  override def delete(id: Long): Unit = memoryDb -= id

}
