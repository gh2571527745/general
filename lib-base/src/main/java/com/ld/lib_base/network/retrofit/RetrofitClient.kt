package com.ld.lib_base.network.retrofit

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.ld.lib_base.base.appContext
import com.ld.lib_base.constant.Constant
import com.ld.lib_base.network.network.interceptor.HeadInterceptor
import me.hgj.jetpackmvvm.network.interceptor.CacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *  author : ld
 *  time   : 2021/01/15
 *  desc   :Retrofit请求的封装类
 */
class RetrofitClient private constructor() {
    companion object {
        val instance: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
    }

    fun <T> create(baseUrl: String, service: Class<T>?): T {
        return initRetorfitWithLiveData(baseUrl, initOkHttp()).create(service)
    }

    private fun initRetorfitWithLiveData(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun initOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .readTimeout(Constant.TIMEOUT_CONNECTION, TimeUnit.SECONDS)//设置读取超时时间
            .connectTimeout(Constant.TIMEOUT_READ, TimeUnit.SECONDS)//设置请求超时时间
            .writeTimeout(Constant.TIMEOUT_READ, TimeUnit.SECONDS)//设置写入超时时间
            .retryOnConnectionFailure(true)
            //设置缓存配置 缓存最大10M
            .cache(Cache(File(appContext.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            //添加Cookies自动持久化
            .cookieJar(cookieJar)
            //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
            .addInterceptor(HeadInterceptor())
            //添加缓存拦截器 可传入缓存天数，不传默认7天
            .addInterceptor(CacheInterceptor())
            // 日志拦截器
            .addInterceptor(loggingInterceptor)
            .build()
    }

    val cookieJar: PersistentCookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(appContext))
    }
}