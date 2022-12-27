package app.mardsoul.requestbin

import android.app.Application
import android.content.Context
import androidx.room.Room
import app.mardsoul.requestbin.data.HistoryUseCaseImpl
import app.mardsoul.requestbin.data.SearchUseCaseImpl
import app.mardsoul.requestbin.data.database.HistoryDatabase
import app.mardsoul.requestbin.data.network.BinlistApi
import app.mardsoul.requestbin.domain.HistoryUseCase
import app.mardsoul.requestbin.domain.SearchUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BINLIST_BASE_URL = "https://lookup.binlist.net/"

class App : Application() {

    val historyUseCase: HistoryUseCase by lazy { HistoryUseCaseImpl(database.getHistoryDao()) }
    val searchUseCase: SearchUseCase by lazy { SearchUseCaseImpl(binlistApi) }

    private val binlistApi by lazy {
        retrofit.create(BinlistApi::class.java)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BINLIST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, HistoryDatabase::class.java, "history.db")
            .build()
    }
}

val Context.app: App
    get() = applicationContext as App