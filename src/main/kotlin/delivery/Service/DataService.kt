package delivery.Service

import DBsettings.dbQuery
import delivery.models.*
import delivery.models.Orders.customer
import delivery.models.Orders.orderDate
import delivery.models.Orders.totalCost
import delivery.models.Products.amount
import delivery.models.Products.description
import delivery.models.Products.price
import delivery.models.Products.title
import delivery.models.Users.address
import delivery.models.Users.city
import delivery.models.Users.firstName
import delivery.models.Users.lastName
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonArray
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDateTime

class DataService {
    suspend fun getUser(): List<User?> = dbQuery {
        Users.selectAll().map { toUser(it) }
    }

    suspend fun addUser(user: User) {
        dbQuery {
            Users.insert {
                it[Id] = user.id!!
                it[firstName] = user.firstName
                it[lastName] = user.lastname
                it[address] = user.address
                it[city] = user.city
            }
        }
    }

    suspend fun getProduct(): List<Product?> = dbQuery {
        Products.selectAll().map { toProduct(it) }
    }

    suspend fun addProduct(item: Product) {
        dbQuery {
            Products.insert {
                it[Id] = item.id!!
                it[title] = item.title
                it[description] = item.description
                it[amount] = item.amount
                it[price] = item.price
            }
        }
    }

    suspend fun  getOrder():List<Order?> = dbQuery {
        Orders.selectAll().map { toOrder(it) }
    }

    suspend fun addOrder(order: Order) {
        dbQuery {
            Orders.insert {
                it[Id] = order.id!!
                it[customer] = order.customerId!!
                it[totalCost] = order.totalCost
                it[orderDate] = LocalDateTime.now()
            }
        }
    }

    suspend fun  getOrderList():List<OrderList?> = dbQuery {
        OrderLists.selectAll().map { toOrderList(it) }
    }

    suspend fun addOrderList(orderList: OrderList) {
        dbQuery {
            OrderLists.insert {
                it[Id] = orderList.id!!
                it[orderId] = orderList.orderId!!
                it[productID] = orderList.products!!
            }
        }
    }


}
