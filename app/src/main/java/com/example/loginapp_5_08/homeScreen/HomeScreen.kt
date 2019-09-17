package com.example.loginapp_5_08.homeScreen

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.homeScreen.ViewPager.WeatherPagerAdapter
import com.example.loginapp_5_08.settings.SettingsActivity
import com.example.loginapp_5_08.shared.SharedPreference
import com.example.loginapp_5_08.homeScreen.viewModels.HomeViewModel
import kotlinx.android.synthetic.main.activity_home_screen.*
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.common.api.GoogleApiClient
import android.location.Location
import com.google.android.gms.location.LocationServices
import android.os.Build
import android.content.pm.PackageManager

import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.loginapp_5_08.settings.roomDB.City
import com.google.android.gms.location.LocationListener
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.content.Context


class HomeScreen : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener, LocationListener {

    //Variables Declaration
    private lateinit var pagerAdapter: WeatherPagerAdapter
    private lateinit var sharedPreference: SharedPreference
    private lateinit var homeViewModel: HomeViewModel
    private var latitude:Double = 0.0
    private var longitude:Double = 0.0

    //to Get Current Location
    private var location: Location? = null
    private var googleApiClient: GoogleApiClient? = null
    private val PLAY_SERVICES_RESOLUTION_REQUEST = 9000
    private var locationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL: Long = 5000
    private val FASTEST_INTERVAL: Long = 5000 // = 5 seconds

    // lists for permissions
    lateinit var permissionsToRequest: ArrayList<String>
    private val permissionsRejected = ArrayList<String>()
    private val permissions = ArrayList<String>()

    // integer for permissions results request
    private val ALL_PERMISSIONS_RESULT = 1011

    private lateinit var cityObserver: Observer<List<City>>
    private lateinit var currentLoc:City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)


        //Current Location
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        permissionsToRequest = permissionsToRequest(permissions)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size > 0) {
                requestPermissions(
                    permissionsToRequest.toArray(
                        arrayOfNulls(permissionsToRequest.size)
                    ), ALL_PERMISSIONS_RESULT
                )
            }
        }

        // we build google api client
        googleApiClient = GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this).build()

        ///////////////////////////////////////////////////////////////////////////////////////////////

        //Initializing Shared Preference
        sharedPreference = SharedPreference(applicationContext)

        //View Model
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        //homeViewModel.init(sharedPreference)

        val firstStart = sharedPreference.getValueBoolean("firstStart", true)

        //Setting Up Default City
        if (firstStart){
            val citiesAdded = ArrayList<String>()
            citiesAdded.add("London")

            sharedPreference.saveArrayList("addedCitiesList",citiesAdded)
            sharedPreference.save("firstStart",false)
            sharedPreference.save("latitude", latitude.toString())
            sharedPreference.save("longitude", longitude.toString())

        }

        //getCurrentLocationCheck
        val currentCityCheck = homeViewModel.getCurrentLocationCheck(sharedPreference)

        //getAddedCities
        val citiesAdded = homeViewModel.getAddedCities(sharedPreference)

        //addCurrentCity
        currentLoc = City(123, "Current", "Pk", longitude, latitude)
        //addCurrentCity
        if (currentCityCheck == true)
        {

            currentLoc = City(123, "Current", "Pk", sharedPreference.getValueString("longitude")!!.toDouble(),
                sharedPreference.getValueString("latitude")!!.toDouble()
            )

            //cityAdded = homeViewModel.addCurrentCity()
            homeViewModel.insert(currentLoc)
        }

        else if (currentCityCheck == false){
            homeViewModel.delete(currentLoc)
        }

        citiesAdded.reversed()

        pagerAdapter = WeatherPagerAdapter(supportFragmentManager, citiesAdded, sharedPreference)
        viewPager.adapter = pagerAdapter
        viewPager.currentItem = pagerAdapter.cities.size


        //setPagerAdapter with viewPager
        cityObserver = Observer { newCity ->
            // Update the UI
            val citiesAdded = newCity as ArrayList

            val addedCity = ArrayList<String>()

            for (i in citiesAdded){
                addedCity.add(i.name)
            }

            //pagerAdapter = WeatherPagerAdapter(supportFragmentManager, addedCity, latitude,longitude, sharedPreference)
            pagerAdapter = WeatherPagerAdapter(supportFragmentManager, citiesAdded, sharedPreference)
            viewPager.adapter = pagerAdapter
            viewPager.currentItem = 0

        }

        homeViewModel.allCities!!.observe(this, cityObserver)

        //Setting Tab with View Pager
        cityTabLayout.setupWithViewPager(viewPager)

        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()

        val currentCityCheck = homeViewModel.getCurrentLocationCheck(sharedPreference)

        if (currentCityCheck == true)
        {

            currentLoc = City(123, "Current", "Pk", sharedPreference.getValueString("longitude")!!.toDouble(),
                sharedPreference.getValueString("latitude")!!.toDouble()
            )

            //cityAdded = homeViewModel.addCurrentCity()
            homeViewModel.insert(currentLoc)
        }

        else if (currentCityCheck == false){
            homeViewModel.delete(currentLoc)
        }

        homeViewModel.allCities!!.observe(this, cityObserver)
        //viewPager.currentItem = 0

        cityTabLayout.setupWithViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.tool_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        return when (item.itemId){
            R.id.setting ->{
                val clickintent = Intent(this, SettingsActivity::class.java)
                startActivity(clickintent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun permissionsToRequest(wantedPermissions: ArrayList<String>): ArrayList<String> {
        val result = ArrayList<String>()

        for (perm in wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm)
            }
        }

        return result
    }

    private fun hasPermission(permission: String): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        } else true

    }

    override fun onStart() {
        super.onStart()

        //homeViewModel.connectGoogleApi()
        if (googleApiClient != null) {
            googleApiClient!!.connect()
        }
    }

    override fun onPause() {
        super.onPause()

        //homeViewModel.disconnectGoogleApi()
        // stop location updates
        if (googleApiClient != null && googleApiClient!!.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this)
            googleApiClient!!.disconnect()
        }
    }

    private fun checkPlayServices(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
            } else {
                finish()
            }

            return false
        }

        return true
    }

    override fun onConnected(p0: Bundle?) {
        if (ActivityCompat.checkSelfPermission(this,
	                 Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        &&  ActivityCompat.checkSelfPermission(this,
		             Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        return
        }

        // Permissions ok, we get last location
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)

        if (location != null) {
            latitude = location!!.latitude
            longitude = location!!.longitude

            sharedPreference.save("latitude", latitude.toString())
            sharedPreference.save("longitude", longitude.toString())
          //locationTv.setText("Latitude : " + location.getLatitude() + "\nLongitude : " + location.getLongitude());
        }

        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        locationRequest = LocationRequest()
        locationRequest!!.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest!!.setInterval(UPDATE_INTERVAL)
        locationRequest!!.setFastestInterval(FASTEST_INTERVAL)

        if (ActivityCompat.checkSelfPermission(this,
	          Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
              &&  ActivityCompat.checkSelfPermission(this,
		      Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "You need to enable permissions to display location !", Toast.LENGTH_SHORT).show()
                }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this)
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onLocationChanged(p0: Location?) {
        if (location != null) {
            latitude = location!!.latitude
            longitude = location!!.longitude

            sharedPreference.save("latitude", latitude.toString())
            sharedPreference.save("longitude", longitude.toString())

            //locationTv.setText("Latitude : " + location.getLatitude() + "\nLongitude : " + location.getLongitude());
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when(requestCode){
            ALL_PERMISSIONS_RESULT ->
                for (perm in permissionsToRequest) {
                    if (!hasPermission(perm)) {
                        permissionsRejected.add(perm)
                    }
                }
        }

        googleApiClient?.connect()
    }

}