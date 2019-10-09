package com.example.loginapp_5_08.settings.manageCities

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp_5_08.settings.roomDB.City
import com.example.loginapp_5_08.settings.roomDB.CityViewModel
import kotlinx.android.synthetic.main.layout_addedcities_list.view.*
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.loginapp_5_08.R
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class ManageCityDialogFragment : AppCompatDialogFragment(){

    //RxJava
    private val disposable = CompositeDisposable()
    private val _textInput = BehaviorSubject.create<String>()
    private val textInput = _textInput.toFlowable(BackpressureStrategy.LATEST)


    private var content: String? = null
    private lateinit var  listAdapter : ArrayAdapter<String>


     //View Model
    private lateinit var cityViewModel: CityViewModel
    private lateinit var cityObserver: Observer<List<City>>

    //RecyclerView to show added Cities
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CitiesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)
        content = arguments?.getString("content")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_manage_cities, container, false)

        recyclerView = view?.findViewById(R.id.recyclerviewAddedCities)!!
        adapter = context?.let { CitiesListAdapter(it) }!!

        cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        return view
    }

    //LongClick to delete the added City

    interface OnItemLongClickListener {
        fun onItemLongClicked(position: Int, view: View)
    }

    private fun RecyclerView.addOnItemLongClickListener(onClickLongListener: OnItemLongClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnLongClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnLongClickListener {
                    val holder = getChildViewHolder(view)
                    onClickLongListener.onItemLongClicked(holder.adapterPosition, view)

                    return@setOnLongClickListener true
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView1) as AutoCompleteTextView
        autoCompleteTextView.setAdapter(this@ManageCityDialogFragment.context?.let { getCitiesAdapter(it) })
        autoCompleteTextView.setDropDownBackgroundResource(R.color.white)

        cityObserver = Observer { newCity ->

            // Update the cached copy of the words in the adapter.
            // Update the UI
            adapter.setCities(newCity)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@ManageCityDialogFragment.context)
        }

        cityViewModel.allCities!!.observe(this, cityObserver)

        //ViewModel.getAllCities()
        //List of All cities
        val cities = cityViewModel.getAllCities()

        //ListAdapter For All Cities
        listAdapter = cityViewModel.getListAdapterAllCities()

        //ClickListeners
        //Long click listener on added cities to delete the city from Database

        recyclerView.addOnItemLongClickListener(object : OnItemLongClickListener {
            override fun onItemLongClicked(position: Int, view: View) {
                Toast.makeText(context,  view.textViewCity.text.toString() + "Deleted", Toast.LENGTH_SHORT).show()

                for (i in cities) {
                    if ((i.name) == view.textViewCity.text.toString()){
                        val city = City(i.id.toInt(), i.name, i.country, i.long.toDouble(), i.lat.toDouble() )
                        cityViewModel.delete(city)
                    }
                }
            }
        })

        //Click on any city to add it into the database
        //AutoCompleteTextView is used for filtering the cities according to the text entered

        autoCompleteTextView.onItemClickListener =
            OnItemClickListener { arg0, _, arg2, _ ->
                Toast.makeText(context, arg0.getItemAtPosition(arg2) as CharSequence, Toast.LENGTH_LONG)
                    .show()

                val item = arg0.getItemAtPosition(arg2).toString()

                for (i in cities) {
                    if ((i.name + " , " + i.country) == item){
                        val city = City(i.id.toInt(), i.name, i.country, i.long.toDouble(), i.lat.toDouble() )
                        cityViewModel.insert(city)
                    }
                }
            }


        //RxJava for better Implementation

        autoCompleteTextView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                _textInput.onNext(autoCompleteTextView.text.toString())
            }

            override fun beforeTextChanged(charSeq: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSeq: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        //applied Debounce Operator

        disposable.add(
            textInput
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    val str = it
                    Toast.makeText(context, str, Toast.LENGTH_LONG).show()
                    //autoCompleteTextView.setAdapter(this@ManageCityDialogFragment.context?.let { getCitiesAdapter(it) })
                }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    companion object {

        /**
         * Create a new instance of CustomDialogFragment
         */
        fun newInstance( content: String): ManageCityDialogFragment {
            val f = ManageCityDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putString("content", content)
            f.arguments = args

            return f
        }
    }


    private fun getCitiesAdapter(context: Context): ArrayAdapter<String> {
        val city = ArrayList<String>()
        val cities = cityViewModel.getAllCities()


        for (i in cities){
            city.add(i.name + " , " + i.country)
        }

        return ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, city)
    }

}

