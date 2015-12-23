package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json
import scala.util.Try
import scala.collection.mutable._
import conf.FeaturedImp._
import models._


class GetFeatured extends Controller {

  def getFeatured(preferredLanguage: String, app: String, country: String, state: String) = Action {

    //val repo = new repositories.Featured() EDU esta usando patron repositories
    val data = new data();
    val seeds = data.getSeeds(preferredLanguage, app, country, state)
    val body = new Body(seeds.size, seeds)
    val res = new Response(true, "OK", 0, body)
    //println(res.toString)

    val try_json: Try[JsValue] = Try(Json.toJson(res))
    val jmap = try_json.getOrElse(Json.parse("""{"error":"parse error"}"""))
    Ok(jmap)
  }
}

