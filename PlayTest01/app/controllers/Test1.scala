package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._


case class Msj(msj:String, version:String)



class Test1 extends Controller  {
  
  def hello()= Action { Ok(Json.parse("""{"msj":"hola"}"""))}
  

  
  def hello2(msj:String, version:String)= Action { 
    val m = new Msj(msj,version)
    val res = Json.toJson(m)
    Ok(res)
    
  
  }
  
  
implicit val msjWrite: Writes[Msj] = (
  (JsPath \ "msj").write[String] and
  (JsPath \ "version").write[String]
)(unlift(Msj.unapply))
  
}

