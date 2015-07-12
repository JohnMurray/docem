package io.johnmurray.docem.repo

import io.johnmurray.docem.model.UserGroup

/**
 * Repository layer for working with user groups
 */
trait UserGroupRepo {

  def getAll(): List[UserGroup]

  def get(id: Long): Option[UserGroup]

  def store(userGroup: UserGroup): Unit

  def edit(id: Long, userGroup: UserGroup): Unit

  def delete(id: Long): Unit

}

