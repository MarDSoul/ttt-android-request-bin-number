package app.mardsoul.requestbin.data.network

import app.mardsoul.requestbin.domain.entities.dto.BinInformationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinlistApi {

    @GET("{bin_number}")
    suspend fun getBinInformation(@Path("bin_number") binNumber: String): Response<BinInformationDto>
}