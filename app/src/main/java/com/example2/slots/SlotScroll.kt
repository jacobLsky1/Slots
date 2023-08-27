package com.example2.slots

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import kotlinx.android.synthetic.main.slot_image_scroll.view.*

class SlotScroll: FrameLayout {

    internal lateinit var eventEnd: EventEnd
    internal var lastResult = 0
    internal var oldValue = 0

    companion object {
        private const val ANIMATION_DURATION = 100
    }

    val value: Int
        get() = Integer.parseInt(nextImage.tag.toString())

    fun setEventEnd(eventEnd: EventEnd){
        this.eventEnd = eventEnd
    }

    constructor(context: Context) : super(context){
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init(context)
    }

    private fun init(context: Context){
        LayoutInflater.from(context).inflate(R.layout.slot_image_scroll, this)
        nextImage.translationY = height.toFloat()
    }

    fun setRandomValue(image:Int, numRoll:Int){
        currentImage.animate()
            .translationY(-height.toFloat())
            .setDuration(ANIMATION_DURATION.toLong()).start()

        nextImage.translationY = nextImage.height.toFloat()
        nextImage.animate()
            .translationY(0f).setDuration(ANIMATION_DURATION.toLong())
            .setListener(object: Animator.AnimatorListener{
                override fun onAnimationRepeat(p0: Animator?) {
                }

                override fun onAnimationEnd(p0: Animator?) {
                    setImage(currentImage, oldValue%6)
                    currentImage.translationY = 0f
                    if(oldValue != numRoll) {
                        setRandomValue(image,numRoll)
                        oldValue++
                    }
                    else {
                        lastResult = 0
                        oldValue = 0
                        setImage(nextImage, image)
                        eventEnd.eventEnd(image%6, numRoll)

                    }

                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {
                }

            }).start()

    }

    //!! symbol is for asserting non-null to variables
    private fun setImage(currentImage: ImageView?, value: Int){
        when (value) {
            Utils.aroma -> currentImage!!.setImageResource(R.drawable.aromapng)
            Utils.jerusalem -> currentImage!!.setImageResource(R.drawable.jerusalempng)
            Utils.telaviv -> currentImage!!.setImageResource(R.drawable.telavivpng)
            Utils.nightclub -> currentImage!!.setImageResource(R.drawable.nightcliubpng)
            Utils.food -> currentImage!!.setImageResource(R.drawable.foodpng)
            Utils.negev -> currentImage!!.setImageResource(R.drawable.negevpng)
        }

        currentImage!!.tag = value
        lastResult = value
    }



}