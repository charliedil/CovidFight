package com.example.covidfight;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//need this to be async somewhere probably, very slow
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
  private GoogleMap googleMap;
  private Button button;
  private Button closeActivity;

  int[] colors = { //used to color the heat map
    Color.GREEN,    // green(0-50)
    Color.YELLOW,    // yellow(51-100)
    Color.rgb(255, 165, 0), //Orange(101-150)
    Color.RED, //red(151-200)
    Color.rgb(153, 50, 204), //dark orchid(201-300)

  };
  float[] startPoints = new float[]{
    .2f, .4f, .6f, .8f, 1.0f};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
    button = findViewById(R.id.dataButton);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) { //switching maps
        openMap2();
      }
    });
    //Button to close popup
    closeActivity = (Button) findViewById(R.id.btnClose);
    closeActivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) { //back to main
        openMain();
      }
    });
  }

  //Function to open the Virginia Active Cases map
  public void openMap2() {
    Intent intent = new Intent(this, MapsActivity2.class);
    startActivity(intent);
  }

  //Function to open the main activity
  public void openMain() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }


  @Override
  public void onMapReady(GoogleMap googleMap) {
    this.googleMap = googleMap;
    MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.style);
    googleMap.setMapStyle(mapStyleOptions);
    LatLng richmond = new LatLng(37.5483, -89.4527);
    this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(richmond));
    this.googleMap.moveCamera(CameraUpdateFactory.zoomTo((float) 3.75)); //5.0 for US
    Gradient gradient = new Gradient(colors, startPoints);
    List<WeightedLatLng> weightedLatLngs = loadData();
    HeatmapTileProvider provider = new HeatmapTileProvider.Builder().weightedData(weightedLatLngs)
        .gradient(gradient).build();
    this.googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
  }

  /** loadData2 function.
   * Function that loads the Virginia Active Cases data and our zip code data.
   * The number of active cases per zip code is used as the "weight" for each coordinate.
   * Coordinates are mapped from the zip codes using our zip code data.
   *
   * @return ArrayList of weighted coordinates of active cases in Virginia.
   */
  public List<WeightedLatLng> loadData2() {
    ArrayList<WeightedLatLng> wdat = new ArrayList<>(); //array list to return
    try {
      InputStream zipcodeFile = getAssets().open("ZipLatLong.csv"); //open zip code data
      Scanner scan = new Scanner(zipcodeFile);
      String text = "";
      ArrayList<String> indexToZipcode = new ArrayList<>(); //parse file - zips go here
      ArrayList<String> indexToLong = new ArrayList<>(); //parse file - longitudes go here
      ArrayList<String> indexToLat = new ArrayList<>(); //parse file - latitudes go
      while (scan.hasNextLine()) {
        text += scan.nextLine();
      }
      text = text.replaceAll(",,", "\n");
      String [] datapoints = text.split("\n");
      for (String x : datapoints) {
        indexToZipcode.add(x.split(",")[0]);
        indexToLat.add(x.split(",")[1]);
        indexToLong.add(x.split(",")[2]);
      }
      String myUrl = "https://data.virginia.gov/resource/8bkr-zfqv.json";
      String result = "";
      HttpGetRequest getRequest = new HttpGetRequest();
      result = getRequest.execute(myUrl).get(); //virginia cases data
      JSONArray jsonHold = new JSONArray(result);
      ArrayList<String> reportDate = new ArrayList<>();
      ArrayList<String> zipcodes = new ArrayList<>(); //we use this
      ArrayList<String> numCases = new ArrayList<>(); //and this
      ArrayList<String> numpcrtesting = new ArrayList<>(); //more data to potentially display
      ArrayList<DataRichmond> dataList = new ArrayList<>();
      for (int i = 0; i < jsonHold.length(); i++) {
        JSONObject obj = jsonHold.getJSONObject(i);
        //JSONObject attributes = obj.getJSONObject("attributes");
        if (!(obj.isNull("report_date") || obj.isNull("zip_code")
                || obj.isNull("number_of_cases")
                || obj.isNull("number_of_pcr_testing"))) {
          reportDate.add((String) obj.get(("report_date")));
          zipcodes.add((String) obj.get(("zip_code")));
          numCases.add((String) obj.get(("number_of_cases")));
          numpcrtesting.add((String) obj.get("number_of_pcr_testing"));
        }
      }
      for (int i = 0; i < reportDate.size(); i++) {
        DataRichmond dataPoint = new DataRichmond(reportDate.get(i), zipcodes.get(i),
                numCases.get(i), numpcrtesting.get(i));
        dataList.add(dataPoint); //data structure to hold for cases
      }
      for (DataRichmond d : dataList) {
        for (int i = 0; i < indexToZipcode.size(); i++) {
          if (indexToZipcode.get(i).equals(d.getZip_code()) //use zipcode to find long/lat
                  && !d.getNumber_of_cases().equals("Suppressed")) {
            WeightedLatLng dataPoint = new WeightedLatLng(//using long/lat to pass in
                    new LatLng(Double.valueOf(indexToLat.get(i)),
                            Double.valueOf(indexToLong.get(i))),
                    Double.valueOf(d.getNumber_of_cases())); //active cases is used for weight
            wdat.add(dataPoint); //add it to the array
            break;
          }
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return wdat;
  }

  /** loadData function:
   * Function that loads the US Active Cases data.
   * The number of active cases per coordinate is used as the "weight" for each coordinate.
   *
   * @return ArrayList of weighted coordinates of active cases across the US.
   */
  public List<WeightedLatLng> loadData() {

    String myUrl = "https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases_"
            + "US/FeatureServer/0/query?where=1%3D1&outFields=Lat,Long_,Active&outSR=4326&f=json";
    //String to place our result in
    String result;
    //Instantiate new instance of our class
    HttpGetRequest getRequest = new HttpGetRequest();
    //Perform the doInBackground method, passing in our url
    try {
      result = getRequest.execute(myUrl).get();
      JSONObject obj = new JSONObject(result);
      JSONArray two = obj.getJSONArray("features");
      ArrayList<Double> latList = new ArrayList<Double>(); //use these to keep track of datapoints
      ArrayList<Double> longList = new ArrayList<Double>();
      ArrayList<Integer> activeList = new ArrayList<Integer>();
      for (int i = 0; i < two.length(); i++) {
        JSONObject thingy = two.getJSONObject(i);
        JSONObject attributes = thingy.getJSONObject("attributes");
        if (!(attributes.isNull("Lat") || attributes.isNull("Long_")
                || attributes.isNull("Active"))) {
          double lat = attributes.getDouble("Lat"); //extract Lats
          double lng  = attributes.getDouble("Long_"); //extract Longs
          int active = attributes.getInt("Active"); //extract active cases
          latList.add(lat); //add these to our lists
          longList.add(lng);
          activeList.add(active);
        }
      }
      List<WeightedLatLng> weightedLatLngs = new ArrayList<WeightedLatLng>();
      for (int i = 0; i < latList.size(); i++) { //now, use those lists to create...
        WeightedLatLng dataPoint = new WeightedLatLng(new LatLng(latList.get(i), longList.get(i)),
                activeList.get(i));
        weightedLatLngs.add(dataPoint); //...weighted lat longs!
      }
      return weightedLatLngs;
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  //Android does not like having get requests clogging the main thread, so this takes care of it
  // asynchronously
  static class HttpGetRequest extends AsyncTask<String, Void, String> {
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
  }
}
