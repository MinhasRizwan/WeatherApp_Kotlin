package com.example.loginapp_5_08.settings.manageCities

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
import java.util.ArrayList
import android.widget.TextView
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.layout_manage_cities.*
import android.app.ActionBar


class ManageCityDialogFragment(val sharedPreference: SharedPreference) : DialogFragment(){

    private var content: String? = null
    private lateinit var manageCityViewModel: ManageCityViewModel
    lateinit var  listAdapter : ArrayAdapter<String>
    lateinit var  listAdapterAddedCities : ArrayAdapter<String>
    //lateinit var citiesSpinner:Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments?.getString("content")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.example.loginapp_5_08.R.layout.layout_manage_cities, container, false)

        val textView = TextView(context)
        textView.text = "Added Cities"

        manageCityViewModel = ViewModelProviders.of(this).get(ManageCityViewModel::class.java)
        manageCityViewModel.init(sharedPreference, view)

        val searchCity = view.findViewById<View>(com.example.loginapp_5_08.R.id.serchText) as EditText
        val citiesList = view.findViewById<View>(com.example.loginapp_5_08.R.id.allCities) as ListView
        val addedCitiesList = view.findViewById<View>(com.example.loginapp_5_08.R.id.addedCities) as ListView
       // citiesSpinner = view.findViewById<View>(com.example.loginapp_5_08.R.id.spinnerCities) as Spinner

        addedCitiesList.addHeaderView(textView)

        //ViewModel.getAllCities()
        val cities = manageCityViewModel.getAllCities()

        //ListAdapter For All Cities
        listAdapter = manageCityViewModel.getListAdapterAllCities()
        //ListAdapter For All Added Cities
        listAdapterAddedCities = manageCityViewModel.getListAdapterAddedCities()


        citiesList.setAdapter(listAdapter)
        addedCitiesList.setAdapter(listAdapterAddedCities)

        listAdapter.getFilter().filter("1232442")

        citiesList.onItemClickListener = AdapterView.OnItemClickListener {parent,view, position, id ->
            // Get the selected item text from ListView
            val selectedItem = parent.getItemAtPosition(position) as String

            Toast.makeText(context, parent.getItemAtPosition(position).toString() + " Added", Toast.LENGTH_LONG).show()
            searchCity.setText(selectedItem)

            manageCityViewModel.addCityIntoUserPreference(selectedItem)

        }

        addedCitiesList.onItemLongClickListener = AdapterView.OnItemLongClickListener{
                parent, veiw, position, id ->
            // make Toast when click
            //Delete City from user preference
            Toast.makeText(veiw.context, parent.getItemAtPosition(position).toString() + " Deleted", Toast.LENGTH_LONG).show()

            manageCityViewModel.deleteCityFromUserPreference(position-1)

            return@OnItemLongClickListener true
        }

        searchCity.addTextChangedListener(object:TextWatcher {

            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

            }

            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {

                //val lp = citiesList.getLayoutParams()

                //lp.height = 600
                //citiesList.setLayoutParams(lp)

                listAdapter.getFilter().filter(s)

                citiesList.visibility = View.VISIBLE
            }

            override fun afterTextChanged(s:Editable) {
            }
        })

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

    //add items into spinner dynamically



}