import scalikejdbc._
import java.sql.SQLException
import ENVars._

case class User(name: String)

object DBInterface {
  // Initialize JDBC driver & connection pool
  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton(s"jdbc:postgresql://${ENVars.DB.dbHost}:${ENVars.DB.dbPort}/${ENVars.DB.dbName}", s"${ENVars.DB.dbUser}", s"${ENVars.DB.dbPass}")
  // Ad-hoc session provider on the REPL
  implicit val session: AutoSession.type = AutoSession

  def playerByName(name: String) : Option[User] =
  {
    try 
      {
        val users: List[User] = 
          sql"""SELECT * 
            FROM users 
            WHERE name=?""".bind(name)
          .map(rs => User(rs.string("name")))
          .list
          .apply()
        if (users.nonEmpty) Some(users(0)) else None
      }
    catch
      {
        case error: SQLException => 
          {
            println(s"[ERROR] Occured while finding user $name")
            None;
          }
      }
  }
  def addUser(user: User) : Option[Int] =
  {
    try
      {
        val result: Int = 
          sql"INSERT INTO users (name) VALUES (?)".bind(user.name).update.apply()
        Some(result)
      }
    catch
      {
        case e: SQLException =>
          {
            println(s"[ERROR] Occured while trying to INSERT user(${user.name})")
            None
          }
      }

  }
}
