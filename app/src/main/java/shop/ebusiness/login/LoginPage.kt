package shop.ebusiness.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import shop.ebusiness.R

// Klasse für den Login
class LoginPage : AppCompatActivity() {
    var goToRegister: Button? = null
    var forgot: Button? = null
    var button2: Button? = null
    var mail: EditText? = null
    var password: EditText? = null
    var myDB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mail = findViewById<View>(R.id.login_mail) as EditText
        password = findViewById<View>(R.id.login_pw) as EditText
        myDB = DBHelper(this)

        // Link zur Registrierung
        goToRegister = findViewById<View>(R.id.button_register) as Button
        goToRegister!!.setOnClickListener { view: View ->
            val intent = Intent(view.context, RegisterPage::class.java)
            view.context.startActivity(intent)
        }


        // Link zum Passwortzurücksetzen
        forgot = findViewById<View>(R.id.button_forgot_password) as Button
        forgot!!.setOnClickListener { view: View ->
            val intent = Intent(view.context, ForgotPage::class.java)
            view.context.startActivity(intent)
        }

        // Button für Login
        button2 = findViewById<View>(R.id.action_login) as Button
        button2!!.setOnClickListener {
            val maill = mail!!.text.toString()
            val passwordd = password!!.text.toString()

            // Plausibilitätschecks
            if (maill == "") {
                Toast.makeText(this@LoginPage, "Enter email", Toast.LENGTH_SHORT).show()
            } else {
                val result = myDB!!.checkMail(maill)
                if (!result) {
                    // User existiert nicht
                    Toast.makeText(this@LoginPage, "Benutzer nicht gefunden.", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext, RegisterPage::class.java)
                    startActivity(intent)
                } else if (!myDB!!.checkEmailPassword(maill, passwordd)) {
                    // Nutzer und Passwort stimmen nicht überein
                    Toast.makeText(this@LoginPage, "Passwort ist inkorrekt.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Login erfolgreich
                    val intent = Intent(applicationContext, WelcomePage::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}