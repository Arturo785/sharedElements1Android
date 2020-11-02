package com.example.sharedtransitions.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedtransitions.R
import com.example.sharedtransitions.data.Sports

class SportsAdapter(
    private val sportsList: List<Sports>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<SportsAdapter.SportsViewHolder>() {

    class SportsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val banner: ImageView = itemView.findViewById(R.id.sports_item_image_view)
        private val title: TextView = itemView.findViewById(R.id.title_item_text_view)

        fun bind(sports: Sports, onClickListener: OnClickListener) {
            banner.setImageResource(sports.banner)
            title.text = sports.title

            //Each transition needs an unique name
            banner.transitionName = sports.banner.toString()
            title.transitionName = sports.title

            itemView.setOnClickListener {
                onClickListener.onClick(sports, banner, title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        return SportsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sports,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        val sports: Sports = sportsList[position]
        holder.bind(sports, onClickListener)
    }

    override fun getItemCount() = sportsList.size

    //Needed for the sharedElements
    //Receives a lambda
    class OnClickListener(val clickListener: (Sports, ImageView, TextView) -> Unit) {

        // the on click does whatever the one that implements the class desires
        // in our case in listFragment and passed by params and binded on the viewHolder
        fun onClick(sports: Sports, banner : ImageView, title : TextView) =
            clickListener(sports, banner, title)
    }
}