package com.example.quicktestapplication.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.quicktestapplication.R

/**
 * Use the [BaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class BaseFragment : Fragment() {
    protected var composeView: ComposeView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.layout_compose_view, container, false)
        composeView = root.findViewById(R.id.compose_view)
        return root
    }
}