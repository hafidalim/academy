package com.example.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity
import com.example.academy.data.source.AcademyRepository
import com.example.academy.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId : String
    fun setSelectedCourse(courseId : String){
        this.courseId = courseId
    }

    fun getCourse() : CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules() : List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)

}