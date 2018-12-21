package com.github.edwnmrtz.borderedstyleedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.AppCompatTextView
import android.text.InputFilter
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo

class BorderedStyleSpinner (context : Context, attrs : AttributeSet) : ConstraintLayout(context, attrs) {

    private var tvTitle : AppCompatTextView
    private var spinner : AppCompatSpinner

    private var titleTextColor : Int = Color.BLACK

    private var isError = false

    init {
        View.inflate(context, R.layout.bordered_spinner, this)
        tvTitle = findViewById(R.id.tvTitle)
        spinner = findViewById(R.id.spinner)

        val attributes: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BorderedStyleSpinner, 0, 0)

        val count = attributes.indexCount
        for (i in 0 until count) {
            val attr = attributes.getIndex(i)
            when (attr) {
                R.styleable.BorderedStyleSpinner_fieldTitle -> {
                    tvTitle.text = attributes.getString(R.styleable.BorderedStyleSpinner_fieldTitle)
                    tvTitle.visibility = View.VISIBLE
                }

                R.styleable.BorderedStyleSpinner_fieldTitleTextColor -> {
                    titleTextColor = attributes.getColor(R.styleable.BorderedStyleSpinner_fieldTitleTextColor,
                        Color.BLACK)
                    tvTitle.setTextColor(titleTextColor)
                }
            }
        }
        attributes.recycle()
    }

    fun getSpinner() : AppCompatSpinner {
        return spinner;
    }

    fun setError(errorMessage: String) {
        isError = true
        tvTitle.setTextColor(Color.RED)
        spinner.setBackgroundResource(R.drawable.bordered_roundbox_error)
    }

    fun removeError() {
        tvTitle.setTextColor(titleTextColor)
        spinner.setBackgroundResource(R.drawable.bordered_roundbox_active)
    }

}