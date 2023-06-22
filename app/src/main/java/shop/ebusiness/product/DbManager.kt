package shop.ebusiness.product

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

/**
 * Diese Klasse verwaltet die Datenbank.
 * @param c der Kontext der Anwendung
 */
class DbManager(c: Context) {
    private lateinit var dbHelper: DatabaseHelper
    private var context: Context = c
    private lateinit var  database: SQLiteDatabase

    /**
     * Öffnet die Datenbank.
     * @throws SQLException falls die Datenbank nicht geöffnet werden kann
     * @return gibt eine Instanz von DbManager zurück
     */
    @Throws(SQLException::class)
    fun open(): DbManager? {
        dbHelper = DatabaseHelper(context)
        database = dbHelper.writableDatabase
        return this
    }

    /**
     * Schließt die Datenbank.
     */
    fun close() {
        dbHelper.close()
    }

    /**
     * Gibt alle Früchte zurück.
     * @return gibt eine Liste von ProductList-Objekten zurück
     */
    fun getAllFruits(): List<ProductList> {
        return dbHelper.getAllFruits()
    }

    fun getAllVegetables(): List<ProductList> {
        return dbHelper.getAllVegetables()
    }

    /**
     * Gibt alle Hülsenfrüchte zurück.
     * @return gibt eine Liste von ProductList-Objekten zurück
     */
    fun getAllPulse(): List<ProductList> {
        return dbHelper.getAllPulse()
    }

    /**
     * Gibt alle Produkte zurück.
     * @return gibt eine ArrayList von ProductList-Objekten zurück
     */
    fun getAllProducts(): ArrayList<ProductList> {
        return dbHelper.getAllProducts()
    }

    /**
     * Sucht nach einem Keyword in der Datenbank.
     * @param keyword das Keyword, nach dem gesucht werden soll
     * @return gibt eine Liste von ProductList-Objekten zurück oder null, falls nichts gefunden wurde
     */
    fun search(keyword: String): List<ProductList?>? {
        return dbHelper.search(keyword)
    }

}
