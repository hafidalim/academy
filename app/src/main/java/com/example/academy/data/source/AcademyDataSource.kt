package com.example.academy.data.source

import androidx.lifecycle.LiveData
import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity

interface AcademyDataSource {
    fun getAllcourses() : LiveData<List<CourseEntity>>
    fun getBookmarkedCourses() : LiveData<List<CourseEntity>>
    fun getCourseWithModules(courseId : String) : LiveData<CourseEntity>
    fun getAllModulesByCourse(courseId: String) : LiveData<List<ModuleEntity>>
    fun getContent(courseId: String, moduleId : String) : LiveData<ModuleEntity>
}