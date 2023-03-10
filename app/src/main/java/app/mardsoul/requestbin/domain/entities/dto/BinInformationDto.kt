package app.mardsoul.requestbin.domain.entities.dto

import com.google.gson.annotations.SerializedName

data class BinInformationDto(
    @SerializedName("bank")
    val bankDto: BankDto,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("country")
    val countryDto: CountryDto,
    @SerializedName("number")
    val numberDto: NumberDto,
    @SerializedName("prepaid")
    val prepaid: Boolean,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String
)