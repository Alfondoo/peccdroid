package com.javialonso.peccdroid.data.network

import com.javialonso.peccdroid.data.entity.*
import io.reactivex.Maybe
import retrofit2.http.*

interface FeedApi {
    @GET("perfil/")
    fun profile(@Header("Authorization") token: String): Maybe<ProfileEntity>

    @GET("historias/")
    fun historias(@Header("Authorization") token: String): Maybe<List<HistoriaEntity>>

    @GET("historias/{id}")
    fun detailHistoria(@Header("Authorization") token: String, @Path("id") id: Int): Maybe<HistoriaDetailEntity>

    @POST("historias/")
    fun createHistoria(@Header("Authorization") token: String, @Body historia: HistoriaCreationEntity): Maybe<HistoriaDetailEntity>

    @POST("historias/{idHistoria}/aportes/{aportePadre}/continuar/")
    fun createAporteHistoria(@Header("Authorization") token: String, @Body aporte: AporteCreationEntity, @Path("aportePadre") aportePadre: Int, @Path("idHistoria") historia: Int): Maybe<AporteDetailEntity>
}