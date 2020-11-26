package com.onesimo.nyathi.hogwarts.ui.view.customwidget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.onesimo.nyathi.hogwarts.R
import kotlinx.android.synthetic.main.character_detail_item_widget.view.*

class CharacterDetailItemWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CharacterDetailItemWidget)
        try {
            inflate(context, R.layout.character_detail_item_widget, this)
            label.text = typedArray.getString(R.styleable.CharacterDetailItemWidget_label_text)
            detail.text = typedArray.getString(R.styleable.CharacterDetailItemWidget_detail_text)
        } finally {
            typedArray.recycle()
        }
    }

    fun setDetails(detailText: String) {
        detail.text = detailText
    }

    fun setLabel(labelText: String) {
        label.text = labelText
    }
}