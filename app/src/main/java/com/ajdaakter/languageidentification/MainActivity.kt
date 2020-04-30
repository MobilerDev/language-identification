package com.ajdaakter.languageidentification

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import java.util.*

class MainActivity : AppCompatActivity() {

    var viewLanguage: Button? = null
    var detectLanguage: TextView? = null
    var inputText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewLanguage = findViewById(R.id.btnDetect)
        detectLanguage = findViewById(R.id.txtShowLanguage)
        inputText = findViewById(R.id.eTxtLanguage)


        viewLanguage?.setOnClickListener(View.OnClickListener {
            val languageString = inputText?.getText().toString()
            if (!languageString.isEmpty()) {
                showLanguage(languageString)
            }
        })
    }

    private fun showLanguage(lan_str: String) {
        val languageIdentifier =
            FirebaseNaturalLanguage.getInstance().languageIdentification
        languageIdentifier.identifyLanguage(lan_str)
            .addOnSuccessListener { languageCode ->
                val output: String?
                if (languageCode !== "und") {
                    output = languageCode
                    val loc = Locale(output)
                    detectLanguage!!.text = loc.displayLanguage
                } else {
                    output = "Can't identify language."
                    detectLanguage!!.text = output
                }
            }
            .addOnFailureListener {

            }
    }

}