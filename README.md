

### 简介
本项目基于 Arouter+Kotlin+Jetpack(Lifecycle+UnPeek-LiveData等+Room)+(协程+Retrofit)+Aop(Aspectjx)+ktx拓展函数等。该项目仅仅是个简单的demo,用做通用框架，((https://github.com/1170762202/WanAndroid/issues))。
[源码地址](https://github.com/gh2571527745/general)

# 目录结构
```kotlin
||-- app // app 入口
    ||lib //lib库
      ||--lib-aop// aop 封装（登录校验、重复点击）
      ||--lib-db// room
      ||--lib-base// 基础封装（路由分发、网络请求、Base封装）
      ||--lib-common//通用库（基础依赖、SmallestWidth限定符适配、基础Vlaues）
      ||--lib-widget// 控件
  ||--modules// 功能模块
    ||--module-home// 首页
    ||--module-login// 登录
    ||--module-other//其他
||-- README.md
 ```

 #### module单独编译运行说明
 gradle.properties 文件下有个"集成开发模式" 和 "组件开发模式"的切换开关 true表示组件独立运行，false表示一个library
 ````kotlin
 isRunModule=true
 ````


 ## 主要开源框架
*   [Arouter](https://github.com/alibaba/ARouter)

*   [MMKV](https://github.com/Tencent/MMKV)

*   [aop](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)

*   [Jetpack MVVM](https://developer.android.google.cn/jetpack/)

*   [UnPeek-LiveData](https://github.com/KunMinX/UnPeek-LiveData)

*   [flexbox-layout](https://github.com/google/flexbox-layout)

*   [MMKV](https://github.com/Tencent/MMKV)

*   [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

*   [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)

*   [PersistentCookieJar](https://github.com/franmontiel/PersistentCookieJar)

Thanks to
*   [WanAndroid](https://github.com/1170762202/WanAndroid)
*   [JetpackMvvm](https://github.com/hegaojian/JetpackMvvm)
*   [重学安卓](https://xiaozhuanlan.com/kunminx?rel=8184827882)