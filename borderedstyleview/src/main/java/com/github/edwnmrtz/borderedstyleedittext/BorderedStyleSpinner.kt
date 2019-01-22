package com.github.edwnmrtz.borderedstyleedittext

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.View

class BorderedStyleSpinner (context : Context, attrs : AttributeSet) : ConstraintLayout(context, attrs) {

    private var tvTitle : AppCompatTextView
    private var spinner : AppCompatSpinner

    private var titleTextColor : Int = ContextCompat.getColor(context,R.color.greyish)

    private var isError = false
    private var etField : AppCompatEditText

    init {
        View.inflate(context, R.layout.bordered_spinner, this)
        tvTitle = findViewById(R.id.tvTitle)
        spinner = findViewById(R.id.spinner)
        etField = findViewById(R.id.etField)

        val attributes: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BorderedStyleSpinner, 0, 0)

        val count = attributes.indexCount
        for (i in 0 until count) {
            val attr = attributes.getIndex(i)
            when (attr) {
                R.styleable.BorderedStyleSpinner_fieldLabel -> {
                    tvTitle.text = attributes.getString(R.styleable.BorderedStyleSpinner_fieldLabel)
                    tvTitle.visibility = View.VISIBLE
                }
                R.styleable.BorderedStyleSpinner_fieldLabelTextColor -> {
                    titleTextColor = attributes.getColor(R.styleable.NormalBorderedStyleEditText_fieldLabelTextColor, titleTextColor)
                    tvTitle.setTextColor(titleTextColor)
                }
                R.styleable.BorderedStyleSpinner_android_textAppearance -> {
                    TextViewCompat.setTextAppearance(etField, attributes.getResourceId(attr, 0))
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
        tvTitle.setTextColor(ContextCompat.getColor(context, R.color.reddish_pink))
        spinner.setBackgroundResource(R.drawable.bordered_roundbox_error)
    }

    fun removeError() {
        tvTitle.setTextColor(titleTextColor)
        spinner.setBackgroundResource(R.drawable.bordered_roundbox_active)
    }

}