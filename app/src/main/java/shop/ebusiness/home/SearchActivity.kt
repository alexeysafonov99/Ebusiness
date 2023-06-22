package shop.ebusiness.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.SearchView
import androidx.activity.ComponentActivity
import shop.ebusiness.R
import shop.ebusiness.product.ProductList

class SearchActivity : ComponentActivity() {
    private lateinit var listView: ListView
    private lateinit var dbManager: DbManager_m
    private lateinit var listAdapter: ProductListAdapter
    private lateinit var searchView: SearchView
    private lateinit var allProducts: ArrayList<ProductList>
    private lateinit var currentSort: Sort
    enum class Sort{ NONE, ASCENDING, DESCENDING }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity_m)

        dbManager = DbManager_m(this)
        dbManager.open()
        //Zurückbutton mit Activitywechsek
        val backButton: ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()}

        listView = findViewById(R.id.listView)
        allProducts = dbManager.getAllProducts()
        searchView = findViewById(R.id.search_bar)
        listAdapter = ProductListAdapter(this, allProducts)
        listView.adapter = listAdapter

        currentSort= Sort.ASCENDING
        sortListByCurrentSort()




        val sortButton:ImageButton =findViewById(R.id.sort_button)
        sortButton.setOnClickListener { sortList() }
        /**
         * Methode wird aufgerufen, sobald ein User eine Suchanfrage eingibt oder ändert
         * @param object
         */
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            /**
             * Methode wird aufgerufen, sobald ein User eine Suchanfrage eingibt
             * @param query
             */
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            /**
             * Methode wird aufgerufen, sobald ein User eine Suchanfrage ändert
             * @param newText neuer Eingabetext
             * @return nach dem Keyword gefilterte Produktliste
             */
            override fun onQueryTextChange(newText: String?): Boolean {
                val searchResults= if(newText.isNullOrBlank()){
                    allProducts
                }else {
                    dbManager.search(newText)?.filterNotNull() ?: emptyList()
                }
                listAdapter.updateList(searchResults)
                return true
            }
        })


        //Übernimmt suchquery aus der shop.ebusiness.product.HomeActivity
        val searchQuery = intent.getStringExtra("searchQuery")?:""
        searchView.requestFocus()
        searchView.setQuery(searchQuery, false)
        val searchResults=dbManager.search(searchQuery)?.filterNotNull() ?: emptyList()
        listAdapter.updateList(searchResults)


    }
    /**
     * Methode wird aufgerufen, sobald ein User den Sortierbutton klickt
     * stellt ein, ob die  Sortieroptionen im Layout sichtbar sind
     */
    private fun sortList(){
        val sortOptions: LinearLayout = findViewById(R.id.sort_options)
        if (sortOptions.visibility == View.VISIBLE) {
            sortOptions.visibility = View.GONE
        } else {
            sortOptions.visibility = View.VISIBLE
        }

    }
    /**
     * Methode sortiert Produktpreise aufsteigend, sobald der User den im Layout definierten Text anklickt
     * Die neue Liste wird sortiert ausgegeben
     */
    fun onSortAscendingClick(view: View) {
        currentSort = Sort.ASCENDING
        sortListByCurrentSort()
        sortList()
    }

    /**
     * Methode sortiert Produktpreise absteigend, sobald der User den im Layout definierten Text anklickt
     * Die neue Liste wird sortiert ausgegeben
     */
    fun onSortDescendingClick(view: View) {
        currentSort = Sort.DESCENDING
        sortListByCurrentSort()
        sortList()
    }

    /**
     * Methode sortiert Produktpreise nach der momentan definierten Sortieroption
     */
    private fun sortListByCurrentSort() {
        when (currentSort) {
            Sort.ASCENDING -> listAdapter.sortByPriceAscending()
            Sort.DESCENDING -> listAdapter.sortByPriceDescending()
            Sort.NONE -> listAdapter.updateList(allProducts)
        }
    }



}

