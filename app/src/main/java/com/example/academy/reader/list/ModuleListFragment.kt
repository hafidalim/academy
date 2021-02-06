package com.example.academy.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academy.R
import com.example.academy.data.ModuleEntity
import com.example.academy.databinding.FragmentModuleListBinding
import com.example.academy.databinding.ItemsModuleListCustomBinding
import com.example.academy.reader.CourseReaderActivity
import com.example.academy.reader.CourseReaderCallback
import com.example.academy.utils.DataDummy

class ModuleListFragment : Fragment(), ModulelistAdapter.MyAdapterClickListener {
    companion object{
        val TAG : String = ModuleListFragment::class.java.simpleName
        fun newInstance() : ModuleListFragment = ModuleListFragment()

    }
    private lateinit var fragmentModuleListFragmentBinding: FragmentModuleListBinding
    private lateinit var adapter : ModulelistAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentModuleListFragmentBinding = FragmentModuleListBinding.inflate(inflater, container, false)
        return fragmentModuleListFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ModulelistAdapter(this)
        populateRecyclerView(DataDummy.generateDummyModules("a14"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }
    private fun populateRecyclerView(modules : List<ModuleEntity>){
        with(fragmentModuleListFragmentBinding){
            progressBar.visibility = View.GONE
            adapter.setModules(modules)
            rvModule.layoutManager = LinearLayoutManager(context)
            rvModule.setHasFixedSize(true)
            rvModule.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
    }


}