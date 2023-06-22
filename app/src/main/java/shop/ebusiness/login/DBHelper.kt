package shop.ebusiness.login

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Funktionen für SQLite Datenbank Aufrufe
class DBHelper(context: Context?) : SQLiteOpenHelper(context, "register.db", null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        val crateTableStatement =
            "CREATE TABLE $CUSTOMER_TABLE ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_CUSTOMER_DATUM TEXT, $COLUMN_CUSTOMER_EISENGEHALT INT, $COLUMN_CUSTOMER_CHOLOSTERINGEHALT INT, $COLUMN_CUSTOMER_BLUTZUCKER INT, $COLUMN_CUSTOMER_TRIGLYCERIDE INT, $COLUMN_CUSTOMER_BLUTDRUCK INT, $COLUMN_CUSTOMER_VITAMIND INT,$COLUMN_CUSTOMER_VITAMINB_12 INT)"
        MyDB.execSQL("create Table person(name TEXT, mail TEXT primary key, password TEXT, passwordrepeat TEXT)")
        MyDB.execSQL(crateTableStatement)
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("drop Table if exists person")
    }

    // Funktionen zum Schreiben von Daten
    fun insertData(
        name: String?,
        mail: String?,
        password: String?,
        passwordrepeat: String?
    ): Boolean {
        val myDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("mail", mail)
        contentValues.put("password", password)
        contentValues.put("passwordrepeat", passwordrepeat)
        val result = myDB.insert("person", null, contentValues)
        return result != -1L
    }

    // Funktion zum überschreiben von Daten
    fun replaceData(
        name: String?,
        mail: String?,
        password: String?,
        passwordrepeat: String?
    ): Boolean {
        val myDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("mail", mail)
        contentValues.put("password", password)
        contentValues.put("passwordrepeat", passwordrepeat)
        val result = myDB.replace("person", null, contentValues)
        return result != -1L
    }

    // Funktion zum Finden existierender Konten
    fun checkMail(mail: String): Boolean {
        val myDB = this.writableDatabase
        val cursor = myDB.rawQuery("select * from person where mail = ?", arrayOf(mail))
        return cursor.count > 0
    }

    // Funktion für Passwortabgleich
    fun checkEmailPassword(mail: String, password: String): Boolean {
        val myDB = this.writableDatabase
        val cursor = myDB.rawQuery(
            "select * from person where mail = ? and password = ?",
            arrayOf(mail, password)
        )
        return cursor.count > 0
    }

    fun addOne(customerModel: CustomerModel): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_CUSTOMER_DATUM, customerModel.datum)
        cv.put(COLUMN_CUSTOMER_EISENGEHALT, customerModel.eisengehalt)
        cv.put(COLUMN_CUSTOMER_CHOLOSTERINGEHALT, customerModel.cholesteringehalt)
        cv.put(COLUMN_CUSTOMER_BLUTZUCKER, customerModel.blutzucker)
        cv.put(COLUMN_CUSTOMER_TRIGLYCERIDE, customerModel.triglyceride)
        cv.put(COLUMN_CUSTOMER_BLUTDRUCK, customerModel.blutdruck)
        cv.put(COLUMN_CUSTOMER_VITAMIND, customerModel.vitaminD)
        cv.put(COLUMN_CUSTOMER_VITAMINB_12, customerModel.vitaminB12)
        val insert = db.insert(CUSTOMER_TABLE, null, cv)
        return insert != -1L
    }

    companion object {
        const val CUSTOMER_TABLE = "CUSTOMER_TABLE"
        const val COLUMN_CUSTOMER_DATUM = "CUSTOMER_DATUM"
        const val COLUMN_CUSTOMER_EISENGEHALT = "CUSTOMER_EISENGEHALT"
        const val COLUMN_CUSTOMER_CHOLOSTERINGEHALT = "CUSTOMER_CHOLOSTERINGEHALT"
        const val COLUMN_CUSTOMER_BLUTZUCKER = "CUSTOMER_BLUTZUCKER"
        const val COLUMN_ID = "ID"
        const val COLUMN_CUSTOMER_TRIGLYCERIDE = "CUSTOMER_TRIGLYCER" + COLUMN_ID + "E"
        const val COLUMN_CUSTOMER_BLUTDRUCK = "CUSTOMER_BLUTDRUCK"
        const val COLUMN_CUSTOMER_VITAMIND = "CUSTOMER_VITAMIND"
        const val COLUMN_CUSTOMER_VITAMINB_12 = "CUSTOMER_VITAMINB12"
    }
}