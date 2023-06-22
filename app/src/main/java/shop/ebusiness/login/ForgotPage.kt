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

// Klasse zum Passwortzurücksetzen
class ForgotPage : AppCompatActivity() {
    var name: EditText? = null
    var mail: EditText? = null
    var password: EditText? = null
    var passwordrepeat: EditText? = null
    var submit: Button? = null
    var goToLogin: Button? = null
    var MyDB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        // Speichern der Nutzereingabe
        name = findViewById<View>(R.id.username_set) as EditText
        mail = findViewById<View>(R.id.mail_set) as EditText
        password = findViewById<View>(R.id.pw_set) as EditText
        passwordrepeat = findViewById<View>(R.id.pw_confirm) as EditText
        val regex = "^(.+)@(.+)$"
        val pattern = Pattern.compile(regex)
        submit = findViewById<View>(R.id.button_resetPW) as Button
        MyDB = DBHelper(this)

        // Link zur LoginPage
        goToLogin = findViewById<View>(R.id.button_loginScreen) as Button
        goToLogin!!.setOnClickListener { view: View ->
            val intent = Intent(view.context, LoginPage::class.java)
            view.context.startActivity(intent)
        }

        // Button zum Passwortzurücksetzen
        submit!!.setOnClickListener { view: View? ->
            val namee = name!!.text.toString()
            val maill = mail!!.text.toString()
            val passwordd = password!!.text.toString()
            val passwordrepeatt = passwordrepeat!!.text.toString()
            val matcher = pattern.matcher(maill)

            // Plausibilitätschecks
            if (namee == "" || maill == "" || passwordd == "" || passwordrepeatt == "") {
                Toast.makeText(
                    this@ForgotPage,
                    "Bitte keines der Felder leer lassen.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!matcher.matches()) {
                Toast.makeText(this@ForgotPage, "Bitte valide E-Mail angeben", Toast.LENGTH_SHORT)
                    .show()
            } else if (passwordd != passwordrepeatt) {
                Toast.makeText(this@ForgotPage, "Bitte Passwort kontrollieren.", Toast.LENGTH_SHORT)
                    .show()
            } else if (passwordd.length < 8) {
                Toast.makeText(
                    this@ForgotPage,
                    "Das Passwort muss mindestens 8 Zeichen lang sein",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val result = MyDB!!.checkMail(maill)

                // Wenn Konto nicht existiert kann Passwort nicht zurückgesetzt werden
                if (!result) {
                    Toast.makeText(
                        this@ForgotPage,
                        "Dieses Konto existiert nicht!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val res = MyDB!!.replaceData(namee, maill, passwordd, passwordrepeatt)
                    if (res) {
                        Toast.makeText(
                            this@ForgotPage,
                            "Passwort zurückgesetzt.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@ForgotPage, LoginPage::class.java)
                        this@ForgotPage.startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@ForgotPage,
                            "Passwort konnte nicht zurückgesetzt werden.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}