package com.example.myapplication
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class SwipeListener(private val listView: ListView) : View.OnTouchListener {
    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(listView.context, GestureListener())
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let {
            return gestureDetector.onTouchEvent(it)
        }
        return false
    }


    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = e2!!.x - e1!!.x
            val diffY = e2.y - e1.y
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        deleteItem(listView.pointToPosition(e1.x.toInt(), e1.y.toInt()))
                    } else {
                        deleteItem(listView.pointToPosition(e1.x.toInt(), e1.y.toInt()))
                    }
                    return true
                }
            }
            return false
        }

        private fun deleteItem(position: Int) {
            // Remove item from the list at 'position'
            (listView.adapter as ArrayAdapter<String>).remove(listView.getItemAtPosition(position) as String)
        }
    }
}
