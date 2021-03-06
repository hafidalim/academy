package com.example.academy.data.source

import com.example.academy.data.ContentEntity
import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity
import com.example.academy.data.source.remote.RemoteDataSource

class FakeAcademyRepository (private val remoteDataSource: RemoteDataSource) : AcademyDataSource{


    override fun getAllcourses(): List<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponses){
            val course = CourseEntity(response.id,
            response.title,
            response.description,
            response.date,
            false,
            response.imagePath)
            courseList.add(course)
        }
        return courseList
    }

    override fun getBookmarkedCourses(): List<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponses){
            val course = CourseEntity(response.id,
            response.title,
            response.description,
            response.date,
            false,
            response.imagePath)
            courseList.add(course)
        }
        return courseList
    }

    override fun getCourseWithModules(courseId: String): CourseEntity {
        val courseResponse = remoteDataSource.getAllCourses()
        lateinit var course : CourseEntity
        for (i in courseResponse){
            if (i.id == courseId){
                course = CourseEntity(i.id,
                i.title,
                i.description,
                i.date,
                false,
                i.imagePath)
            }
        }
        return course
    }

    override fun getAllModulesByCourse(courseId: String): List<ModuleEntity> {
        val moduleResponses = remoteDataSource.getModules(courseId)
        val moduleList = ArrayList<ModuleEntity>()
        for (response in moduleResponses){
            val course = ModuleEntity(response.moduleId,
            response.courseId,
            response.title,
            response.position,
            false)
            moduleList.add(course)

        }
        return moduleList
    }

    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteDataSource.getModules(courseId)
        lateinit var module : ModuleEntity
        for (response in moduleResponses){
            if (response.moduleId == moduleId){
                module = ModuleEntity(
                    response.moduleId,
                    response.courseId,
                    response.title,
                    response.position,
                    false
                )
                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }
        return module
    }
}