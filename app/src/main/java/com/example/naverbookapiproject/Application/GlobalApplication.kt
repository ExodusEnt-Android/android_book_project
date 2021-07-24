package com.example.naverbookapiproject.Application

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo

class GlobalApplication :Application() {

    companion object{
        var isDebugMode:Boolean=false // 디버그 모드 인지 여부
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        isDebugMode = isDebuggable(this)//application 시작 시점에서 ->  debug 모드 판단.
    }

    //debug 가능 여부를  체크해준다. (logger 안보이게 할려고)
    //release 버전에서는  false 로 체크된다.
    fun isDebuggable(context: Context): Boolean {
        return context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0//0이 아니면 debug 모드
    }
}