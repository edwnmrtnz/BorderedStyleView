package com.github.edwnmrtz.borderedstyleedittext

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat


open class NormalBorderedStyleEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

     private var tvFieldLabelTitle: AppCompatTextView
     private var tvAssistiveText: AppCompatTextView
     private var etField: AppCompatEditText

     private var titleTextColor : Int       = ContextCompat.getColor(context,R.color.greyish)
     private var assistiveTextColor: Int    = ContextCompat.getColor(context,R.color.greyish)
     private var assistiveText : String?    = ""

     private var isError = false

    init {
        View.inflate(context, R.layout.bordered_edittext_normal, this)
        tvFieldLabelTitle   = findViewById(R.id.tvTitle)
        tvAssistiveText     = findViewById(R.id.tvAssistiveText)
        etField             = findViewById(R.id.etField)

        val attributes: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.NormalBorderedStyleEditText, 0, 0)

        val count = attributes.indexCount
        for (i in 0 until count) {
            val attr = attributes.getIndex(i)
            when (attr) {
                R.styleable.NormalBorderedStyleEditText_android_imeOptions -> {
                    etField.imeOptions =  attributes.getInt(attr, 0)
                }
                R.styleable.NormalBorderedStyleEditText_android_inputType -> {
                    etField.inputType = attributes.getInt(R.styleable.NormalBorderedStyleEditText_android_inputType, EditorInfo.TYPE_CLASS_TEXT)
                }
                R.styleable.NormalBorderedStyleEditText_android_maxLines -> {
                    etField.maxLines = attributes.getInt(attr, 1000)
                }
                R.styleable.NormalBorderedStyleEditText_android_maxLength -> {
                    etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(attributes.getInt(attr, 1000)))
                }
                R.styleable.NormalBorderedStyleEditText_assistiveText -> {
                    assistiveText = attributes.getString(R.styleable.NormalBorderedStyleEditText_assistiveText)
                    tvAssistiveText.text = assistiveText
                    tvAssistiveText.visibility = View.VISIBLE
                }
                R.styleable.NormalBorderedStyleEditText_fieldLabel -> {
                    tvFieldLabelTitle.text = attributes.getString(R.styleable.NormalBorderedStyleEditText_fieldLabel)
                    tvFieldLabelTitle.visibility = View.VISIBLE
                }
                R.styleable.NormalBorderedStyleEditText_assistiveTextColor -> {
                    assistiveTextColor = attributes.getColor(R.styleable.NormalBorderedStyleEditText_assistiveTextColor, assistiveTextColor)
                    tvAssistiveText.setTextColor(assistiveTextColor)
                }
                R.styleable.NormalBorderedStyleEditText_fieldLabelTextColor-> {
                    titleTextColor = attributes.getColor(R.styleable.NormalBorderedStyleEditText_fieldLabelTextColor, titleTextColor)
                    tvFieldLabelTitle.setTextColor(titleTextColor)
                }
                R.styleable.NormalBorderedStyleEditText_android_hint -> {
                    etField.hint = attributes.getString(R.styleable.NormalBorderedStyleEditText_android_hint)
                }

                R.styleable.NormalBorderedStyleEditText_android_textAppearance -> {
                    val appearance = attributes.getResourceId(attr, 0)
                    TextViewCompat.setTextAppearance(etField, appearance)
                }


                R.styleable.NormalBorderedStyleEditText_android_focusableInTouchMode -> {
                    etField.isFocusableInTouchMode = attributes.getBoolean(attr, true)
                }
                R.styleable.NormalBorderedStyleEditText_android_clickable -> {
                    etField.isClickable = attributes.getBoolean(attr, true)
                }
                R.styleable.NormalBorderedStyleEditText_android_focusable -> {
                    etField.isFocusable = attributes.getBoolean(attr, true)
                }
            }
        }
        attributes.recycle()

        textChangeListener()

            //setError("Invalid")
    }

    private fun textChangeListener() {
        etField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(isError) {
                    removeError()
                    isError = false
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Ignore
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Ignore
            }

        })
    }

    fun getText() : String {
        return etField.text.toString()
    }

    fun getEditText(): AppCompatEditText {
        return etField
    }

    fun setError(errorMessage: String) {
        isError = true
        tvFieldLabelTitle.setTextColor(ContextCompat.getColor(context, R.color.reddish_pink))
        tvAssistiveText.visibility = View.VISIBLE
        tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.reddish_pink))
        etField.setBackgroundResource(R.drawable.bordered_roundbox_error)
        tvAssistiveText.text = errorMessage
    }

    fun removeError() {
        isError = false
        tvFieldLabelTitle.setTextColor(titleTextColor)
        etField.setBackgroundResource(R.drawable.bordered_roundbox_active)
        tvAssistiveText.setTextColor(assistiveTextColor)

        if(assistiveText != null) {
            tvAssistiveText.visibility = View.VISIBLE
            tvAssistiveText.text = assistiveText
        } else {
            tvAssistiveText.visibility = View.GONE
        }
    }



    private fun setTypefaceFromAttrs(textView: TextView, styleIndex: Int) {
        textView.typeface = Typeface.DEFAULT
        if (styleIndex == 1) {
            textView.setTypeface(null, Typeface.BOLD)
        } else {
            textView.setTypeface(null, Typeface.NORMAL)
        }
    }

    companion object {
        val TAG = "NormalBorderedStyle"
    }
}