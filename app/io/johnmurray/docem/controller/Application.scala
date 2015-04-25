package io.johnmurray.docem.controller

import play.api._
import play.api.mvc._
import io.johnmurray.docem.view

class Application extends Controller {

  def index = Action {
    Ok(view.html.index("Your new application is ready."))
  }

}