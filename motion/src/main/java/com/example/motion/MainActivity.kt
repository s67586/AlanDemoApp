package com.example.motion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val set = motionLayout.getConstraintSet(R.id.end)
        set.setRotation(R.id.imageView, ((Math.random() * 3600)).toFloat())
        motionLayout.updateState(R.id.end, set)


        view.setOnClickListener {
            motionLayout.transitionToStart()
            val set = motionLayout.getConstraintSet(R.id.end)
            set.setRotation(R.id.imageView, ((Math.random() * 3600)).toFloat())
            motionLayout.updateState(R.id.end, set)

        }


        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                Log.e("TAG","onTransitionTrigger")
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                Log.e("TAG","onTransitionStarted")
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                Log.e("TAG","onTransitionChange")
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                Log.e("TAG","onTransitionCompleted")
            }
        })
    }
}
