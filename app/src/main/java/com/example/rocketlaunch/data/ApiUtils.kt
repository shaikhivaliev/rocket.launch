package com.example.rocketlaunch.data

import com.example.rocketlaunch.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/*        val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiLaunch::class.java)
*/

class ApiUtils {

    companion object {

        private var sClient: OkHttpClient? = null
        private var sRetrofit: Retrofit? = null
        private var sGson: Gson? = null
        private var sApi: ApiLaunch? = null


        private fun getClient(): OkHttpClient? {
            if (sClient == null) {

/*
                val interceptor = Interceptor { chain ->
                    val request = chain.request()?.newBuilder()
                        ?.build()
                    chain.proceed(request)
                }
*/

                val builder = OkHttpClient().newBuilder()
                builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                sClient = builder.build()

            }

            return sClient
        }

        private fun getRetrofit(): Retrofit? {

            if (sGson == null) sGson = Gson()

            if (sRetrofit == null) {
                sRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create(sGson))
                    .build()
            }

            return sRetrofit
        }

        fun getApiService(): ApiLaunch? {
            if (sApi == null) {
                sApi = getRetrofit()?.create(ApiLaunch::class.java)
            }

            return sApi
        }


    }


}