package shop.ebusiness.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import shop.ebusiness.R
import shop.ebusiness.product.ProductList

/**
 * Diese Klasse ist ein Adapter, der verwendet wird, um eine Liste von Produkten in einer ListView
 * anzuzeigen. Sie erweitert die Klasse BaseAdapter.
 * @param context der Kontext der Anwendung
 * @param ListOfProducts Liste von Produkten
 */
class ProductListAdapter (context: Context, ListOfProducts: List<ProductList>)  : BaseAdapter() {

    private var listOfProducts: List<ProductList>
    private val mInflator: LayoutInflater

    //Initialisierung der Variablen
    init {
        this.listOfProducts = ListOfProducts
        this.mInflator = LayoutInflater.from(context) //Umwandlung von XML-Layoutdateien in ViewObjekte
    }

    /**
     * Überschreibung der Methode updateList aus der BaseAdapter Klasse
     * Wenn die Methode aufgerufen wird, soll die Liste aktualisiert werden
     * @param newList neue Produktliste
     */
    fun updateList(newList:List<ProductList>){
        listOfProducts=newList

    }
    /**
     * Überschreibung der Methode getCount aus der BaseAdapter Klasse
     * Wenn die Methode aufgerufen wird, soll die Länge der Liste ausgegeben werden
     */
    override fun getCount(): Int {
        return listOfProducts.size
    }

    /**
     * Überschreibung der Methode getItem aus der BaseAdapter Klasse
     * @param position Position des Elements
     * Wenn die Methode aufgerufen wird, soll ein Element von einer gegebenen Position ermittelt werden
     */
    override fun getItem(position: Int): Any {
        return listOfProducts[position]
    }
    /**
     * Überschreibung der Methode getItem aus der BaseAdapter Klasse
     * @param position Position des Elements
     * Wenn die Methode aufgerufen wird, soll die ID des Elements von einer gegebenen Position ermittelt werden
     */

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    /**
     * Überschreibung der Methode getView aus der BaseAdapter Klasse
     * @param position Position des Elements
     * @param parent Übergeordnete Ansicht des Elements
     * @param viewHolder Eine Instanz der Klasse ViewHolder.
     * @convertView
     * @return View Die Methode gibt eine Ansicht zurück, die das Element der Liste darstellt.
     */

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        //Layout für ein einzelnes Produkt soll in den Listview eingefügt werden
        if (convertView == null) {
            view = mInflator.inflate(R.layout.product_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val product = listOfProducts[position]
        viewHolder.bind(product)

        return view
    }


    inner class ViewHolder(itemView: View) {
        private val productName: TextView = itemView.findViewById(R.id.product_name)
        private val productPrice: TextView = itemView.findViewById(R.id.product_price)

        @SuppressLint("SetTextI18n")
        /**
        * Methode verknüpft Produkte aus der Liste mit dem XML Layout
        */
        fun bind(product: ProductList) {
            productName.text = product.name
            productPrice.text = "${product.pr} ${product.einh_preis}"
        }
    }



    /**
     * Methode sortiert Produkte nach aufsteigendem Preis
     */
    fun sortByPriceAscending(){
        listOfProducts=listOfProducts.sortedBy { it.pr }

    }
    /**
     * Methode sortiert Produkte nach absteigendem Preis
     */
    fun sortByPriceDescending(){
        listOfProducts=listOfProducts.sortedByDescending { it.pr }

    }

}
