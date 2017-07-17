package com.javialonso.peccdroid.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RestApi @Inject internal constructor() {

    private val retrofit: Retrofit = getApiClient()
    val authenticationService: AuthenticationApi = retrofit.create(AuthenticationApi::class.java)
    val feedService: FeedApi = retrofit.create(FeedApi::class.java)
    var authorizationToken: String? = null

    fun setAuthorizationToken(authToken: String): String {
        authorizationToken = authToken
        authorizationToken?.let { return it }
        return ""
    }

    private fun getApiClient(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val builder = GsonBuilder()
        builder.excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()

        val debugServerMac = "http://192.168.0.125:8800/api/0.1/"
        val debugServerWin = "http://192.168.1.8/api/0.1/"

        return Retrofit.Builder()
                .baseUrl(debugServerMac)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
