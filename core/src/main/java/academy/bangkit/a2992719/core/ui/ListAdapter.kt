package academy.bangkit.a2992719.core.ui

import academy.bangkit.a2992719.core.databinding.ItemDataBinding
import academy.bangkit.a2992719.core.domain.model.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(
    private val movies: List<Movie>,
    private val onItemClicked: (Movie) -> Unit
) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    init {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding){
            onItemClicked(movies[it])
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ItemViewHolder(
        private val binding: ItemDataBinding,
        onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClicked(absoluteAdapterPosition)
            }
        }

        fun bind(movie: Movie){
            with(binding){
                Glide.with(root.context)
                    .load(movie.posterUrl)
                    .into(itemImg)

                itemTitle.text = movie.title
                itemOverview.text = movie.overview
            }
        }
    }

}