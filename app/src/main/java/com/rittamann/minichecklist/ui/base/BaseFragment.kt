package com.rittamann.minichecklist.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    fun finishFrag() {
        activity!!.supportFragmentManager.popBackStack()
    }

    fun initProgress() {
        (activity!! as BaseActivity).initProgress()
    }

    fun finishProgress() {
        (activity!! as BaseActivity).finishProgress()
    }
}