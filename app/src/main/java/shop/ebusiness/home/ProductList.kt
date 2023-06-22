package shop.ebusiness.home

import android.database.Cursor
import shop.ebusiness.home.DatabaseHelper_m.Companion.ID
import shop.ebusiness.home.DatabaseHelper_m.Companion.anzahl
import shop.ebusiness.home.DatabaseHelper_m.Companion.bes
import shop.ebusiness.home.DatabaseHelper_m.Companion.cat
import shop.ebusiness.home.DatabaseHelper_m.Companion.einh
import shop.ebusiness.home.DatabaseHelper_m.Companion.einh_anz
import shop.ebusiness.home.DatabaseHelper_m.Companion.einh_fett
import shop.ebusiness.home.DatabaseHelper_m.Companion.einh_fol
import shop.ebusiness.home.DatabaseHelper_m.Companion.einh_preis
import shop.ebusiness.home.DatabaseHelper_m.Companion.einh_vitC
import shop.ebusiness.home.DatabaseHelper_m.Companion.fettanteil
import shop.ebusiness.home.DatabaseHelper_m.Companion.fol
import shop.ebusiness.home.DatabaseHelper_m.Companion.her
import shop.ebusiness.home.DatabaseHelper_m.Companion.kalo
import shop.ebusiness.home.DatabaseHelper_m.Companion.name
import shop.ebusiness.home.DatabaseHelper_m.Companion.pos1
import shop.ebusiness.home.DatabaseHelper_m.Companion.pr
import shop.ebusiness.home.DatabaseHelper_m.Companion.veg
import shop.ebusiness.home.DatabaseHelper_m.Companion.vege
import shop.ebusiness.home.DatabaseHelper_m.Companion.vitc
import shop.ebusiness.home.DatabaseHelper_m.Companion.war
import shop.ebusiness.product.DatabaseHelper
import shop.ebusiness.product.ProductList


/**
 * Die ProductList-Klasse ist eine Model-Klasse. Sie nimmt die in der Datenbank definierten Werte und fungiert als zusätzliche Schicht, mit welcher geabeitet werden kann
 *
 * @param ID Die Lebensmittel-Id
 * @param name Der Name des Lebensmittels
 * @param cat Die Lebensmittelkategorie
 * @param her Der Lebensmittelhersteller
 * @param war Die Warnung über Inhaltstoffe
 * @param bes Die Lebensmittelbeschreibung
 * @param pos1 Die positive Wirkung des Lebensmittels
 * @param kalo Die im Lebensmittel enthaltenen Kalorien
 * @param einh Die Einheit der Kalorien
 * @param anz Die Anzahl der Lebensmittel
 * @param einh_anz Die Einheit der Anzahl der Lebensmittel
 * @param fettanteil Der Fettanteil im Lebensmittel
 * @param einh_fett Die Einheit des Fettanteils des Lebensmittels
 * @param fol Der Folsäureanteil im Lebensmittels
 * @param einh_fol Die Einheit des Folsäureanteils im Lebensmittel
 * @param vitc Der Vitamin C Gehalt im Lebensmittel
 * @param einh_vitC Die Einheit des Vitamin C-Anteils im Lebensmittel
 * @param vege Ob das Lebensmittel vegetarisch ist
 * @param veg Ob das Lebensmittel vegan ist
 * @param prot Ob das Lebensmittel proteinreich ist
 * @param fet Ob das Lebensmittel fettarm ist
 * @param kal Ob das Lebensmittel kaloriearm ist
 * @param pr Der Preis des Lebensmittels
 * @param einh_preis Die Einheit des Preises des Lebensmittels
 */
class ProductList(var ID:Int,
        var name:String,
        var cat:String,
        var her:String,
        var war:String,
        var bes:String,
        var pos1:String,
        var kalo:Double,
        var einh:String,
        var anz:Int,
        var einh_anz:String,
        var fettanteil :Double,
        var einh_fett:String,
        var fol:Double,
        var einh_fol:String,
        var vitc:Double,
        var einh_vitC:String,
        var vege:Boolean,
        var veg:Boolean,
        var prot:Boolean,
        var fet:Boolean,
        var kal:Boolean,
        var pr:Double,
        var einh_preis:String) {


            companion object{

            //lateinit var dbh:DatabaseHelper
                /**
                 * Diese Funktion liest die in der Datenbank gespeicherten Lebensmittel aus .
                 * @param cursor ein Cursor, welcher spaltenweise die Daten ausliest
                 * @return eine Instanz der Klasse ProductList, deren im Konstruktor defiierten Variablen die in der Datenbank ausgelesenen Werte zugewiesen wurden.
                 */
            fun fromCursor(cursor: Cursor): ProductList {
                    val ID = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(name))
                    val cat = cursor.getString(cursor.getColumnIndexOrThrow(cat))
                    val her = cursor.getString(cursor.getColumnIndexOrThrow(her))
                    val war = cursor.getString(cursor.getColumnIndexOrThrow(war))
                    val bes = cursor.getString(cursor.getColumnIndexOrThrow(bes))
                    val pos1 = cursor.getString(cursor.getColumnIndexOrThrow(pos1))
                    val kalo = cursor.getDouble(cursor.getColumnIndexOrThrow(kalo))
                    val einh = cursor.getString(cursor.getColumnIndexOrThrow(einh))
                    val anz = cursor.getInt(cursor.getColumnIndexOrThrow(anzahl))
                    val einh_anz= cursor.getString(cursor.getColumnIndexOrThrow(einh_anz))
                    val fettanteil= cursor.getDouble(cursor.getColumnIndexOrThrow(fettanteil))
                    val einh_fett= cursor.getString(cursor.getColumnIndexOrThrow(einh_fett))
                    val fol= cursor.getDouble(cursor.getColumnIndexOrThrow(fol))
                    val einh_fol= cursor.getString(cursor.getColumnIndexOrThrow(einh_fol))
                    val vitc= cursor.getDouble(cursor.getColumnIndexOrThrow(vitc))
                    val einh_vitC= cursor.getString(cursor.getColumnIndexOrThrow(einh_vitC))
                    val vege= intToBool(cursor.getInt(cursor.getColumnIndexOrThrow(vege)))
                    val veg= intToBool(cursor.getInt(cursor.getColumnIndexOrThrow(veg)))
                    val prot= intToBool(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.prot)))
                    val fet= intToBool(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.fet)))
                    val kal= intToBool(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.kal)))
                    val pr = cursor.getDouble(cursor.getColumnIndexOrThrow(pr))
                    val einh_preis = cursor.getString(cursor.getColumnIndexOrThrow(einh_preis))




                return ProductList( ID,name, cat, her, war, bes, pos1, kalo, einh, anz, einh_anz, fettanteil, einh_fett, fol, einh_fol,vitc, einh_vitC, vege, veg, prot, fet, kal, pr, einh_preis)
                }

                    /**
                     * Diese Funktion dient als Helferfunktion zur Konvertierung eines Integers zu einem Boolean
                     * @param value Wert einer Variable vom Int
                     * @return gibt den Boolean Wert des Integers zurück
                     */ fun intToBool(value: Int): Boolean {
                            return value == 1
                    }
            }

}





/*val productList: List<ProductList> = listOf(
    ProductList().apply {
        ID = 1
        name = "Produkt 1"
        cat = "Kategorie 1"
        her = "Herkunft 1"
        war = "Warnhinweis 1"
        pos1 = "Position 1"
        vege = true
        veg = false
        prot = true
        fet = false
        kal = true
        pr = 10.0
    },
    ProductList().apply {
        ID = 2
        name = "Produkt 2"
        cat = "Kategorie 2"
        her = "Herkunft 2"
        war = "Warnhinweis 2"
        pos1 = "Position 2"
        vege = true
        veg = true
        prot = false
        fet = true
        kal = false
        pr = 20.0
    },
    ProductList().apply {
        ID = 3
        name = "Produkt 3"
        cat = "Kategorie 1"
        her = "Herkunft 1"
        war = "Warnhinweis 1"
        pos1 = "Position 3"
        vege = false
        veg = true
        prot = true
        fet = true
        kal = false
        pr = 30.0
    },
    ProductList().apply {
        ID = 4
        name = "Produkt 4"
        cat = "Kategorie 2"
        her = "Herkunft 2"
        war = "Warnhinweis 2"
        pos1 = "Position 4"
        vege = true
        veg = false
        prot = false
        fet = false
        kal = true
        pr = 40.0
    },
    ProductList().apply {
        ID = 5
        name = "Produkt 5"
        cat = "Kategorie 3"
        her = "Herkunft 3"
        war = "Warnhinweis 3"
        pos1 = "Position 5"
        vege = false
        veg = false
        prot = true})

     */
