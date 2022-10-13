package uz.mamadalievdev.footballerdb.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mamadalievdev.footballerdb.R
import uz.mamadalievdev.footballerdb.data.response.Player
import uz.mamadalievdev.footballerdb.databinding.ItemFootballerBinding

class FootballerAdapter : RecyclerView.Adapter<FootballerAdapter.MovieCardViewHolder>() {
    var data = mutableListOf<Player>()

    private var itemClickListener: ((name: String, biography: String, image: String) -> Unit)? =
        null

    fun setItemClickListener(f: (name: String, biography: String, image: String) -> Unit) {
        itemClickListener = f
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setArticles(nData: List<Player>) {
        this.data.clear()
        this.data.addAll(nData)
        notifyDataSetChanged()
    }

    inner class MovieCardViewHolder(private val binding: ItemFootballerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Player) {
            binding.apply {
                title.text = data.strPlayer
            }

            Glide.with(binding.root.context)
                .load(data.strThumb)
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(binding.image)

            itemView.setOnClickListener {
                itemClickListener?.invoke(data.strPlayer, data.strDescriptionEN, data.strThumb)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieCardViewHolder(
        ItemFootballerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) =
        holder.bindData(data[position])
}