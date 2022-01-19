package com.example.androidpaintapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var ib_brush: ImageButton? = null
    private var mImageButtonCurrentPaint: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        ib_brush = findViewById(R.id.ib_brush)

        val linearLayoutPaintColors = findViewById<LinearLayout>(R.id.ll_paint_colors)

        mImageButtonCurrentPaint = linearLayoutPaintColors[2] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
        )

        drawingView?.setSizeForBrush(20.toFloat())

        ib_brush?.setOnClickListener {
            showBrushSizeChooserDialog()
        }


    }

    private fun showBrushSizeChooserDialog() {
        var brushDialog = Dialog(this)

        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush Size: ")

        val smallBtn = brushDialog.findViewById<ImageView>(R.id.ib_small_brush)
        val mediumBtn = brushDialog.findViewById<ImageView>(R.id.ib_medium_brush)
        val largeBtn = brushDialog.findViewById<ImageView>(R.id.ib_large_brush)

        smallBtn.setOnClickListener {
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        mediumBtn.setOnClickListener {
            drawingView?.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()

        }
        largeBtn.setOnClickListener {
            drawingView?.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()

        }

        brushDialog.show()
    }

    fun paintClicked(view: View) {
        if(view != mImageButtonCurrentPaint) {
            val imageButtom = view as ImageButton
            val colorTag = imageButtom.tag.toString()
            drawingView?.setColor(colorTag)
            imageButtom.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
            )
            mImageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )

            mImageButtonCurrentPaint = view
        }
    }
}