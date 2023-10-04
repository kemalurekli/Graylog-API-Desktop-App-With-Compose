package data

import data.model.TotalContent
import data.model.dailyModel.DailyContent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object TotalContentApiClient {
    private val client = HttpClient(CIO) {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(username = "admin", password = "password")
                }
            }
        }
        install(ContentNegotiation) {
            json()
        }
    }


    suspend fun getAllContent(): TotalContent {
        val url = "http://ipadress:9000/api/count/total"
        return client.get(url).body()
    }


    private val clientTwo = HttpClient(CIO) {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(username = "admin", password = "password")
                }
            }
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        //This is for request time out limits
        engine {
            requestTimeout = 0 // 0 to disable, or a millisecond value to fit your needs
        }


    }


    suspend fun getDailyContent(): DailyContent {
        val urllink = "http://ipadress:9000/api/search/universal/absolute"
        return clientTwo.get(urllink) {
            url {
                //These are body parameters
                parameters.append("query", "192.168.100.1")
                parameters.append("from", "2023-10-01T00:00:00.000Z")
                parameters.append("to", "2023-10-03T00:10:00.000Z")
                //parameters.append("limit", "9000")
                parameters.append("search_after", "1463538857")
            }
        }.body()

    }


}