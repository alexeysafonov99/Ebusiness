package shop.ebusiness.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import shop.ebusiness.R
import shop.ebusiness.home.HomeActivity

class BloodPage : AppCompatActivity() {
    //references to buttons amd otter controls on the layouts
    var btn_add: Button? = null
    var et_datum: EditText? = null
    var et_eisengehalt: EditText? = null
    var et_cholesteringehalt: EditText? = null
    var et_blutzucker: EditText? = null
    var et_triglyceride: EditText? = null
    var et_blutdruck: EditText? = null
    var et_vitamind: EditText? = null
    var et_vitaminb12: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood)
        btn_add = findViewById(R.id.btn_add)
        et_datum = findViewById(R.id.et_datum)
        et_eisengehalt = findViewById(R.id.et_eisengehalt)
        et_cholesteringehalt = findViewById(R.id.et_cholesteringehalt)
        et_blutzucker = findViewById(R.id.et_blutzucker)
        et_triglyceride = findViewById(R.id.et_triglyceride)
        et_blutdruck = findViewById(R.id.et_blutdruck)
        et_vitamind = findViewById(R.id.et_vitamind)
        et_vitaminb12 = findViewById(R.id.et_vitaminb12)

        //button listeners for the add and view all buttons
        btn_add!!.setOnClickListener { v: View ->
            var customerModel: CustomerModel
            try {
                customerModel = CustomerModel(
                    -1,
                    et_datum!!.text.toString(),
                    et_eisengehalt!!.text.toString().toInt(),
                    et_cholesteringehalt!!.text.toString().toInt(),
                    et_blutzucker!!.text.toString().toInt(),
                    et_triglyceride!!.text.toString().toInt(),
                    et_blutdruck!!.text.toString().toInt(),
                    et_vitamind!!.text.toString().toInt(),
                    et_vitaminb12!!.text.toString().toInt()
                )
                Toast.makeText(this@BloodPage, customerModel.toString(), Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@BloodPage, "Error Creating Customer", Toast.LENGTH_SHORT).show()
                customerModel = CustomerModel(-1, "error", 0, 0, 0, 0, 0, 0, 0)
            }
            val dataBaseHelper = DBHelper(this@BloodPage)
            val success = dataBaseHelper.addOne(customerModel)
            Toast.makeText(this@BloodPage, "Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(v.context, HomeActivity::class.java)
            v.context.startActivity(intent)
        }
    }
}