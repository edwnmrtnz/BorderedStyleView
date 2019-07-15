package com.github.edwnmrtz.borderedstyleview.app


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.fragment_fragment_one.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentOne : Fragment() {

    private lateinit var suffixes: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_fragment_one, container, false)

        suffixes = context?.resources?.getStringArray(R.array.suffixes)!!
        val adapter  = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, suffixes)

        view.spSuffix.getSpinner().adapter = adapter

        return view
    }


}
