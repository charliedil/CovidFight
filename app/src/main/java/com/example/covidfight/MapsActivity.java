package com.example.covidfight;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    int[] colors = {
            Color.GREEN,    // green(0-50)
            Color.YELLOW,    // yellow(51-100)
            Color.rgb(255,165,0), //Orange(101-150)
            Color.RED,              //red(151-200)
            Color.rgb(153,50,204), //dark orchid(201-300)
           // Color.rgb(165,42,42) //brown(301-500)
    };
   float[] startpoints = new float[]{
           .2f, .4f, .6f, .8f, 1.0f};

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

        MapStyleOptions mapStyleOptions=MapStyleOptions.loadRawResourceStyle(this,R.raw.style);
        googleMap.setMapStyle(mapStyleOptions);
        // Add a marker in Sydney and move the camera
        LatLng richmond = new LatLng(37.5483, -77.4527);
        //mMap.addMarker(new MarkerOptions().position(richmond).title("Marker in Richmond"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(richmond));
        mMap.moveCamera(CameraUpdateFactory.zoomTo((float) 15.0));
        Gradient gradient = new Gradient(colors,startpoints);
        WeightedLatLng thingy = new WeightedLatLng(new LatLng(37.5483, -77.4527),2.0);
        WeightedLatLng thingy2 = new WeightedLatLng(new LatLng(37.5493, -77.4527),5.0);
        List<WeightedLatLng> wDat = new ArrayList<WeightedLatLng>();
        wDat.add(thingy);
        wDat.add(thingy2);
        HeatmapTileProvider provider = new HeatmapTileProvider.Builder().weightedData(wDat).gradient(gradient).build();
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
        loadData();
        //System.exit(0);



    }
    public void loadData(){

        String myUrl = "https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cas" +
                "es_US/FeatureServer/0/query?where=1%3D1&outFields=Lat,Long_,Active&outSR=4326&f=json";
        //String to place our result in
        String result;
        //Instantiate new instance of our class
        HttpGetRequest getRequest = new HttpGetRequest();
        //Perform the doInBackground method, passing in our url
        try {
            result = getRequest.execute(myUrl).get();
            //System.exit(0);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();

    }
}
class HttpGetRequest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String stringUrl = params[0];
        String result = "";
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            InputStream thing = myUrl.openStream();
            Scanner scan = new Scanner(thing);
            while (scan.hasNextLine()) {
                result += scan.nextLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}}
