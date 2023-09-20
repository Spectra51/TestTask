package com.flycode.testtask.model.retrofit

import com.flycode.testtask.model.api.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val service: Api = getInstance().create(Api::class.java)

    fun getInstance(): Retrofit {

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(AuthInterceptor(token = "66e060af6f5ed7d4217dc7d05ad909da"))
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.onlinetours.ru/api/v2/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        return retrofit
    }
}

class AuthInterceptor(val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val newRequest: Request = originalRequest.newBuilder()
            .header("Authorization", "Token token=$token")
            .build()

        return chain.proceed(newRequest)
    }

}