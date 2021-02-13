package com.example.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.example.academy.data.CourseEntity
import com.example.academy.data.source.AcademyRepository
import com.example.academy.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel (){
    fun getBookmarks() : List<CourseEntity> = academyRepository.getBookmarkedCourses()
}