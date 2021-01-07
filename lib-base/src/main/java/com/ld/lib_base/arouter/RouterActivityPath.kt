package com.ld.lib_base.arouter

class RouterActivityPath {
    object Login {
        const val LOGIN = "/module_login"

        /**
         * 登录页
         */
        const val PAGER_LOGIN = "$LOGIN/Login"
    }

    object Main {
        const val MAIN = "/module_main"

        /**
         * 主页面
         */
        const val PAGE_MAIN = "$MAIN/Main"
    }
}