package shop.ebusiness.product

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SearchView
import shop.ebusiness.R

class HomeActivity : ComponentActivity() {
    private lateinit var dbManager: DbManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        dbManager = DbManager(this)
        dbManager.open()

        val searchBar: SearchView = findViewById(R.id.search_bar1)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchInput: String?): Boolean {
                val intent = Intent(this@HomeActivity, SearchActivity::class.java)
                intent.putExtra("searchQuery", searchInput)
                startActivity(intent)
                return true
            }
        })
    }
}
