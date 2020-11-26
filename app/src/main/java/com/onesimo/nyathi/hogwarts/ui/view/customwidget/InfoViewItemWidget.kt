package com.onesimo.nyathi.hogwarts.ui.view.customwidget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.onesimo.nyathi.hogwarts.R
import kotlinx.android.synthetic.main.info_item_widget.view.*

class InfoViewItemWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InfoViewItemWidget)
        try {
            inflate(context, R.layout.info_item_widget, this)
            title.text = typedArray.getString(R.styleable.InfoViewItemWidget_title_text)
            info.text = typedArray.getString(R.styleable.InfoViewItemWidget_info_text)
        } finally {
            typedArray.recycle()
        }
    }

    fun setInfo(infoText: String) {
        info.text = infoText
    }

    fun setTitle(titleText: String) {
        title.text = titleText
    }
}