package shop.ebusiness.home

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.ComponentActivity
import shop.ebusiness.R
import shop.ebusiness.product.ProductList


class HomeActivity : ComponentActivity() {
    private lateinit var dbManager: DbManager_m
    private lateinit var allProducts: ArrayList<ProductList>
    private lateinit var kaloriearmCheckbox: CheckBox
    private lateinit var vegetarischCheckbox: CheckBox
    private lateinit var veganCheckbox: CheckBox
    private lateinit var fettarmCheckbox: CheckBox
    private lateinit var proteinreichCheckbox: CheckBox
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_m)

        dbManager = DbManager_m(this)
        dbManager.open()
        allProducts = dbManager.getAllProducts()

        //Checkboxen Initialisierung
        kaloriearmCheckbox = findViewById(R.id.Kaloriearm)
        vegetarischCheckbox = findViewById(R.id.Vegetarisch)
        veganCheckbox = findViewById(R.id.Vegan)
        fettarmCheckbox = findViewById(R.id.Fettarm)
        proteinreichCheckbox = findViewById(R.id.Proteinreich)

        //Initialisierung des Linearen Layouts für die Produktdarstellung
        linearLayout = findViewById(R.id.linearLayout)

        //Wenn auf den TextView Früchte geklickt wird, sollen alle Produkte der Kategorie Früchte ausgegeben werden
        val textFruechte: TextView = findViewById(R.id.textFruechte)
        textFruechte.setOnClickListener {
            displayFruits()
        }
//Wenn auf den TextView Hülsenfrüchte geklickt wird, sollen alle Produkte der Kategorie Hülsenfrüchte ausgegeben werden
        val textHuelsenfruechte: TextView = findViewById(R.id.textHuelsenfruechte)
        textHuelsenfruechte.setOnClickListener {
            displayPulses()
        }

        //Wenn auf den TextView Alle geklickt wird, sollen alle Produkte der Kategorie Alle ausgegeben werden
        val textAlle: TextView = findViewById(R.id.textAlle)
        textAlle.setOnClickListener {
            displayAllProducts()
        }

        //Verknüfung der Funktion applyFilter mit den Checkboxen
        kaloriearmCheckbox.setOnCheckedChangeListener { _, _ ->
            applyFilter()
        }

        vegetarischCheckbox.setOnCheckedChangeListener { _, _ ->
            applyFilter()
        }

        veganCheckbox.setOnCheckedChangeListener { _, _ ->
            applyFilter()
        }

        fettarmCheckbox.setOnCheckedChangeListener { _, _ ->
            applyFilter()
        }

        proteinreichCheckbox.setOnCheckedChangeListener { _, _ ->
            applyFilter()
        }

        //Adapter Instanz für die Erstellung des Objekt-Views
        val adapter = HomeProductListAdapter(this, allProducts)
        adapter.createProductViews(linearLayout)

        val searchBar: SearchView = findViewById(R.id.search_bar1)

        /**
         * Methode wird aufgerufen, sobald ein User eine Suchanfrage eingibt oder ändert
         * @param object
         */
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /**
             * Methode wird aufgerufen, sobald ein User eine Suchanfrage eingibt
             * @param query
             * @return false
             */
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            /**
             * Methode wird aufgerufen, sobald ein User eine Suchanfrage ändert, Activity wird zu shop.ebusiness.product.SearchActivity gewechselt
             * @param query
             */
            override fun onQueryTextChange(searchInput: String?): Boolean {
                val intent = Intent(this@HomeActivity, SearchActivity::class.java)
                intent.putExtra("searchQuery", searchInput)
                startActivity(intent)
                return true
            }
        })
    }

    /**
     * Zeigt alle Früchte der Datenbank an
     */
    private fun displayFruits() {
        linearLayout.removeAllViews()

        val fruits = dbManager.getAllFruits()
        val adapter = HomeProductListAdapter(this, fruits)
        adapter.createProductViews(linearLayout)
    }
    /**
     * Zeigt alle Hülsenfrüchte der Datenbank an
     */
    private fun displayPulses() {
        linearLayout.removeAllViews()

        val pulses = dbManager.getAllPulse()
        val adapter = HomeProductListAdapter(this, pulses)
        adapter.createProductViews(linearLayout)
    }

    /**
     * Zeigt alle Produkte der Datenbank an
     */
    private fun displayAllProducts() {
        linearLayout.removeAllViews()

        val adapter = HomeProductListAdapter(this, allProducts)
        adapter.createProductViews(linearLayout)
    }

    /**
     * Wendet die Checkboxfilter an der Produktmenge an
     * Checkbox filtert mit inklusivem Oder
     */
    private fun applyFilter() {
        linearLayout.removeAllViews()

        val filteredProducts = mutableListOf<ProductList>()

        val isCheckedKaloriearm = kaloriearmCheckbox.isChecked
        val isCheckedVegetarisch = vegetarischCheckbox.isChecked
        val isCheckedVegan = veganCheckbox.isChecked
        val isCheckedFettarm = fettarmCheckbox.isChecked
        val isCheckedProteinreich = proteinreichCheckbox.isChecked

        for (produkt in allProducts) {
            if ((isCheckedKaloriearm && produkt.kal) ||
                (isCheckedVegetarisch && produkt.vege) ||
                (isCheckedVegan && produkt.veg) ||
                (isCheckedFettarm && produkt.fet) ||
                (isCheckedProteinreich && produkt.prot)
            ) {
                filteredProducts.add(produkt)
            }
        }

        val adapter = HomeProductListAdapter(this, filteredProducts)
        adapter.createProductViews(linearLayout)

    }
}
