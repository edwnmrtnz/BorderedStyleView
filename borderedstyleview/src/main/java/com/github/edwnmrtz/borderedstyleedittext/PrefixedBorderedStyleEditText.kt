package com.github.edwnmrtz.borderedstyleedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo

class PrefixedBorderedStyleEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var tvTitle: AppCompatTextView
    private var tvError: AppCompatTextView
    private var etField: AppCompatEditText
    private var tvPrefix : AppCompatTextView

    private var titleTextColor : Int    = Color.BLACK
    private var errorTextColor: Int     = Color.RED

    private var isError = false

    init {
        View.inflate(context, R.layout.bordered_edittext_prefixed, this)
        tvTitle     = findViewById(R.id.tvTitle)
        tvError     = findViewById(R.id.tvError)
        etField     = findViewById(R.id.etField)
        tvPrefix    = findViewById(R.id.tvPrefix)

        val attributes: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PrefixedBorderedStyleEditText, 0, 0)
        val count = attributes.indexCount
        for (i in 0 until count) {
            val attr = attributes.getIndex(i)
            when (attr) {
                R.styleable.PrefixedBorderedStyleEditText_android_imeOptions -> {
                    etField.imeOptions =  attributes.getInt(attr, 0)
                }
                R.styleable.NormalBorderedStyleEditText_android_inputType -> {
                    etField.inputType = attributes.getInt(R.styleable.NormalBorderedStyleEditText_android_inputType, EditorInfo.TYPE_CLASS_TEXT)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_maxLines -> {
                    etField.maxLines = attributes.getInt(attr, 1000)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_maxLength -> {
                    etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(attributes.getInt(attr, 1000)))
                }
                R.styleable.PrefixedBorderedStyleEditText_fieldError -> {
                    tvError.text = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_fieldError)
                }
                R.styleable.PrefixedBorderedStyleEditText_fieldTitle -> {
                    tvTitle.text = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_fieldTitle)
                    etField.hint = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_fieldTitle)
                    tvTitle.visibility = View.VISIBLE
                }
                R.styleable.PrefixedBorderedStyleEditText_fieldErrorTextColor -> {
                    errorTextColor = attributes.getColor(R.styleable.PrefixedBorderedStyleEditText_fieldErrorTextColor,
                            ContextCompat.getColor(context, android.R.color.holo_red_light))
                    tvError.setTextColor(errorTextColor)
                }
                R.styleable.PrefixedBorderedStyleEditText_fieldTitleTextColor -> {
                    titleTextColor = attributes.getColor(R.styleable.PrefixedBorderedStyleEditText_fieldTitleTextColor,
                            Color.BLACK)
                    tvTitle.setTextColor(titleTextColor)
                }
                R.styleable.PrefixedBorderedStyleEditText_prefix -> {
                    tvPrefix.text = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_prefix)
                }
            }
        }
        attributes.recycle()

        textChangeListener()
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
        tvTitle.setTextColor(Color.RED)
        tvError.visibility = View.VISIBLE
        tvError.setTextColor(Color.RED)
        etField.setBackgroundResource(R.drawable.bordered_roundbox_error)
        tvError.text = errorMessage
    }

    fun removeError() {
        tvTitle.setTextColor(titleTextColor)
        tvError.visibility = View.GONE
        etField.setBackgroundResource(R.drawable.bordered_roundbox_active)
        tvError.text = ""
    }

    companion object {
        val TAG = "PrefixedBorderedStyle"
    }
}