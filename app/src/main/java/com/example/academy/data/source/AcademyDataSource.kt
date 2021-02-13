package com.example.academy.data.source

import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity

interface AcademyDataSource {
    fun getAllcourses() : List<CourseEntity>
    fun getBookmarkedCourses() : List<CourseEntity>
    fun getCourseWithModules(courseId : String) : CourseEntity
    fun getAllModulesByCourse(courseId: String) : List<ModuleEntity>
    fun getContent(courseId: String, moduleId : String) : ModuleEntity
}