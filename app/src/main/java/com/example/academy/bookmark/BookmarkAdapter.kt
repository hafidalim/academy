package com.example.academy.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.academy.R
import com.example.academy.data.CourseEntity
import com.example.academy.databinding.ItemsBookmarkBinding
import com.example.academy.detail.DetailCourseActivity

class BookmarkAdapter(private val callBack : BookmarkFragmentCallback) :  RecyclerView.Adapter<BookmarkAdapter.CourseViewHolder>(){
    inner class CourseViewHolder(private val binding : ItemsBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course : CourseEntity){
            with(binding){
                tvItemTitle.text = course.title
                tvItemDate.text = itemView.resources.getString(R.string.deadline_date, course.deadline)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener { callBack.onShareClick(course) }
                Glide.with(itemView.context)
                    .load(course.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imagePoster)
            }
        }

    }

    private val listCourse = ArrayList<CourseEntity>()
    fun setCourses(courses : List<CourseEntity>?){
        if (courses == null) return
        this.listCourse.clear()
        this.listCourse.addAll(courses)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkAdapter.CourseViewHolder {
        val itemsBookmarkBinding = ItemsBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CourseViewHolder(itemsBookmarkBinding)
    }

    override fun getItemCount(): Int {
        return listCourse.size
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.CourseViewHolder, position: Int) {
        val course = listCourse[position]
        holder.bind(course)
    }
}