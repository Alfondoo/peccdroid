package com.javialonso.peccdroid.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton
import com.google.gson.Gson
import com.google.gson.GsonBuilder




@Singleton
class RestApi @Inject internal constructor() {

    private val retrofit: Retrofit = getApiClient()
    val authenticationService: AuthenticationApi = retrofit.create(AuthenticationApi::class.java)
    val feedService: FeedApi = retrofit.create(FeedApi::class.java)
    var authorizationToken: String? = null

    fun setAuthorizationToken(authToken: String): String? {
        authorizationToken = authToken
        return authorizationToken
    }

    private fun getApiClient(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val builder = GsonBuilder()
        builder.excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()

        return Retrofit.Builder()
                .baseUrl("http://192.168.1.8/api/0.1/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
