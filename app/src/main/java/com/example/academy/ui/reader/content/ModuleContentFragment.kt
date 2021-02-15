package com.example.academy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.academy.data.ModuleEntity
import com.example.academy.databinding.FragmentModuleContentBinding
import com.example.academy.ui.reader.CourseReaderViewModel
import com.example.academy.viewmodel.ViewModelFactory


class ModuleContentFragment : Fragment() {
    companion object{
        val TAG : String = ModuleContentFragment::class.java.simpleName
        fun newInstance() : ModuleContentFragment = ModuleContentFragment()
    }
    private lateinit var fragmentModuleContentBinding : FragmentModuleContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentModuleContentBinding = FragmentModuleContentBinding.inflate(inflater,container, false)
        return fragmentModuleContentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            
            val viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
            fragmentModuleContentBinding.progressBar.visibility = View.VISIBLE
            viewModel.getSelectedModules().observe(viewLifecycleOwner, {module ->
                fragmentModuleContentBinding.progressBar.visibility = View.GONE
                if (module != null){
                    populateWebView(module)
                }
            })
        }
    }
    private fun populateWebView(module : ModuleEntity){
        fragmentModuleContentBinding.webView.loadData(module.contentEntity?.content ?: "", "text/html", "UTF-8")
    }

}