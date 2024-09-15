import scalikejdbc._
import java.sql.SQLException
import ENVars._
import java.math.BigInteger
import gameClasses.Deposit
import gameClasses.Withdraw

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
  def getDepositsByUserID(user_tg_ID: Long) : Option[List[Deposit]] =
  {
    try
      {
        val result = 
          sql"""SELECT *
          FROM deposits
          WHERE (tg_id = ? and active = true)""".bind(user_tg_ID).map(rs => Deposit(rs.bigInt("transaction_id"), rs.boolean("active"), rs.bigInt("tg_id"), rs.bigInt("amount"), rs.bigInt("time_stamp"), rs.string("jetton_signature"), rs.string("commentary")))
          .list
          .apply()
        Some(result)
      }
    catch
      {
        case e: SQLException =>
          {
            println(s"[ERROR] Occured while trying to find user's deposits(${user_tg_ID})")
            None
          }
      }
  }
  def closeDepositByTransactionID(transaction_id: BigInteger) =
  {
    try
      {
        val result: Int = 
          sql"""UPDATE deposits
          SET active = false
          WHERE transaction_id = ?;""".bind(transaction_id).update.apply()
        Some(result)
      }
    catch
      {
        case e: SQLException =>
          {
            println(s"[ERROR] Occured while trying to deactivate transaction(${transaction_id})")
            None
          }
      }
  }
  def checkWithdrawByInfo(transaction_id: Long): Option[List[Withdraw]] =
  {
    try
      {
        val result: List[Withdraw] = 
          sql"""SELECT *
          FROM withdraws
          WHERE transaction_id = ?""".bind(BigInteger.valueOf(transaction_id)).
          map(rs => Withdraw(rs.bigInt("transaction_id"), rs.short("status"), rs.bigInt("tg_id"), rs.string("wallet"), rs.bigInt("amount"), rs.bigInt("time_stamp")))
          .list
          .apply()
        Some(result)
      }
    catch
      {
        case e: SQLException =>
          {
            println(s"[ERROR] Occured while trying to check withdraw transaction(${transaction_id})")
            None
          }
      }
  }
  def registerWithdraw(witdraw: Withdraw) : Option[Int] =
  {
    try
      {
        val result: Int = 
          sql"INSERT INTO withdraws (transaction_id, status, tg_id, wallet, amount, time_stamp) VALUES (?,?,?,?,?,?)".bind(witdraw.transaction_id, witdraw.status, witdraw.tg_id, witdraw.wallet, witdraw.amount, witdraw.time_stamp).update.apply()
        Some(result)
      }
    catch
      {
        case e: SQLException =>
          {
            println(s"[ERROR] Occured while trying to INSERT withdraw(${witdraw.transaction_id}, ${witdraw.status}, ${witdraw.tg_id}, ${witdraw.wallet}, ${witdraw.amount}, ${witdraw.time_stamp})")
            None
          }
      }
  }
}
