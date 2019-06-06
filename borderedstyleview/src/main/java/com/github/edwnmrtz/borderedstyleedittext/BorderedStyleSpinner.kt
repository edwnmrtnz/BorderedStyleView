package com.github.edwnmrtz.borderedstyleedittext

import android.content.Context
import android.content.res.TypedArray
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.View

class BorderedStyleSpinner (context : Context, attrs : AttributeSet) : ConstraintLayout(context, attrs) {

    private var tvFieldLabelTitle : AppCompatTextView
    private var tvAssistiveText : AppCompatTextView
    private var spinner : AppCompatSpinner

    private var titleTextColor : Int = ContextCompat.getColor(context,R.color.greyish)
    private var assistiveTextColor: Int    = ContextCompat.getColor(context,R.color.greyish)
    private var assistiveText : String?    = ""

    private var isError = false
    private var etField : AppCompatEditText

    init {
        View.inflate(context, R.layout.bordered_spinner, this)
        tvFieldLabelTitle = findViewById(R.id.tvFieldLabelTitle)
        tvAssistiveText = findViewById(R.id.tvAssistiveText)
        spinner = findViewById(R.id.spinner)
        etField = findViewById(R.id.etField)

        val attributes: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BorderedStyleSpinner, 0, 0)

        val count = attributes.indexCount
        for (i in 0 until count) {
            when (val attr = attributes.getIndex(i)) {
                R.styleable.BorderedStyleSpinner_fieldLabel -> {
                    tvFieldLabelTitle.text = attributes.getString(R.styleable.BorderedStyleSpinner_fieldLabel)
                    tvFieldLabelTitle.visibility = View.VISIBLE
                }
                R.styleable.BorderedStyleSpinner_fieldLabelTextColor -> {
                    titleTextColor = attributes.getColor(R.styleable.NormalBorderedStyleEditText_fieldLabelTextColor, titleTextColor)
                    tvFieldLabelTitle.setTextColor(titleTextColor)
                }
                R.styleable.BorderedStyleSpinner_android_textAppearance -> {
                    TextViewCompat.setTextAppearance(etField, attributes.getResourceId(attr, 0))
                }
                R.styleable.BorderedStyleSpinner_assistiveText -> {
                    assistiveText = attributes.getString(R.styleable.BorderedStyleSpinner_assistiveText)
                    tvAssistiveText.text = assistiveText
                    tvAssistiveText.visibility = View.VISIBLE
                }
                R.styleable.BorderedStyleSpinner_assistiveTextColor -> {
                    assistiveTextColor = attributes.getColor(R.styleable.BorderedStyleSpinner_assistiveTextColor, assistiveTextColor)
                    tvAssistiveText.setTextColor(assistiveTextColor)
                }
            }
        }
        attributes.recycle()
    }

    fun getSpinner() : AppCompatSpinner {
        return spinner;
    }

    fun setText(text : String) {
        etField.setText(text)
    }

    fun setFieldLabel(label : String) {
        tvFieldLabelTitle.text = label
        tvFieldLabelTitle.visibility = View.VISIBLE
    }

    fun setError(errorMessage: String) {
        isError = true
        tvFieldLabelTitle.setTextColor(ContextCompat.getColor(context, R.color.reddish_pink))
        etField.setBackgroundResource(R.drawable.bordered_roundbox_error)
    }

    fun removeError() {
        tvFieldLabelTitle.setTextColor(titleTextColor)
        etField.setBackgroundResource(R.drawable.bordered_roundbox_active)
    }

    fun enable() {
        etField.isEnabled = true
        etField.setBackgroundResource(R.drawable.bordered_roundbox_active)
    }

    fun disable() {
        etField.isEnabled = false
        etField.setBackgroundResource(R.drawable.bordered_roundbox_disabled)
    }

}