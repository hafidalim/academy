package com.example.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.academy.data.ContentEntity
import com.example.academy.data.ModuleEntity
import com.example.academy.data.source.AcademyRepository
import com.example.academy.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId : String
    private lateinit var moduleId : String

    fun selectedCourse(courseId : String){
        this.courseId = courseId
    }
    fun setSelectedModule(moduleId : String){
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)


    fun getSelectedModules() : LiveData<ModuleEntity> = academyRepository.getContent(courseId, moduleId)

}