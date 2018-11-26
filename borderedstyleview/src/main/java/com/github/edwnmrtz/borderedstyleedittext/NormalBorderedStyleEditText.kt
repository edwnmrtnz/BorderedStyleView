package com.github.edwnmrtz.borderedstyleedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.View

class NormalBorderedStyleEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvError: AppCompatTextView
    private lateinit var etField: AppCompatEditText

    private val attributes: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BorderedEditTextNormal, 0, 0)

    init {
        View.inflate(context, R.layout.bordered_edittext_normal, this)

        tvTitle = findViewById(R.id.tvTitle)
        tvError = findViewById(R.id.tvErrorTExt)
        etField = findViewById(R.id.etField)

        tvTitle.text = attributes.getString(R.styleable.BorderedEditTextNormal_title)
        tvError.text = attributes.getString(R.styleable.BorderedEditTextNormal_error)

        attributes.recycle()
    }

    fun setError(errorMessage: String) {
        tvTitle.setTextColor(Color.RED)
        tvError.visibility = View.VISIBLE
        tvError.setTextColor(Color.RED)
        etField.setBackgroundResource(R.drawable.bordered_roundbox_error)
    }
}