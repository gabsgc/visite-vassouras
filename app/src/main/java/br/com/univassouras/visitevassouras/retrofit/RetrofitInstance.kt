package br.com.univassouras.visitevassouras.retrofit

import br.com.univassouras.visitevassouras.service.ServiceInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var retrofitInstance: Retrofit? = null
    val retrofit: Retrofit get() = retrofitInstance ?: createRetrofit()
    private var serviceInstance: ServiceInterface? = null
    val service: ServiceInterface get() = serviceInstance ?: createService()

    private fun createService() : ServiceInterface {
        serviceInstance = retrofit.create(ServiceInterface::class.java)
        return serviceInstance!!
    }

    private val BASE_URL = "https://private-a13983-visitevassouras.apiary-mock.com/"
    private fun createRetrofit() : Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }
}