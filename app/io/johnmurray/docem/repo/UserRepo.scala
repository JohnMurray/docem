package io.johnmurray.docem.repo

import io.johnmurray.docem.model.{UserProfile, User}

/**
 * Repository layer for working with users
 */
trait UserRepo {

  def getAll(): List[(User, UserProfile)]

  def get(id: Long): Option[(User, UserProfile)]

  def store(user: User, userProfile: UserProfile): Unit

  def edit(id: Long, user: User, userProfile: UserProfile): Unit

  def delete(id: Long): Unit

}
