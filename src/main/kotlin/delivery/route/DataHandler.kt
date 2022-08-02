package delivery.route

import delivery.Service.DataService
import delivery.models.Order
import delivery.models.OrderList
import delivery.models.Product
import delivery.models.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.Data(dataService: DataService){
    route("user"){
        get {
            call.respond(dataService.getUser())
            call.response.status(HttpStatusCode.OK)

        }

        post {
            val data = call.receive<User>()
            call.respond(dataService.addUser(data))
            call.response.status(HttpStatusCode.OK)

        }

    }
    route("product"){
        get {
            call.respond(dataService.getProduct())
            call.response.status(HttpStatusCode.OK)

        }

        post {
            val data = call.receive<Product>()
            call.respond(dataService.addProduct(data))
            call.response.status(HttpStatusCode.OK)

        }

    }
    route("order"){
        get {
            call.respond(dataService.getOrder())
            call.response.status(HttpStatusCode.OK)

        }

        post {
            val data = call.receive<Order>()
            call.respond(dataService.addOrder(data))
            call.response.status(HttpStatusCode.OK)

        }

    }
    route("orderlist"){
        get {
            call.respond(dataService.getOrderList())
            call.response.status(HttpStatusCode.OK)

        }

        post {
            val data = call.receive<OrderList>()
            call.respond(dataService.addOrderList(data))
            call.response.status(HttpStatusCode.OK)

        }

    }
}