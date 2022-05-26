package com.example.onboardingsense.Activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.onboardingsense.AdaptersAndViewModel.DataViewModel
import com.example.onboardingsense.AdaptersAndViewModel.FragmentScreens
import com.example.onboardingsense.R
import com.example.onboardingsense.Fragments.ViewPagerFragment
import com.example.onboardingsense.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataModel: DataViewModel by viewModels()
    private var positionStep : Int = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        openFrag(ViewPagerFragment.newInstance(), R.id.fragment)
    }

    private fun openFrag(f: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(idHolder, f)
            .commit()
    }

    override fun onBackPressed() {
        var currentPosition: Int = dataModel.posFrag.value!!
        dataModel.posFrag.value = currentPosition - positionStep
        when(currentPosition){
            FragmentScreens.FRAGMENT_BRING.currentFragmentScreen->
                super.onBackPressed() }
        if(currentPosition>FragmentScreens.FRAGMENT_PERSONALIZE.currentFragmentScreen){
            super.onBackPressed()
        }
    }
}