package com.example.academy.detail

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.academy.R
import com.example.academy.data.CourseEntity
import com.example.academy.databinding.ActivityDetailCourseBinding
import com.example.academy.databinding.ContentDetailCourseBinding
import com.example.academy.reader.CourseReaderActivity
import com.example.academy.utils.DataDummy

class DetailCourseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private lateinit var detailContentBinding : ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()
        val extras = intent.extras
        if (extras != null){
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null){
                val modules = DataDummy.generateDummyModules(courseId)
                adapter.setModules(modules)
                for (course in DataDummy.generateDummyCourse()){
                    if (course.courseId == courseId){
                        populateCourse(course)
                    }
                }
            }
        }
        with(detailContentBinding.rvModules){
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }
    private fun populateCourse(courseEntity: CourseEntity){
        detailContentBinding.textTitle.text = courseEntity.title
        detailContentBinding.textDescription.text = courseEntity.description
        detailContentBinding.textDate.text = resources.getString(R.string.deadline_date, courseEntity.deadline)
        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)

        detailContentBinding.btnStart.setOnClickListener {
            val intent = Intent(this, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }
}