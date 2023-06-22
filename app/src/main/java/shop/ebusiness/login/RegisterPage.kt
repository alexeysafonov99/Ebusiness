package shop.ebusiness.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import shop.ebusiness.R
import java.util.regex.Pattern

// Klasse für die Registrierungsseite
class RegisterPage : AppCompatActivity() {
    var name: EditText? = null
    var mail: EditText? = null
    var password: EditText? = null
    var passwordrepeat: EditText? = null
    var submit: Button? = null
    var goToLogin: Button? = null
    var myDB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Nutzereingabe speichern
        name = findViewById<View>(R.id.name) as EditText
        mail = findViewById<View>(R.id.mail) as EditText
        password = findViewById<View>(R.id.password) as EditText
        passwordrepeat = findViewById<View>(R.id.passwordrepeat) as EditText
        // Regex für valide Email
        val regex = "^(.+)@(.+)$"
        val pattern = Pattern.compile(regex)
        submit = findViewById<View>(R.id.action_register) as Button
        myDB = DBHelper(this)

        //Link zum Login
        goToLogin = findViewById<View>(R.id.button_loginScreen) as Button
        goToLogin!!.setOnClickListener { view: View ->
            val intent = Intent(view.context, LoginPage::class.java)
            view.context.startActivity(intent)
        }
        submit!!.setOnClickListener {
            val namee = name!!.text.toString()
            val maill = mail!!.text.toString()
            val passwordd = password!!.text.toString()
            val passwordrepeatt = passwordrepeat!!.text.toString()
            val matcher = pattern.matcher(maill)

            // Plausibilitätschecks
            if (namee == "" || maill == "" || passwordd == "" || passwordrepeatt == "") {
                Toast.makeText(
                    this@RegisterPage,
                    "Bitte keines der Felder leer lassen.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!matcher.matches()) {
                Toast.makeText(this@RegisterPage, "Bitte valide E-Mail angeben", Toast.LENGTH_SHORT)
                    .show()
            } else if (passwordd != passwordrepeatt) {
                Toast.makeText(
                    this@RegisterPage,
                    "Bitte Passwort kontrollieren.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (passwordd.length < 8) {
                Toast.makeText(
                    this@RegisterPage,
                    "Das Passwort muss mindestens 8 Zeichen lang sein",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val result = myDB!!.checkMail(maill)
                if (!result) {
                    // Benutzer existiert noch nicht -> Registrierung möglich
                    val res = myDB!!.insertData(namee, maill, passwordd, passwordrepeatt)
                    if (res) {
                        Toast.makeText(
                            this@RegisterPage,
                            "Registrierung erfolgreich!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@RegisterPage, BloodPage::class.java)
                        this@RegisterPage.startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@RegisterPage,
                            "Registrierung fehlgeschlagen!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // Benutzer existiert bereits
                    Toast.makeText(
                        this@RegisterPage,
                        "Dieses Konto existiert bereits.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@RegisterPage, LoginPage::class.java)
                    this@RegisterPage.startActivity(intent)
                }
            }
        }
    }
}