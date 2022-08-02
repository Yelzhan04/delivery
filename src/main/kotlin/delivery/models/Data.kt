package delivery.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@Serializable
data class User(

    val id:Long? = null,
    val firstName:String? = null,
    val lastname:String? = null,
    val address:String? = null,
    val city:String? = null

)

object Users:Table("users"){

    val Id:Column<Long> = long( "id").autoIncrement()
    val firstName:Column<String?> = varchar("first_name",50).nullable()
    val lastName:Column<String?> = varchar("last_name",50).nullable()
    val address:Column<String?> = varchar("address",50).nullable()
    val city:Column<String?> = varchar("city",50).nullable()
    override val primaryKey = PrimaryKey(Id, name = "PK_User_Id")

}

fun toUser(row:ResultRow):User =
    User(
        id = row[Users.Id],
        firstName = row[Users.firstName],
        lastname = row[Users.lastName],
        address = row[Users.address],
        city = row[Users.city]
    )

@Serializable
data class Product(
    val id:Long? = null,
    val title:String? = null,
    val description:String? = null,
    val amount:Int? = null,
    val price:Double? = null,

    )

object Products:Table("products"){

    val Id: Column<Long> = long("id").autoIncrement()
    val title:Column<String?> = varchar("title",50).nullable()
    val description: Column<String?> = text("description").nullable()
    val amount: Column<Int?> = integer("amount").nullable()
    val price:Column<Double?> = double("price").nullable()
    override val primaryKey = PrimaryKey(Id, name = "PK_Product_Id")

}

fun toProduct(row:ResultRow):Product =
    Product(
        id = row[Products.Id],
        title = row[Products.title],
        description = row[Products.description],
        amount = row[Products.amount],
        price = row[Products.price]

    )
@Serializable
data class OrderList(
    val id:Long? = null,
    val orderId:Long? = null,
    val products:Long? = null

)

object OrderLists: Table("order_items"){

    val Id: Column<Long> = long("id").autoIncrement()
    val orderId = reference("order_id",Orders.Id)
    val productID = reference("products",Products.Id)
    override val primaryKey = PrimaryKey(Products.Id, name = "PK_OrderList_Id")

}

fun toOrderList(row: ResultRow):OrderList =
    OrderList(
        id = row[OrderLists.Id],
        orderId = row[OrderLists.orderId],
        products = row[OrderLists.productID]
    )

@Serializable
data class Order(
    val id: Long? = null,
    val customerId:Long? = null,
    val totalCost:Double? = null,
    val orderDate:String? = null

)

object Orders:Table("orders"){

    val Id: Column<Long> = long("id").autoIncrement()
    val customer = reference("customer",Users.Id)
    val totalCost:Column<Double?> = double("total_cost").nullable()
    val orderDate: Column<LocalDateTime?> = datetime("order_date").nullable()
    override val primaryKey = PrimaryKey(Id, name = "PK_Order_Id")

}

fun toOrder(row: ResultRow):Order =
    Order(
        id = row[Orders.Id],
        customerId = row[Orders.customer],
        totalCost = row[Orders.totalCost],
        orderDate = row[Orders.orderDate].toString()
    )
