package com.github.edwnmrtz.borderedstyleview.app


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.edwnmrtz.borderedstyleedittext.NormalBorderedStyleEditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentOne : Fragment() {

    private lateinit var etSample : NormalBorderedStyleEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_fragment_one, container, false)

        etSample = view.findViewById(R.id.etSample)

        etSample.getEditText().setText("Hello")

        return view
    }


}
