package com.example2.slots

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity(), EventEnd {

    private var countDown = 0
    lateinit var fab:Button
    lateinit var slot1: SlotScroll
    lateinit var slot2: SlotScroll
    lateinit var slot3: SlotScroll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        fab = findViewById(R.id.button)
        slot1 = findViewById(R.id.image1)
        slot2 = findViewById(R.id.image2)
        slot3 = findViewById(R.id.image3)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        slot1.setEventEnd(this@MainActivity)
        slot2.setEventEnd(this@MainActivity)
        slot3.setEventEnd(this@MainActivity)

        fab.setOnClickListener{
                fab.isEnabled = false
            slot1.setRandomValue(Random.nextInt(6), Random.nextInt(15,40))
            slot2.setRandomValue(Random.nextInt(6), Random.nextInt(15,40))
            slot3.setRandomValue(Random.nextInt(6), Random.nextInt(15,40))
        }
    }

    override fun eventEnd(result: Int, count: Int) {
        if(countDown < 2){
            countDown++
        }
        else{
            fab.isEnabled = true
            countDown = 0

            if(slot1.value == slot2.value && slot2.value == slot3.value){
                Toast.makeText(this,"YOU WON!!!!", Toast.LENGTH_SHORT).show()
                Utils.score +=300

            }
            else if(slot1.value == slot2.value || slot2.value == slot3.value || slot1.value == slot3.value){
                Toast.makeText(this,"You did good.", Toast.LENGTH_SHORT).show()
                Utils.score +=100

            }
            else{
                Toast.makeText(this,"You lost. Better luck next time.", Toast.LENGTH_SHORT).show()
                Utils.score +=0

            }
        }
    }
}
