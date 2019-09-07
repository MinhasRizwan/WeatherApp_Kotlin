package com.example.loginapp_5_08.settings.manageCities

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.loginapp_5_08.shared.SharedPreference
import kotlinx.android.synthetic.main.layout_manage_cities.*
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.settings.roomDB.City
import com.example.loginapp_5_08.settings.roomDB.CityViewModel
import kotlinx.android.synthetic.main.layout_addedcities_list.view.*

class ManageCityDialogFragment(context: Context, private val sharedPreference: SharedPreference) : DialogFragment(){

    private var content: String? = null
    lateinit var  listAdapter : ArrayAdapter<String>

    //View Model
    private lateinit var cityViewModel: CityViewModel
    private lateinit var cityObserver: Observer<List<City>>

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CitiesListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        content = arguments?.getString("content")


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.example.loginapp_5_08.R.layout.layout_manage_cities, container, false)

        recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerviewAddedCities)!!
        adapter = context?.let { CitiesListAdapter(it) }!!
        //recyclerView?.adapter = adapter
        //recyclerView?.layoutManager = LinearLayoutManager(this@ManageCityDialogFragment.context)

        cityViewModel = ViewModelProviders.of(this).get(CityViewModel::class.java)

        return view
    }

    ///////////////////////////////////////////////////////////////
    interface OnItemLongClickListener {
        fun onItemLongClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemLongClickListener(onClickLongListener: OnItemLongClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnLongClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnLongClickListener({
                    val holder = getChildViewHolder(view)
                    onClickLongListener.onItemLongClicked(holder.adapterPosition, view)

                    return@setOnLongClickListener true
                })
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ////////////////////////////////////////////////////////////
        ////////////////////////
        cityObserver = Observer { newCity ->

            // Update the cached copy of the words in the adapter.
            //    newCity?.let { adapter?.setCities(it) }
            //    Toast.makeText(context, "Added", Toast.LENGTH_LONG).show()

            // Update the UI
            adapter.setCities(newCity)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@ManageCityDialogFragment.context)

            //recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1) , futureWeatherResponseOWM, this@HomeFragment, tempScale)
            //recycler_view.layoutManager = LinearLayoutManager(this@HomeFragment.context)
        }

        cityViewModel.allCities!!.observe(this, cityObserver)
//////////////////////////

        //ViewModel.getAllCities()
        val cities = cityViewModel.getAllCities()

        //ListAdapter For All Cities
        listAdapter = cityViewModel.getListAdapterAllCities()

        //ListAdapter For All Added Cities

        spinnerCities.setAdapter(listAdapter)


        listAdapter.getFilter().filter("1232442")

        spinnerCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(arg0: AdapterView<*>, arg1: View, pos: Int, arg3: Long) {

                if (pos !=0){
                    val item = spinnerCities.getSelectedItem().toString()
                    Toast.makeText(arg1.context, arg0.getItemAtPosition(pos).toString() + " Added", Toast.LENGTH_LONG).show()
                    //manageCityViewModel.addCityIntoUserPreference(item)
                    serchText.setText(item)

                    for (i in cities) {
                        if (i.name.equals(item)){
                            val city = City(i.id.toInt(), i.name, i.country, 123455.0, 42242.43 )
                            cityViewModel.insert(city)
                        }
                    }

                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {

            }
        }


        recyclerView.addOnItemLongClickListener(object : OnItemLongClickListener {
            override fun onItemLongClicked(position: Int, view: View) {
                Toast.makeText(context,  view.textViewCity.text.toString() + "Deleted", Toast.LENGTH_SHORT).show()

                for (i in cities) {
                    if (i.name.equals(view.textViewCity.text.toString())){
                        val city = City(i.id.toInt(), i.name, i.country, 123455.0, 42242.43 )
                        cityViewModel.delete(city)
                    }
                }

            }
        })


        serchText.addTextChangedListener(object:TextWatcher {

            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

            }

            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {

                //val lp = citiesList.getLayoutParams()

                //lp.height = 600
                //citiesList.setLayoutParams(lp)

                listAdapter.getFilter().filter(s)
                spinnerCities.dropDownWidth = 800
                spinnerCities.performClick()
            }

            override fun afterTextChanged(s:Editable) {
            }
        })

    }

    companion object {

        /**
         * Create a new instance of CustomDialogFragment, providing "num" as an
         * argument.
         */
        fun newInstance(context:Context, content: String, sharedPreference: SharedPreference): ManageCityDialogFragment {
            val f = ManageCityDialogFragment(context, sharedPreference)

            // Supply num input as an argument.
            val args = Bundle()
            args.putString("content", content)
            f.arguments = args

            return f
        }
    }

}

