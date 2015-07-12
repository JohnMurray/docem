package io.johnmurray.docem.repo.memory

import io.johnmurray.docem.model.{User, UserProfile}
import io.johnmurray.docem.repo.UserRepo

/**
 * An implementation of the user repository, but in memory. This is useful for testing or working in
 * a development mode where no DB is available.
 */
class MemoryUserRepo extends UserRepo {

  var memoryDb = Map.empty[Long, (User, UserProfile)]

  override def getAll(): List[(User, UserProfile)] = memoryDb.values.toList

  override def get(id: Long) = memoryDb.get(id)

  override def store(user: User, userProfile: UserProfile) = memoryDb += user.id -> (user, userProfile)

  override def edit(id: Long, user: User, userProfile: UserProfile) = memoryDb += id -> (user, userProfile)

  override def delete(id: Long): Unit = memoryDb -= id
}
