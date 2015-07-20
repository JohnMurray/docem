package io.johnmurray.docem.module

import io.johnmurray.docem.repo.memory._
import io.johnmurray.docem.repo._
import scaldi.Module

/**
 * Defines simple binding for the repositories
 */
class RepoModule extends Module {

  bind [ProjectRepo]   to new MemoryProjectRepo
  bind [UserGroupRepo] to new MemoryUserGroupRepo
  bind [UserRepo]      to new MemoryUserRepo

}
