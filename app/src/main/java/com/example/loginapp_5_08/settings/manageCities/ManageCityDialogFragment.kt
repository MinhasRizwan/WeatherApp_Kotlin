package com.example.loginapp_5_08.settings.manageCities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import com.example.loginapp_5_08.R
import android.widget.ArrayAdapter
import com.example.loginapp_5_08.ViewPager.CityHelper
import android.widget.Toast
import android.widget.AdapterView
import com.example.loginapp_5_08.shared.SharedPreference

class ManageCityDialogFragment(val sharedPreference: SharedPreference) : DialogFragment(){

    private var content: String? = null

    lateinit var  listAdapter : ArrayAdapter<String>
    lateinit var  listAdapterAddedCities : ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments?.getString("content")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_manage_cities, container, false)

        val searchCity = view.findViewById<View>(R.id.serchText) as EditText
        val citiesList = view.findViewById<View>(R.id.citiesList) as ListView
        val addedCitiesList = view.findViewById<View>(R.id.addedCities) as ListView


        //FontUtils.setTypeface(getActivity(), textViewQuestion, "fonts/mangal.ttf");
        //FontUtils.setTypeface(getActivity(), textViewAnswer, "fonts/mangal.ttf");

        val movies = CityHelper.getMoviesFromJson("cities.json", view.context)

        val movieSize = movies.size



        val added = sharedPreference.getArrayList("addedCitiesList")
        val listViewAdapterContent:ArrayList<String> = ArrayList()

        for (i in 0..movieSize-1) {
            listViewAdapterContent.add(movies[i].name)
        }

        listAdapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, android.R.id.text1, listViewAdapterContent)
        listAdapterAddedCities = ArrayAdapter(view.context, android.R.layout.simple_list_item_1,android.R.id.text1, added)


        citiesList.setAdapter(listAdapter)
        addedCitiesList.setAdapter(listAdapterAddedCities)

        citiesList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // make Toast when click
            Toast.makeText(view.context, "Position $position", Toast.LENGTH_LONG).show()
            searchCity.setText(movies[position].name)

            val adde = sharedPreference.getArrayList("addedCitiesList")
            adde.add(movies[position].name)

            sharedPreference.saveArrayList("addedCitiesList",adde)

        }

        searchCity.addTextChangedListener(object:TextWatcher {

            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
            }

            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                listAdapter.getFilter().filter(s)
            }

            override fun afterTextChanged(s:Editable) {
            }
        });

        return view
    }

    companion object {

        /**
         * Create a new instance of CustomDialogFragment, providing "num" as an
         * argument.
         */
        fun newInstance(content: String, sharedPreference: SharedPreference): ManageCityDialogFragment {
            val f = ManageCityDialogFragment(sharedPreference)

            // Supply num input as an argument.
            val args = Bundle()
            args.putString("content", content)
            f.arguments = args

            return f
        }
    }
}