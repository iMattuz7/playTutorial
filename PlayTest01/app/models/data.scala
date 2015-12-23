package models
import scala.collection.mutable._
import play.Logger

class data {
  private[this] var s = List[Seed]();
  def seeds: List[Seed] = s;
  def seeds_= (ss: List[Seed]) {this.s = ss}
  
  def getSeeds(preferredLanguage:String, AppType:String, country: String, State: String): MutableList[Seed]  ={
    
    
      try {
      
      
      var lstSeed = new MutableList();
      val conn = MSSql.getConnection()
      val statement = conn.createStatement()
      val res = statement.executeQuery("exec GetFeaturedSeeds @Idioma='" + preferredLanguage + "',  @AppType=" + AppType + ", @Country='"+ country +"'"+ ", @State='"+ State +"'")
      
      
     while ( res.next() ) {
        
        var name = res.getString("Title")
        var seedtypeid =  res.getInt("Musicentitytypeid")
        var seedid = res.getInt("Seedid")
        var description = res.getString("Descripcion")
        var img170 = res.getString("image170")
        var img500 = res.getString("image500")
        var cover = new Cover(img170, img500)
        var obj = new Seed(name, seedtypeid,seedid, description,cover)
        lstSeed += obj
     }
      
     
      return seeds
    } catch {
      case t: Throwable => t.printStackTrace() 
      Logger.error("addSearchEventRelation:"+t.toString())
      return null
    }
    
    
    
    
  }


}