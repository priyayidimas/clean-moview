package academy.bangkit.a2992719.moviewer.home

import academy.bangkit.a2992719.core.data.Resource
import academy.bangkit.a2992719.core.domain.model.Movie
import academy.bangkit.a2992719.core.ui.ListAdapter
import academy.bangkit.a2992719.moviewer.R
import academy.bangkit.a2992719.moviewer.databinding.ActivityMainBinding
import academy.bangkit.a2992719.moviewer.detail.DetailActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel : MainActivityViewModel by viewModels()
    private lateinit var listAdapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.movies.observe(this){ movies ->
            if(movies != null){
                when(movies){
                    is Resource.Loading -> showProgressBar()
                    is Resource.Success -> {
                        listAdapter = ListAdapter(movies.data!!){ moveToDetail(it) }
                        showData()
                    }
                    is Resource.Error -> viewEmpty()
                }
            }
        }
    }

    private fun moveToDetail(movie: Movie){
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DATA, movie)
        }
        startActivity(intent)
    }

    private fun showProgressBar(){
        with(binding){
            wp7progressbar.showProgressBar()
            viewEmpty.visibility = View.GONE
            rvItems.visibility = View.GONE
        }
    }

    private fun showData(){
        with(binding){
            wp7progressbar.hideProgressBar()
            viewEmpty.visibility = View.GONE
            rvItems.apply {
                visibility = View.VISIBLE
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listAdapter
            }
        }
    }

    private fun viewEmpty(){
        with(binding){
            wp7progressbar.hideProgressBar()
            viewEmpty.visibility = View.VISIBLE
            rvItems.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_favorite -> {
                val intent = Intent(
                    this,
                    Class.forName("academy.bangkit.a2992719.favorite.FavoriteActivity"))
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}