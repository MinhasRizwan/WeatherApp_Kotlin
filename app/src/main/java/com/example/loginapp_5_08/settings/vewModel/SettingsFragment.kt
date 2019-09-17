package com.example.loginapp_5_08.settings.vewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.settings.manageCities.ManageCityDialogFragment
import com.example.loginapp_5_08.shared.SharedPreference
import kotlinx.android.synthetic.main.fragment_setting_screen.*
import android.widget.*
import com.example.loginapp_5_08.homeScreen.HomeFragment
import com.example.loginapp_5_08.settings.roomDB.City


class SettingsFragment : Fragment(), View.OnClickListener {


    private var tempUnit : Int = 0
    private var cityChecked : Boolean = false
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        sharedPreference = SharedPreference(activity!!.applicationContext)

        getSavedValues()


        return inflater.inflate(
            R.layout.fragment_setting_screen,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setTitle("Settings")
        val tempScaleSpinner = setAdapter()

        val b = view.findViewById(com.example.loginapp_5_08.R.id.buttonManage) as Button
        b.setOnClickListener(this)

        dropdownScale.setSelection(tempUnit)
        checkedTextView.isChecked = cityChecked

        tempScaleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sharedPreference.save("tempScale", position)
                //userPrefViewModel.insertPref(position,cityChecked)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        checkedTextView.setOnClickListener(object: View.OnClickListener {

            override fun onClick(v:View) {


                if(checkedTextView.isChecked()){
                    sharedPreference.save("currentLocation",true)

                }

                else {
                    sharedPreference.save("currentLocation", false)
                }

            }
        })

    }

    private fun setAdapter() : Spinner
    {
        val spinner: Spinner = dropdownScale

        ArrayAdapter.createFromResource(
            this.context!!,
            R.array.temp_scale,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // adapter to the spinner
            spinner.adapter = adapter
        }

        return spinner
    }

    private fun getSavedValues()
    {
        tempUnit = sharedPreference.getValueInt("tempScale")
        cityChecked = sharedPreference.getValueBoolean("currentLocation",false)
    }

    override fun onClick(p0: View?) {
        val ft = activity?.getSupportFragmentManager()?.beginTransaction()
        val newFragment = ManageCityDialogFragment.newInstance("pass content" )
        if (ft != null) {
            newFragment.show(ft, "dialog")
        }

    }

    companion object{
        fun newInstance(): SettingsFragment {
            //val args = Bundle()
            //args.putSerializable(inputData, input)
            val frag = SettingsFragment( )
            //frag.arguments = args
            return frag
        }
    }

}