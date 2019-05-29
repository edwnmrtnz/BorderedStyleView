package com.github.edwnmrtz.borderedstyleedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo

class PrefixedBorderedStyleEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var tvFieldLabelTitle: AppCompatTextView
    private var tvAssistiveText: AppCompatTextView
    private var etField: AppCompatEditText

    private var titleTextColor : Int       = ContextCompat.getColor(context,R.color.greyish)
    private var assistiveTextColor: Int    = ContextCompat.getColor(context,R.color.greyish)
    private var assistiveText : String?    = ""
    private var llPrefixedContainer : LinearLayoutCompat
    private var prefixDivider : View

    private var tvPrefix : AppCompatTextView
    private var isError = false

    init {
        View.inflate(context, R.layout.bordered_edittext_prefixed, this)
        tvFieldLabelTitle       = findViewById(R.id.tvFieldLabelTitle)
        tvAssistiveText         = findViewById(R.id.tvAssistiveText)
        etField                 = findViewById(R.id.etField)
        tvPrefix                = findViewById(R.id.tvPrefix)
        llPrefixedContainer     = findViewById(R.id.llPrefixedContainer)
        prefixDivider           = findViewById(R.id.prefixDivider)

        val attributes: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PrefixedBorderedStyleEditText, 0, 0)
        val count = attributes.indexCount
        for (i in 0 until count) {
            val attr = attributes.getIndex(i)
            when (attr) {
                R.styleable.PrefixedBorderedStyleEditText_android_imeOptions -> {
                    etField.imeOptions =  attributes.getInt(attr, 0)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_inputType -> {
                    etField.inputType = attributes.getInt(R.styleable.NormalBorderedStyleEditText_android_inputType, EditorInfo.TYPE_TEXT_VARIATION_NORMAL)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_maxLines -> {
                    etField.maxLines = attributes.getInt(attr, 1000)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_maxLength -> {
                    etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(attributes.getInt(attr, 1000)))
                }
                R.styleable.PrefixedBorderedStyleEditText_assistiveText-> {
                    assistiveText = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_assistiveText)
                    tvAssistiveText.text = assistiveText
                    tvAssistiveText.visibility = View.VISIBLE                }
                R.styleable.PrefixedBorderedStyleEditText_fieldLabel -> {
                    tvFieldLabelTitle.text = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_fieldLabel)
//                    etField.hint = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_fieldLabel)
                    tvFieldLabelTitle.visibility = View.VISIBLE
                }
                R.styleable.PrefixedBorderedStyleEditText_assistiveTextColor -> {
                    assistiveTextColor = attributes.getColor(R.styleable.PrefixedBorderedStyleEditText_assistiveTextColor, assistiveTextColor)
                    tvAssistiveText.setTextColor(assistiveTextColor)
                }
                R.styleable.PrefixedBorderedStyleEditText_fieldLabelTextColor -> {
                    titleTextColor = attributes.getColor(R.styleable.PrefixedBorderedStyleEditText_fieldLabelTextColor, titleTextColor)
                    tvFieldLabelTitle.setTextColor(titleTextColor)
                }
                R.styleable.PrefixedBorderedStyleEditText_prefix -> {
                    tvPrefix.text = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_prefix)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_hint -> {
                    etField.hint = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_android_hint)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_textAppearance -> {
                    TextViewCompat.setTextAppearance(tvPrefix, attributes.getResourceId(attr, 0))
                    TextViewCompat.setTextAppearance(etField, attributes.getResourceId(attr, 0))
                }
                R.styleable.PrefixedBorderedStyleEditText_android_focusableInTouchMode -> {
                    etField.isFocusableInTouchMode = attributes.getBoolean(attr, true)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_clickable -> {
                    etField.isClickable = attributes.getBoolean(attr, true)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_focusable -> {
                    etField.isFocusable = attributes.getBoolean(attr, true)
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

//        setError("Wrong")
    }

    fun getText() : String {
        return etField.text.toString()
    }

    fun getEditText(): AppCompatEditText {
        return etField
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
        tvAssistiveText.visibility = View.VISIBLE
        tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.reddish_pink))
        llPrefixedContainer.setBackgroundResource(R.drawable.bordered_roundbox_error)
        prefixDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.reddish_pink))
        tvAssistiveText.text = errorMessage
    }

    fun removeError() {
        isError = false
        tvFieldLabelTitle.setTextColor(titleTextColor)
        llPrefixedContainer.setBackgroundResource(R.drawable.bordered_roundbox_active)
        prefixDivider.setBackgroundColor(Color.parseColor("#ebebeb"))
        tvAssistiveText.setTextColor(assistiveTextColor)

        if(assistiveText != null) {
            tvAssistiveText.visibility = View.VISIBLE
            tvAssistiveText.text = assistiveText
        } else {
            tvAssistiveText.visibility = View.GONE
        }
    }
    fun enable() {
        etField.isEnabled = true
        etField.setBackgroundResource(R.drawable.bordered_roundbox_active)
    }

    fun disable() {
        etField.isEnabled = false
        etField.setBackgroundResource(R.drawable.bordered_roundbox_disabled)
    }

    companion object {
        val TAG = "PrefixedBorderedStyle"
    }
}