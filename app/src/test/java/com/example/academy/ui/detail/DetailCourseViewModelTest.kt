package com.example.academy.ui.detail

import com.example.academy.data.source.AcademyRepository
import com.example.academy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class DetailCourseViewModelTest {

    private lateinit var viewModel : DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourse()[0]
    private val courseId = dummyCourse.courseId

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setup(){
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }
    @Test
    fun getCourse(){
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse)
        val courseEntity = viewModel.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules(){
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(DataDummy.generateDummyModules(courseId))
        val moduleEntities = viewModel.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size.toLong())
    }
}