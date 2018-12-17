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
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo


class NormalBorderedStyleEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var tvTitle: AppCompatTextView
    private var tvError: AppCompatTextView
    private var etField: AppCompatEditText

    private var titleTextColor : Int    = Color.BLACK
    private var errorTextColor: Int     = Color.RED

    private var isError = false

    private val attributes: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.NormalBorderedStyleEditText, 0, 0)

    init {
        View.inflate(context, R.layout.bordered_edittext_normal, this)
        tvTitle = findViewById(R.id.tvTitle)
        tvError = findViewById(R.id.tvErrorText)
        etField = findViewById(R.id.etField)

        val count = attributes.indexCount
        for (i in 0 until count) {
            val attr = attributes.getIndex(i)
            when (attr) {
                R.styleable.NormalBorderedStyleEditText_android_imeOptions -> {
                    Log.e(TAG, "Has ime options")
                    etField.imeOptions =  attributes.getInt(attr, 0)

                }
                R.styleable.NormalBorderedStyleEditText_android_inputType -> {
                    Log.e(TAG, "Has input type")
                    etField.inputType = attributes.getInt(R.styleable.NormalBorderedStyleEditText_android_inputType, EditorInfo.TYPE_CLASS_TEXT)
                }
                R.styleable.NormalBorderedStyleEditText_android_maxLines -> {
                    Log.e(TAG, "Has max lines")
                    etField.maxLines = attributes.getInt(attr, 1000)
                }
                R.styleable.NormalBorderedStyleEditText_android_maxLength -> {
                    Log.e(TAG, "Has maxlength")
                    etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(attributes.getInt(attr, 1000)))
                }
                R.styleable.NormalBorderedStyleEditText_error -> {
                    Log.e(TAG, "Has error")
                    tvError.text = attributes.getString(R.styleable.NormalBorderedStyleEditText_error)
                }
                R.styleable.NormalBorderedStyleEditText_title -> {
                    Log.e(TAG, "Has title")
                    tvTitle.text = attributes.getString(R.styleable.NormalBorderedStyleEditText_title)
                    tvTitle.visibility = View.VISIBLE
                }
                R.styleable.NormalBorderedStyleEditText_ErrorTextColor -> {
                    errorTextColor = attributes.getColor(R.styleable.NormalBorderedStyleEditText_ErrorTextColor,
                            ContextCompat.getColor(context, android.R.color.holo_red_light))
                    tvError.setTextColor(errorTextColor)
                }
                R.styleable.NormalBorderedStyleEditText_titleTextColor -> {
                    titleTextColor = attributes.getColor(R.styleable.NormalBorderedStyleEditText_ErrorTextColor,
                            Color.BLACK)
                    tvTitle.setTextColor(errorTextColor)
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
        val TAG = "NormalBorderedStyle"
    }
}