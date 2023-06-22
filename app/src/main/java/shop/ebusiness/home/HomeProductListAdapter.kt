package shop.ebusiness.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import shop.ebusiness.R
import shop.ebusiness.product.ProductList

/**
 * Diese Klasse verknüpft die Datenbank mit den Layouts.
 * @param context der Kontext der Anwendung
 * @param productList die Liste der Produkte
 */
class HomeProductListAdapter(private val context: Context, private val productList: List<ProductList>) {

    /**
     * Erstellt die Ansicht für jedes Produkt in der Liste.
     * @param linearLayout das Layout, in dem die Ansichten erstellt werden sollen
     */
    @SuppressLint("SetTextI18n")
    fun createProductViews(linearLayout: LinearLayout) {
        for (product in productList) {
            val productBox = LayoutInflater.from(context).inflate(R.layout.product_home_screen_item_m, null)

            // TextViews im LinearLayout finden und in Variablen speichern
            val textProductName = productBox.findViewById<TextView>(R.id.textProduktname)
            val textPrice = productBox.findViewById<TextView>(R.id.textProduktpreis)
            val textBeschreibung = productBox.findViewById<TextView>(R.id.textProduktbeschreibung)
            val textManufacturer = productBox.findViewById<TextView>(R.id.textHersteller)

            //Verknüpfung der Layout-Attribute mit den Attributen aus der datenbank
            textProductName.text = product.name
            textPrice.text = "${product.pr} ${product.einh_preis}"
            textBeschreibung.text = product.pos1
            textManufacturer.text = product.her

            linearLayout.addView(productBox)
        }
    }
}

