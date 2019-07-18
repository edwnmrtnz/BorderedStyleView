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
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView

class BorderedStyleSpinner (context : Context, attrs : AttributeSet) : ConstraintLayout(context, attrs) {

    private var tvFieldLabelTitle : AppCompatTextView
    private var tvAssistiveText : AppCompatTextView
    private var ivDropDown : AppCompatImageView
    private var spinner : AppCompatSpinner
    private var flField : FrameLayout

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
        ivDropDown = findViewById(R.id.ivDropDown)
        flField     = findViewById(R.id.flField)
        etField.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return false
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {

            }

        }

        ivDropDown.setOnClickListener {
            spinner.performClick()
        }
        ivDropDown.setOnLongClickListener {
            spinner.performLongClick()
        }

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
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                if(isError) {
                    removeError()
                    isError = false
                }
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(isError) {
                    removeError()
                    isError = false
                }
            }

        }

        attributes.recycle()
    }

    fun getSpinner() : AppCompatSpinner {
        return spinner
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
        flField.setBackgroundResource(R.drawable.bordered_roundbox_error)
        tvAssistiveText.text = errorMessage
    }

    fun removeError() {
        isError = false
        tvFieldLabelTitle.setTextColor(titleTextColor)
        flField.setBackgroundResource(R.drawable.bordered_roundbox_active)
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
        flField.setBackgroundResource(R.drawable.bordered_roundbox_active)
    }

    fun disable() {
        etField.isEnabled = false
        flField.setBackgroundResource(R.drawable.bordered_roundbox_disabled)
    }

}