package com.github.edwnmrtz.borderedstyleedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat

class PrefixedBorderedStyleEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var tvFieldLabelTitle: AppCompatTextView
    private var tvAssistiveText: AppCompatTextView
    private var etField: AppCompatEditText

    private var titleTextColor: Int = ContextCompat.getColor(context, R.color.greyish)
    private var assistiveTextColor: Int = ContextCompat.getColor(context, R.color.greyish)
    private var assistiveText: String? = ""

    private var tvPrefix : AppCompatTextView
    private var isError = false

    private var textColorHint: Int = ContextCompat.getColor(context, R.color.greyish)

    private var clPrefixedContainer : ConstraintLayout
    private var prefixDivider : View

    init {

        View.inflate(context, R.layout.bordered_edittext_prefixed, this)
        tvFieldLabelTitle       = findViewById(R.id.tvFieldLabelTitle)
        tvAssistiveText         = findViewById(R.id.tvAssistiveText)
        tvPrefix                = findViewById(R.id.tvPrefix)
        etField                 = findViewById(R.id.etField)
        clPrefixedContainer     = findViewById(R.id.clEditText)
        prefixDivider           = findViewById(R.id.prefixDivider)

        val attributes: TypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.PrefixedBorderedStyleEditText, 0, 0)

        val count = attributes.indexCount
        for (i in 0 until count) {
            when (val attr = attributes.getIndex(i)) {
                R.styleable.PrefixedBorderedStyleEditText_android_imeOptions -> {
                    etField.imeOptions = attributes.getInt(attr, 0)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_inputType -> {
                    etField.inputType = attributes.getInt(
                        R.styleable.PrefixedBorderedStyleEditText_android_inputType,
                        EditorInfo.TYPE_TEXT_VARIATION_NORMAL
                    )
                }
                R.styleable.PrefixedBorderedStyleEditText_android_maxLines -> {
                    etField.maxLines = attributes.getInt(attr, 1000)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_maxLength -> {
                    etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(attributes.getInt(attr, 1000)))
                }
                R.styleable.PrefixedBorderedStyleEditText_assistiveText -> {
                    assistiveText = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_assistiveText)
                    tvAssistiveText.text = assistiveText
                    tvAssistiveText.visibility = View.VISIBLE
                }
                R.styleable.PrefixedBorderedStyleEditText_fieldLabel -> {
                    tvFieldLabelTitle.text = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_fieldLabel)
                    tvFieldLabelTitle.visibility = View.VISIBLE
                }
                R.styleable.PrefixedBorderedStyleEditText_assistiveTextColor -> {
                    assistiveTextColor = attributes.getColor(
                        R.styleable.PrefixedBorderedStyleEditText_assistiveTextColor,
                        assistiveTextColor
                    )
                    tvAssistiveText.setTextColor(assistiveTextColor)
                }
                R.styleable.PrefixedBorderedStyleEditText_fieldLabelTextColor -> {
                    titleTextColor = attributes.getColor(
                        R.styleable.PrefixedBorderedStyleEditText_fieldLabelTextColor,
                        titleTextColor
                    )
                    tvFieldLabelTitle.setTextColor(titleTextColor)
                }
                R.styleable.PrefixedBorderedStyleEditText_android_hint -> {
                    etField.hint = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_android_hint)
                }

                R.styleable.PrefixedBorderedStyleEditText_android_textAppearance -> {
                    val appearance = attributes.getResourceId(attr, 0)
                    TextViewCompat.setTextAppearance(etField, appearance)
                }
                R.styleable.PrefixedBorderedStyleEditText_prefix -> {
                    tvPrefix.text = attributes.getString(R.styleable.PrefixedBorderedStyleEditText_prefix)
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
                R.styleable.PrefixedBorderedStyleEditText_android_textColorHint -> {
                    textColorHint = attributes.getColor(R.styleable.PrefixedBorderedStyleEditText_android_textColorHint, textColorHint)
                    etField.setHintTextColor(textColorHint)
                }
            }
        }
        attributes.recycle()

        textChangeListener()
    }

    private fun textChangeListener() {
        etField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (isError) {
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

    fun setText(text : String) {
        etField.setText(text)
    }

    fun setFieldLabel(label : String) {
        tvFieldLabelTitle.text = label
        tvFieldLabelTitle.visibility = View.VISIBLE
    }

    fun enable() {
        etField.isEnabled = true
        clPrefixedContainer.setBackgroundResource(R.drawable.bordered_roundbox_active)
    }

    fun disable() {
        etField.isEnabled = false
        clPrefixedContainer.setBackgroundResource(R.drawable.bordered_roundbox_disabled)
    }

    fun setError(errorMessage: String) {
        isError = true
        tvFieldLabelTitle.setTextColor(ContextCompat.getColor(context, R.color.reddish_pink))
        tvAssistiveText.visibility = View.VISIBLE
        tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.reddish_pink))
        clPrefixedContainer.setBackgroundResource(R.drawable.bordered_roundbox_error)
        prefixDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.reddish_pink))
        tvAssistiveText.text = errorMessage
    }

    fun removeError() {
        isError = false
        tvFieldLabelTitle.setTextColor(titleTextColor)
        tvAssistiveText.setTextColor(assistiveTextColor)
        clPrefixedContainer.setBackgroundResource(R.drawable.bordered_roundbox_active)
        prefixDivider.setBackgroundColor(Color.parseColor("#ebebeb"))
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

}