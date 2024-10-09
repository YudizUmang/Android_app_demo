package com.example.android_app_demo.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.android_app_demo.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var myMap: GoogleMap
    private lateinit var supportMapFragment: SupportMapFragment
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_map)
        loading = findViewById(R.id.map_loading)
        loading.visibility = View.VISIBLE
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        supportMapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                10
            )
            return
        } else {
            myMap.isMyLocationEnabled = true
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val latLng = LatLng(it.latitude, it.longitude)
                    myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

                    myMap.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .icon(
                                BitmapDescriptorFactory.fromBitmap(
                                    bitmapSizeByScall(
                                        BitmapFactory.decodeResource(
                                            getResources(),
                                            R.drawable.location
                                        ), 0.1f
                                    )
                                )
                            )
                    )


                    myMap.setOnMarkerClickListener {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val addresses =
                            geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                        val address = addresses?.get(0)?.getAddressLine(0)
                        Toast.makeText(this, address, Toast.LENGTH_LONG).show()
                        true

                    }
                }
            }

            myMap.setOnMapLongClickListener(GoogleMap.OnMapLongClickListener { latLng ->
                myMap.clear()
                myMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                myMap.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .icon(
                            BitmapDescriptorFactory.fromBitmap(
                                bitmapSizeByScall(
                                    BitmapFactory.decodeResource(
                                        getResources(),
                                        R.drawable.location
                                    ), 0.1f
                                )
                            )
                        )
                )
            })
        }
    }

    private fun bitmapSizeByScall(bitmapIn: Bitmap, scall_zero_to_one_f: Float): Bitmap {
        val bitmapOut = Bitmap.createScaledBitmap(
            bitmapIn,
            Math.round(bitmapIn.width * scall_zero_to_one_f),
            Math.round(bitmapIn.height * scall_zero_to_one_f), false
        )

        return bitmapOut
    }

    override fun onMapReady(p0: GoogleMap) {
        loading.visibility = View.GONE
        myMap = p0

        getLastLocation()
    }

}

