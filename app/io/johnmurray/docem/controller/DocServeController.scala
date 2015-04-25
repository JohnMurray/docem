package io.johnmurray.docem.controller

import java.io.File
import java.nio.file.Paths

import io.johnmurray.docem.Global
import play.api._
import play.api.mvc._

import scala.io.Source

/**
 * Takes care of serving the documentation files. Mainly this is just
 * serving various static content from disk.
 */
class DocServeController extends Controller {

  val logger = Logger(this.getClass)

  /**
   * Serve a static file by returning the contents of that file withthe proper (I hope)
   * mime type
   */
  def serveStaticFile(path: String) = Action {
    val fileOnDisk = Paths.get(Global.projectDirectory, path).toFile
    logger.info(s"project dir: ${Global.projectDirectory}")
    logger.info(s"path: $path")
    logger.info("absolute path: " + fileOnDisk.getAbsolutePath)
    if (fileOnDisk.exists()) {
      renderFile(fileOnDisk) match {
        case (content, mimeType) => Ok(content).as(mimeType)
      }
    } else {
      NotFound("couldn't find what you were looking for")
    }
  }


  /**
   * Very simple method to return the content + mime-tpye to render the static file
   *
   * Also handles the case of a directory as the path and checks for index.html
   */
  def renderFile(file: File): (String, String) = {
    if (file.isDirectory) {
      val index = new File(file, "index.html")
      if (index.exists() && index.isFile) {
        Source.fromFile(index).mkString -> "text/html"
      } else {
        "whoops, can't find what you're looking for" -> "text/plain"
      }
    } else {
      val content = Source.fromFile(file).mkString
      file.getName match {
        case c if c.endsWith(".html") => content -> "text/html"
        case c if c.endsWith(".js") => content -> "text/javascript"
        case c if c.endsWith(".css") => content -> "text/css"
        case c => content -> "text/plain"
      }
    }
  }

}
