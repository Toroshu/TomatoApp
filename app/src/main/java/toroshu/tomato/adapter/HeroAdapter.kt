package toroshu.tomato.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_hero.view.*
import toroshu.tomato.R
import toroshu.tomato.interfaces.OnClickListener
import toroshu.tomato.models.Hero

class HeroAdapter(val context: Context,
                  val onClickListener: OnClickListener,
                  val heros: List<Hero>)
    : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false))

    override fun getItemCount() = heros.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.tv_hero.text = heros[position].phone

            itemView.iv_edit.setOnClickListener {
                onClickListener.onClick(position, heros[position])
            }
        }
    }
}