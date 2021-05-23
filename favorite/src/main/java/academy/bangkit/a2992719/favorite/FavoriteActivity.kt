package academy.bangkit.a2992719.favorite

import academy.bangkit.a2992719.core.domain.model.Movie
import academy.bangkit.a2992719.core.ui.ListAdapter
import academy.bangkit.a2992719.favorite.databinding.ActivityFavoriteBinding
import academy.bangkit.a2992719.favorite.di.DaggerFavoriteComponent
import academy.bangkit.a2992719.moviewer.detail.DetailActivity
import academy.bangkit.a2992719.moviewer.di.FavoriteModuleDependency
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val binding : ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    private val viewModel : FavoriteActivityViewModel by viewModels {
        factory
    }

    private lateinit var listAdapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependency::class.java
                )
            ).build().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.favoriteMovies.observe(this){ movies ->
            if(movies != null){
                if(movies.isEmpty()){
                    viewEmpty()
                }else{
                    listAdapter = ListAdapter(movies) { moveToDetail(it) }
                    showData()
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

    private fun showData(){
        with(binding){
            empty.visibility = View.GONE
            rv.apply {
                visibility = View.VISIBLE
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listAdapter
            }
        }
    }

    private fun viewEmpty(){
        with(binding){
            empty.visibility = View.VISIBLE
            rv.visibility = View.GONE
        }
    }
}