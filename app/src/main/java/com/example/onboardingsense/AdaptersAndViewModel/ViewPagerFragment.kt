package com.example.onboardingsense.AdaptersAndViewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.onboardingsense.Fragments.BringFragment
import com.example.onboardingsense.Fragments.StressTypeFragment
import com.example.onboardingsense.Fragments.WorriesFragment
import com.example.onboardingsense.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {
    private val dataModel: DataViewModel by activityViewModels()

    private var _binding: FragmentViewPagerBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentList = arrayListOf<Fragment>(

            BringFragment(),
            StressTypeFragment(),
            WorriesFragment(),

        )

        val adapter = ViewPagerAdapterSense(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPagerFragment.isUserInputEnabled = false
        binding.viewPagerFragment.adapter = adapter


        dataModel.posFrag.observe(viewLifecycleOwner, {
            binding.viewPagerFragment.currentItem = it
        })
        binding.indicator.setViewPager(binding.viewPagerFragment)

    }
    companion object{
        @JvmStatic
        fun newInstance() = ViewPagerFragment()
    }

}
