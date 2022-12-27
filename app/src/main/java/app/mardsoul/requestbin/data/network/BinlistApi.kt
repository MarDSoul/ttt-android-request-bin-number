package app.mardsoul.requestbin.data.network

import app.mardsoul.requestbin.data.network.entitiesdto.BinInformationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinlistApi {

    @GET("{binNumber}")
    fun getBinInformation(@Path("binNumber") binNumber: String): Response<BinInformationDto>
}