package delivery.plugins

import delivery.Service.DataService
import delivery.models.User
import delivery.route.Data
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    val dataService = DataService()

    routing {
        get("/") {
        }
        Data(dataService)
    }
}
