package io.johnmurray.docem.module

import io.johnmurray.docem.service._
import scaldi.Module

/**
 * Defines simple binding for the repositories
 */
class ServiceModule extends Module {

  bind [ProjectService] to new ProjectServiceImpl

}
