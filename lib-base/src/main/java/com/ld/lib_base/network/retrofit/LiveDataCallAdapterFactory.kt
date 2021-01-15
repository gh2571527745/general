package com.ld.lib_base.network.retrofit

import android.util.Log
import com.ld.lib_base.network.network.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *  author : ld
 *  time   : 2021/01/15
 *  desc   :
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        var observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        var rawType = getRawType(observableType)
        Log.e("TAG","rawType = "+rawType.toString())
        var isApiResponse = true
        if (rawType!=ApiResponse::class.java){
            isApiResponse = false
        }
        if (!(observableType is ParameterizedType)){
            Log.e("TAG","rawType = resource must be parameterized"+rawType.toString())
        }
        return LiveDataCallAdapter<String>(observableType,isApiResponse)
    }
}