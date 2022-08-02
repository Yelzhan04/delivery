import com.typesafe.config.ConfigFactory
import delivery.models.OrderLists
import delivery.models.Orders
import delivery.models.Products
import delivery.models.Users
import io.ktor.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object DBsettings {
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private var dbUrl = appConfig.property("jdbc.url").getString()
    private var dbUser = appConfig.property("jdbc.username").getString()
    private var dbPassword = appConfig.property("jdbc.password").getString()

    fun init(dbUrl: String, dbUser: String, dbPassword: String) {
        DBsettings.dbUrl = dbUrl
        DBsettings.dbUser = dbUser
        DBsettings.dbPassword = dbPassword
        pgConnection()
        transaction {
            SchemaUtils.create(Users,Products,Orders,OrderLists)
            addLogger(StdOutSqlLogger)
        }
    }
    private fun pgConnection() = Database.connect(
            url = dbUrl,
            driver = "org.postgresql.Driver",
            user = dbUser,
            password = dbPassword
        )
    suspend fun <T> dbQuery(block: () -> T): T =
            withContext(Dispatchers.IO){
                transaction {
                    block()
                }
            }


}