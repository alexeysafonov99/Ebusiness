package shop.ebusiness.product

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import shop.ebusiness.R

class SearchActivity : AppCompatActivity() {
    internal var helper = DatabaseHelper(this)
    private lateinit var listView: ListView
    private lateinit var productAdapter: ProductListAdapter
    private lateinit var dbManager: DbManager
    private lateinit var listAdapter: ProductListAdapter
    private lateinit var searchView: SearchView
    private lateinit var allProducts: ArrayList<ProductList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        dbManager = DbManager(this)
        dbManager.open()

        listView = findViewById(R.id.listView)
        searchView = findViewById(R.id.search_bar)

        allProducts = helper.getAllProducts()

        listAdapter = ProductListAdapter(this, allProducts)
        listView.adapter = listAdapter

        val searchQuery = intent.getStringExtra("searchQuery")?:""
        val searchView = findViewById<SearchView>(R.id.search_bar)
        searchView.requestFocus()
        searchView.setQuery(searchQuery, false)

        listView = findViewById(R.id.listView)
        productAdapter = ProductListAdapter(this, dbManager.search(searchQuery)?.filterNotNull() ?: emptyList())
        listView.adapter = productAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val keyword = newText.orEmpty().trim()
                val filteredProducts = if (keyword.isEmpty()) {
                    allProducts
                } else {
                    searchProducts(keyword)
                }
                listAdapter.updateList(filteredProducts)
                return true
            }
        })
    }

    private fun searchProducts(keyword: String): ArrayList<ProductList> {
        val filteredList: ArrayList<ProductList> = ArrayList()

        for (product in allProducts) {
            if (product.name.contains(keyword, true)) {
                filteredList.add(product)
            }
        }

        return filteredList
    }
}
