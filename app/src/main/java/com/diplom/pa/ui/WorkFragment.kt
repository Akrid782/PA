package com.diplom.pa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.diplom.pa.R
import com.diplom.pa.databinding.FragmentWorkBinding

/**
 * A simple [Fragment] subclass.
 */
class WorkFragment : Fragment() {

    private lateinit var mBinding: FragmentWorkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentWorkBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
    }

}
