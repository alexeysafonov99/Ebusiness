package shop.ebusiness.product

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/**
 * Die DatabaseHelper-Klasse ist ein Helfer zum Erstellen und Aktualisieren der SQLite-Datenbank.
 * Sie enthält außerdem Methoden zur Durchführung von Read-Operationen
 *
 * @param context Der Kontext der Anwendung.
 * @param db_name Der Name der Datenbank.
 * @param null Das CursorFactory-Objekt.
 * @param db_version Die Versionsnummer der Datenbank.
 */
class DatabaseHelper(context: Context): SQLiteOpenHelper(context, db_name,null, db_version) {

    companion object{


         val db_name="Bloodrecommendation"
        private val db_version= 1

        //Tabellenwerte für Lebensmittel
        private val table_name1="Lebensmittel"
         val ID="LebensmittelId"
         val name="Lebensmittelname"
         val war="Warnung"
         val bes="Lebensmittelproduktbeschreibung"
         val pos1="PositiveWirkung1"
         val cat="Lebensmittelkategorie"
         val her="Hersteller"
         val kalo="Kalorien"
         val einh="Einheit"
         val anzahl="Anzahl"
         val einh_anz="EinheitAnzahl"
         val fettanteil="Fettanteil"
         val einh_fett="EinheitFettanteil"
         val fol="Folsäureanteil"
         val einh_fol="EinheitFolsäure"
         val vitc="VitaminCanteil"
         val einh_vitC="EinheitVitaminC"
         val vege="Vegetarisch"
         val veg="Vegan"
         val prot="Proteinreich"
         val fet="Fettarm"
         val kal="Kaloriearm"
         val pr="Preis"
         val einh_preis="EinheitPreis"

        //Tabellenwerte für Produkteffekt
        private val table_name2="Produkteffekt"
        private val lebensmittelID="ID"
        private val Blutwert_ID="BlutwertID"
        private val erh="Erhöhung"
        private val senk="Senkung"

        //Tabellenwerte für BlutwertReferenzTabelle
        private val table_name3="BlutwertReferenztabelle"
        private val b_id="BlutwertID"
        private val blutwert_Name="BlutwertName"
        private val mas="Maßeinheit"
        private val ref_m="ReferenzwerteMänner"
        private val ref_w="ReferenzwerteFrauen"

        //SQl-Statements für die Erstellung der Tabellen
        val CREATE_TABLE="CREATE TABLE IF NOT EXISTS $table_name1 ($ID INTEGER PRIMARY KEY,$anzahl INTEGER,$einh_anz TEXT, $name TEXT, $war TEXT, $bes TEXT, $pos1, $cat TEXT,$her TEXT,$kalo REAL,$einh TEXT,$fettanteil REAL, $einh_fett TEXT,$fol REAL, $einh_fol TEXT, $vitc REAL, $einh_vitC TEXT, $vege BOOLEAN,$veg BOOLEAN,$prot BOOLEAN,$fet BOOLEAN,$kal BOOLEAN,$pr REAL, $einh_preis TEXT)"
        val CREATE_TABLE2="CREATE TABLE IF NOT EXISTS $table_name2 ($lebensmittelID INTEGER,$Blutwert_ID INTEGER, $erh BOOLEAN,$senk BOOLEAN, FOREIGN KEY($lebensmittelID)REFERENCES $table_name1($ID),FOREIGN KEY($Blutwert_ID)REFERENCES $table_name3($b_id))"
        val CREATE_TABLE3="CREATE TABLE IF NOT EXISTS $table_name3 ($b_id INTEGER PRIMARY KEY, $blutwert_Name TEXT,$mas TEXT,$ref_m REAL,$ref_w REAL)"

        //SQl-Statements für die Wertebefüllung der Tabellen
        val INSERT_TABLE= "INSERT INTO $table_name1 VALUES(1,0, 'kg','Erdbeere','Enthält Fructose','Erdbeeren enthalten mehr abwehrstärkendes Vitamin C als Orangen.','Reich an Vitamin C','Früchte','Johns Biobauernhof',64, 'kcal',0.8,'Gramm', 130,'Microgramm', 124,'Miligramm', 1,1,0,1,1,2.10,'€/kg')"
        val INSERT_TABLE2= "INSERT INTO $table_name1 VALUES(2,0, 'Dose','Linsen','Enthält Oligosaccharide ','','Reich an Balaststoffen','Hülsenfrüchte','Rewe',309, 'kcal',1.4,'Gramm', 132 ,'Microgramm', 1 ,'Miligramm', 1,1,1,1,1,1.99,'€/Dose')"
        val INSERT_TABLE3= "INSERT INTO $table_name3 VALUES(1,'Eisengehalt','µg/dl',120,110)"
        val INSERT_TABLE4= "INSERT INTO $table_name2 VALUES(2,1,1,0)"

        //SQl-Statements für das Löschen der Tabellen
        val DROP_TABLE="DROP TABLE IF EXISTS $table_name1"
        val DROP_TABLE2="DROP TABLE IF EXISTS $table_name2"
        val DROP_TABLE3="DROP TABLE IF EXISTS $table_name3"
    }

    /**
     * Diese Funktion erstellt die Tabellen "Lebensmittel", "Produkteffekt" und "BlutwertReferenztabelle" in der SQLite-Datenbank.
     * @param db Variable vom Typ SQLiteDatabase?
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
        db?.execSQL(CREATE_TABLE2)
        db?.execSQL(CREATE_TABLE3)

        db?.execSQL(INSERT_TABLE)
        db?.execSQL(INSERT_TABLE2)
        db?.execSQL(INSERT_TABLE3)
        db?.execSQL(INSERT_TABLE4)
    }
    /**
     * Diese Funktion erneuert die Tabellen "Lebensmittel", "Produkteffekt" und "BlutwertReferenztabelle" in der SQLite-Datenbank, sofern es zu einer neueren Datenbank Version kommt.
     * @param db Variable vom Typ SQLiteDatabase?
     * @param oldVersion Variable vom Typ INT alte Version der Datenbank
     * @param newVersion Variable vom Typ INT neue Version der Datenbank
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        db?.execSQL(DROP_TABLE2)
        db?.execSQL(DROP_TABLE3)
        onCreate(db)
    }


    /**
     * Diese Funktion liest alle in der Datenbank gespeicherten Lebensmittel der Kategorie Früchte aus
     * @return gibt eine Liste mit den Lebensmitteln der Kategorie Früchte zurück
     */

    fun getAllFruits():List<ProductList>{
        val productList=ArrayList<ProductList>()
        val db= this.writableDatabase
        val selectQuery="SELECT * FROM $table_name1 WHERE $cat ='Früchte' "
        try{
            db.execSQL(selectQuery)
        }
        catch (e:SQLiteException){
            db.execSQL(CREATE_TABLE)
            db.execSQL(INSERT_TABLE)
        }
        val cursor: Cursor =db.rawQuery(selectQuery,null)
        if(cursor.moveToFirst()){
            do{
                productList.add(ProductList.fromCursor(cursor))

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productList

    }

    /**
     * Diese Funktion liest alle in der Datenbank gespeicherten Lebensmittel der Kategorie Hülsenfrüchte aus
     * @return gibt eine Liste mit den Lebensmitteln der Kategorie Hülsenfrüchte zurück
     */
    fun getAllPulse():List<ProductList>{
        val productList=ArrayList<ProductList>()
        val db:SQLiteDatabase=writableDatabase
        val selectQuery="SELECT * FROM $table_name1 WHERE $cat='Hülsenfrüchte' "
        val cursor: Cursor =db.rawQuery(selectQuery,null)
        if(cursor.moveToFirst()){
            do{
                productList.add(ProductList.fromCursor(cursor))

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productList
    }

    /**
     * Diese Funktion liest alle in der Datenbank gespeicherten Lebensmittel der Kategorie Gemüse aus
     * @return gibt eine Liste mit den Lebensmitteln der Kategorie Gemüse zurück
     */
    fun getAllVegetables():List<ProductList>{
        val productList=ArrayList<ProductList>()
        val db:SQLiteDatabase=writableDatabase
        val selectQuery="SELECT * FROM $table_name1 WHERE $cat='Gemüse' "
        val cursor: Cursor =db.rawQuery(selectQuery,null)
        if(cursor.moveToFirst()){
            do{
                productList.add(ProductList.fromCursor(cursor))

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productList
    }

    /**
     * Diese Funktion liest alle in der Datenbank gespeicherten Lebensmittel aus
     * @return gibt eine Liste mit allen in der Datenbank gespeicherten Lebensmitteln
     */
    fun getAllProducts():ArrayList<ProductList>{
        val productList=ArrayList<ProductList>()
        val db: SQLiteDatabase = writableDatabase
        val selectQuery = "SELECT * FROM $table_name1"
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {

            do {
                productList.add(ProductList.fromCursor(cursor))

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return productList
    }

    /**
     * Diese Funktion sucht basierend auf einem Keywort Lebensmittel in der Datenbank
     * @param keyword ein Zeichen/Wort als Parameter
     * @return gibt eine Liste mit den Lebensmitteln zurück, welche dem Keyword ähneln
     */
    fun search(keyword: String): List<ProductList?>? {
        var products: MutableList<ProductList?>? = null

        val sqLiteDatabase = writableDatabase
        val cursor = sqLiteDatabase.rawQuery(
            "select * from $table_name1 where $name like ?", arrayOf(
                "%$keyword%"
            )
        )
        if (cursor.moveToFirst()) {
            products = ArrayList()
            do {
                products.add(ProductList.fromCursor(cursor))
            } while (cursor.moveToNext())
        }

        cursor.close()
        sqLiteDatabase.close()
        return products
    }

    //TODO:IMPLEMENTATION
    /*
    fun getAllRecommendations():List<shop.ebusiness.product.ProductList>{}
    fun getAllLastOrdered():List<shop.ebusiness.product.ProductList>{}

    */

}

