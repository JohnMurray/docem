package io.johnmurray.docem.repo

import io.johnmurray.docem.model.UserProfile

/**
 * Repository layer for working with user groups
 */
trait UserProfileRepo {

  def getAll: List[UserProfile]

  def get(id: Long): Option[UserProfile]

  def store(userProfileId: Long, userProfile: UserProfile): Unit

  def edit(id: Long, userProfile: UserProfile): Unit

  def delete(id: Long): Unit

}

