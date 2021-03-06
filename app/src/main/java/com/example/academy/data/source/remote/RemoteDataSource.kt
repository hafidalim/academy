package com.example.academy.data.source.remote

import android.os.Handler
import android.os.Looper
import com.example.academy.data.source.remote.response.ContentResponse
import com.example.academy.data.source.remote.response.CourseResponse
import com.example.academy.data.source.remote.response.ModuleResponse
import com.example.academy.utils.JsonHelper


class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS : Long = 2000

        @Volatile
        private var instance : RemoteDataSource? = null
        fun getInstance(helper: JsonHelper) : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllCourses(callBack : LoadCoursesCallback) {
        handler.postDelayed({callBack.onAllCourseReceived(jsonHelper.loadCourses())}, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getModules(courseId : String, callBack : LoadModulesCallback){
        handler.postDelayed({callBack.onAllModulesReceived(jsonHelper.loadModule(courseId))}, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getContent(moduleId : String, callBack : LoadContentCallback){
        handler.postDelayed({callBack.onContentReceived(jsonHelper.loadContent(moduleId))}, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadCoursesCallback{
        fun onAllCourseReceived(courseResponse: List<CourseResponse>)
    }
    interface LoadModulesCallback{
        fun onAllModulesReceived(moduleResponse: List<ModuleResponse>)
    }
    interface LoadContentCallback{
        fun onContentReceived(contentResponse : ContentResponse)
    }
}