package shop.ebusiness.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import shop.ebusiness.R

// Klasse für den Willkommen Screen
class WelcomePage : AppCompatActivity() {
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Link zur Registrierung
        button = findViewById<View>(R.id.button) as Button
        button.setOnClickListener { view: View ->
            val intent = Intent(view.context, RegisterPage::class.java)
            view.context.startActivity(intent)
        }
    }
}