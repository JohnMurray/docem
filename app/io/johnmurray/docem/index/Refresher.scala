package io.johnmurray.docem.index

import java.io.File

import akka.actor._

import io.johnmurray.docem.Global
import io.johnmurray.docem.model.{ProjectVersion, Project}

import scala.concurrent.duration._

/**
 * Responsible for refreshing the search-index on disk
 */
class Refresher extends Actor {

  import Refresher._

  val logger = play.api.Logger(this.getClass)
  implicit val ec = context.dispatcher

  var shallowScanSchedule : Cancellable = _

  override def preStart(): Unit = {
    shallowScanSchedule = context.system.scheduler.schedule(0.seconds, 5.minutes, self, ShallowScan)
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    shallowScanSchedule.cancel()
  }

  def receive = {
    case ShallowScan =>
      val projectRoot = new File(Global.projectDirectory)
      walkDir(projectRoot, f => {

      })

    case NewFolder(project, version) =>
      // todo: implement

    case unknown => unhandled(unknown)
  }


  def walkDir[T](dir: File, func: File => T): Seq[T] = dir.listFiles.map(func)

}

object Refresher {
  /*
   * A message to indicate to the actor to do a periodic scan and look for stuff that
   * might have changed due to any sort of external process or possibly by another doc-server
   * instance (if using a shared FS).
   *
   * Note that only the top version-level directories are scanned (thus shallow-scan). This
   * is so things don't get crazy slow or something like that.
   */
  case object ShallowScan

  /*
   * A message to scan a specific folder for a specific project
   */
  case class NewFolder(project: Project, version: ProjectVersion)
}
