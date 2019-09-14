package com.example.loginapp_5_08.settings.manageCities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp_5_08.settings.roomDB.City
import kotlinx.android.synthetic.main.layout_addedcities_list.view.*


class CitiesListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CitiesListAdapter.CityViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var cities = emptyList<City>() // Cached copy of words

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityItemView: TextView = itemView.findViewById(com.example.loginapp_5_08.R.id.textViewCity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = inflater.inflate(com.example.loginapp_5_08.R.layout.layout_addedcities_list, parent, false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = cities[position]
        holder.cityItemView.text = current.name
    }

    internal fun setCities(cities: List<City>) {
        this.cities = cities
        notifyDataSetChanged()
    }

    override fun getItemCount() = cities.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems() {
            itemView.textViewCity.setOnLongClickListener {
                Toast.makeText(itemView.context,"clicked on "+itemView.textViewCity.text, Toast.LENGTH_SHORT).show()
                return@setOnLongClickListener true
            }
        }
    }

}

