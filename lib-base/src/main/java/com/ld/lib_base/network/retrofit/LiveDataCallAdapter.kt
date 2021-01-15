package com.ld.lib_base.network.retrofit

import androidx.lifecycle.LiveData
import com.ld.lib_base.network.network.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 *  author : ld
 *  time   : 2021/01/15
 *  desc   :
 */
class LiveDataCallAdapter<T> internal constructor(
    private val mResponseType: Type,
    private val isApiResponse: Boolean
) : CallAdapter<T, LiveData<T>> {

    override fun adapt(call: Call<T>): LiveData<T> {
        return  MyLiveData(call,isApiResponse)
    }

    override fun responseType(): Type {
        return mResponseType
    }

    private class MyLiveData<T> internal constructor(
        private val call: Call<T>,
        private val isApiResponse: Boolean
    ) : LiveData<T>() {
        private val stared = AtomicBoolean(false)
        override fun onActive() {
            super.onActive()
            val b = stared.compareAndSet(false, true)
            if (b) {
                call.enqueue(object : Callback<T> {
                    override fun onFailure(call: Call<T>, t: Throwable) {
                        if (isApiResponse) {
                            postValue(ApiResponseRetrofit<T>(ApiResponseRetrofit.codeError,t.message!!) as T)
                        } else {
                            postValue(t.message as T?)
                        }
                    }

                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        val body = response.body()
                        postValue(body)
                    }

                })
            }
        }
    }
}