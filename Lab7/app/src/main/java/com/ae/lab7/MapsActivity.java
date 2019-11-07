package com.ae.lab7;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.arch.core.util.Function;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marker;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        marker = null;

        mMap.setOnMapLongClickListener(latLng -> {
            if (marker != null) {
                marker.remove();
            }
            latitude = latLng.latitude;
            longitude = latLng.longitude;
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            marker = mMap.addMarker(markerOption);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
            makeRequest(marker.getPosition().latitude, marker.getPosition().longitude, (String response) -> {
                WHERES_THE_TOAST(ParseJSON(response));
                return null;
            }, (VolleyError error) -> {
                WHERES_THE_TOAST(Arrays.toString(error.getStackTrace()));
                return null;
            });
        });
    }

    public void makeRequest(double lat, double lon, final Function<String, Void> successCallback, final Function<VolleyError, Void> errorCallback) {
        // volley
        String apikey = "9973f3fc9178d889a2f179a3b6ed3826";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&apikey=%s", lat, lon, apikey);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, successCallback::apply, errorCallback::apply);
        queue.add(stringRequest);
    }

    public void WHERES_THE_TOAST(String message) {
        Context context = getApplicationContext();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private String ParseJSON(String json){
        String jsonResult = "";
        try {
            JSONObject JsonObject = new JSONObject(json);
            String cod = jsonHelperGetString(JsonObject, "cod");
            if(cod != null){
                if(cod.equals("200")){
                    JSONObject sys = jsonHelperGetJSONObject(JsonObject, "sys");
                    if(sys != null){
                        jsonResult += jsonHelperGetString(sys, "country") + "\n";
                    }
                    jsonResult += "\n";
                    JSONObject main = jsonHelperGetJSONObject(JsonObject, "main");
                    if(main != null){
                        jsonResult += "temp: " + String.format("%.2f",  convertTheTemp(Double.parseDouble(jsonHelperGetString(main, "temp")))) + " deg C\n";
                    }
                }else if(cod.equals("404")){
                    String message = jsonHelperGetString(JsonObject, "message");
                    jsonResult += "cod 404: " + message;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            jsonResult += e.getMessage();
        }
        return jsonResult;
    }

    public double convertTheTemp(double temp) {
        return temp - 273.15;
    }

    private String jsonHelperGetString(JSONObject obj, String k){
        String v = null;
        try {
            v = obj.getString(k);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return v;
    }

    private JSONObject jsonHelperGetJSONObject(JSONObject obj, String k){
        JSONObject o = null;
        try {
            o = obj.getJSONObject(k);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o;
    }
}
