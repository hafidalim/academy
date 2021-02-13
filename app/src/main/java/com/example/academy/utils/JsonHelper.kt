package com.example.academy.utils

import android.content.Context
import com.example.academy.data.source.remote.response.ContentResponse
import com.example.academy.data.source.remote.response.CourseResponse
import com.example.academy.data.source.remote.response.ModuleResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName : String) : String?{
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        }
        catch (ex : IOException){
            ex.printStackTrace()
            null
        }
    }
    fun loadCourses() : List<CourseResponse>{
        val list = ArrayList<CourseResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString(("CourseResponses.json").toString()))
            val listArray = responseObject.getJSONArray("courses")
            for (i in 0 until listArray.length()){
                val courses = listArray.getJSONObject(i)
                val id = courses.getString("id")
                val title = courses.getString("title")
                val description =  courses.getString("description")
                val date = courses.getString("date")
                val imagePath = courses.getString("imagePath")

                val courseResponse = CourseResponse(id, title, description, date, imagePath)
                list.add(courseResponse)
            }
        } catch (e : JSONException){
            e.printStackTrace()
        }

        return list
    }
    fun loadModule(courseId : String) : List<ModuleResponse>{
        val fileName = String.format("Module_%s.json", courseId)
        val list = ArrayList<ModuleResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result!= null){
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("modules")
                for(i in 0 until listArray.length()){
                    val course = listArray.getJSONObject(i)
                    val moduleId = course.getString("moduleId")
                    val title = course.getString("title")
                    val position = course.getString("position")
                    val courseResponse = ModuleResponse(moduleId, courseId, title, Integer.parseInt(position))
                    list.add(courseResponse)
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
        return list
    }
    fun loadContent(moduleId : String) : ContentResponse{
        val fileName = String.format("Content_%s.json", moduleId)
        var contentResponse : ContentResponse?= null
        try {
            val result = parsingFileToString(fileName)
            if (result != null){
                val responseObject = JSONObject(result)
                val content = responseObject.getString("content")
                contentResponse = ContentResponse(moduleId, content)
            }
        }
        catch (e : JSONException){
            e.printStackTrace()
        }
        return contentResponse as ContentResponse
    }
}