package delivery.plugins

import io.ktor.application.*

fun Application.connectDatabase(){
    val url = environment.config.property("jdbc.url").getString()
    val username = environment.config.property("jdbc.username").getString()
    val password = environment.config.property("jdbc.password").getString()

    DBsettings.init(url,username,password)
}