package academy.bangkit.a2992719.moviewer.detail

import academy.bangkit.a2992719.core.R
import academy.bangkit.a2992719.core.domain.model.Movie
import academy.bangkit.a2992719.moviewer.databinding.ActivityDetailBinding
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var movie: Movie
    private val viewModel : DetailActivityViewModel by viewModels()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        movie = intent.getParcelableExtra(EXTRA_DATA)!!

        initView(movie)
    }

    private fun initView(movie: Movie){
        with(binding) {
            Glide.with(root.context)
                .load(movie.posterUrl ?: "")
                .into(imageView)
            fieldTitle.text = movie.title
            fieldOverview.text = movie.overview ?: ""
            fieldReleaseDate.text = movie.releaseDate ?: ""
            fieldPopularity.text = (movie.popularity ?: 0).toString()
            fieldRating.text = (movie.voteAverage ?: 0).toString()

            var statusFavorite = movie.isFavorite

            setStatusFavorite(statusFavorite)

            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(movie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }
}