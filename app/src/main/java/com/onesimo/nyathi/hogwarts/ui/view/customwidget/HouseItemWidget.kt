package com.onesimo.nyathi.hogwarts.ui.view.customwidget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.util.setImageFromUrl
import kotlinx.android.synthetic.main.house_item_widget.view.*

class HouseItemWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val appearance = context.obtainStyledAttributes(attrs, R.styleable.HouseItemWidget)
        try {
            inflate(context, R.layout.house_item_widget, this)
        } finally {
            appearance.recycle()
        }
    }

    fun seHouseDetails(details: HouseDetails) {
        setImageFromUrl(details.imageUrl, house_image, this.context)
        house_name.text = details.name
        house_card.background = getDrawable(context, details.background)
    }

    class HouseDetails(
        val name: String,
        val imageUrl: String,
        val background: Int
    )
}