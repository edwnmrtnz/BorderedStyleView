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

//        val etPrefixed : PrefixedBorderedStyleEditText2 = view.findViewById(R.id.etPrefixed)
//
//        setEditTextFocus(etPrefixed.getEditText(), true)

        view.btnError.setOnClickListener {
            view.etPrefixed.setError("This field is required")
            view.etPassword.setError("This field is required")
            view.etNormal.setError("This field is required")
            view.etAutoComplete.setError("This field is required")
            view.spSuffix.setError("This field is required")
        }

        view.btnRemoveError.setOnClickListener {
            view.etPassword.removeError()
            view.etPrefixed.removeError()
            view.etNormal.removeError()
            view.etAutoComplete.removeError()
            view.spSuffix.removeError()
        }

        suffixes = context?.resources?.getStringArray(R.array.suffixes)!!
        val adapter  = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, suffixes)

        view.spSuffix.getSpinner().adapter = adapter


        return view
    }

}
