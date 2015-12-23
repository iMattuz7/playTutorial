package models
import java.sql.Connection
import play.Play

object MSSql {
   private var con : Connection = _
  
  def connect(){
    

    // Set the driver
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    val connectionUrl =  play.Play.application().configuration().getString("sql.server")
    con = java.sql.DriverManager.getConnection(connectionUrl)
  } 
  
  def getConnection(): Connection ={
    if (con == null || con.isClosed()) 
      connect()
    con
  }
  
  def close(){
    if(con != null)
       con.close()
  }  
}