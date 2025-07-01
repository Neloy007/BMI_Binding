package com.example.bmi_binding

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.bmi_binding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //click on calculate button
        binding.calculateBtn.setOnClickListener {
            //text to string
            val weightText = binding.weightEt.text.toString()
            val heightText = binding.heightEt.text.toString()

            //Check for empty input
            if(weightText.isEmpty() || heightText.isEmpty()){
                Toast.makeText(this,"Please fill all the fields", Toast.LENGTH_SHORT).show()
            }

            try {
                //convert input into number to calculate
                val weight =weightText.toDouble()
                val height = heightText.toDouble()/100


                val bmi = weight/(height*height)

                //show result card
                binding.resultCard.isVisible = true

                //show bmi number
               binding.bmiRatio.text = String.format("%.2f",bmi)

                if (bmi <18.5) {
                        binding.bmiPosition.text = "Underweight"
                        binding.bmiRangeComment.text = "(Less than Normal)"
                        binding.bmiRatio.setTextColor(ContextCompat.getColor(this,R.color.Underweight))
                }
                else if( bmi>=18.5 && bmi<=24.9) {
                    binding.bmiPosition.text = "Healthy"
                    binding.bmiRangeComment.text = "(Normal)"
                    binding.bmiRatio.setTextColor(ContextCompat.getColor(this, R.color.Normal))
                }
                else if (bmi>= 25.0 && bmi <= 29.99){
                    binding.bmiPosition.text = "Overweight"
                    binding.bmiRangeComment.text = "(Not Normal)"
                    binding.bmiRatio.setTextColor(ContextCompat.getColor(this, R.color.Overweight))
                }
                else{
                    binding.bmiPosition.text = "Obese"
                    binding.bmiRangeComment.text = "(Obase 1)"
                    binding.bmiRatio.setTextColor(ContextCompat.getColor(this, R.color.Obese))
                }
            }
            catch (e: Exception){
                Toast.makeText(this,"Enter valid number only",Toast.LENGTH_SHORT).show()
            }
        }

    }
}