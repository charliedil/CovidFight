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

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {
  private GoogleMap covidMap;
  private Button button;
  private Button closeActivity;

  int[] colors = {
      Color.GREEN,    // green(0-50)
      Color.YELLOW,    // yellow(51-100)
      Color.rgb(255, 165, 0), //Orange(101-150)
      Color.RED,              //red(151-200)
      Color.rgb(153, 50, 204), //dark orchid(201-300)
      // Color.rgb(165,42,42) //brown(301-500)
  };
  float[] startPoints = new float[]{
      .2f, .4f, .6f, .8f, 1.0f};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps2);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

    button = (Button) findViewById(R.id.datAButton);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openMap1();
      }
    });

    //button to close app
    closeActivity = (Button) findViewById(R.id.btnClose);
    closeActivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openMain();
      }
    });
  }

  //Function to open the USA Active Cases map
  public void openMap1() {
    Intent intent = new Intent(this, MapsActivity.class);
    startActivity(intent);
  }

  //Function to open the main activity
  public void openMain() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    covidMap = googleMap;
    MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.style);
    googleMap.setMapStyle(mapStyleOptions);
    LatLng richmond = new LatLng(37.5483, -77.4527);
    covidMap.moveCamera(CameraUpdateFactory.newLatLng(richmond));
    covidMap.moveCamera(CameraUpdateFactory.zoomTo((float) 5.75));
    Gradient gradient = new Gradient(colors, startPoints);
    List<WeightedLatLng> weightedLatLngs = loadData2();
    HeatmapTileProvider provider = new HeatmapTileProvider.Builder().weightedData(weightedLatLngs)
        .gradient(gradient).build();
    covidMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
  }

  /**
   * loadData2 function.
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
      ArrayList<String> indexToZipcode = new ArrayList<>(); //List for zips after parse
      ArrayList<String> indexToLong = new ArrayList<>(); //List for long after parse
      ArrayList<String> indexToLat = new ArrayList<>(); //List for lat after parse
      while (scan.hasNextLine()) {
        text += scan.nextLine();
      }
      text = text.replaceAll(",,", "\n");
      String[] datapoints = text.split("\n");
      for (String x : datapoints) {
        indexToZipcode.add(x.split(",")[0]);
        indexToLat.add(x.split(",")[1]);
        indexToLong.add(x.split(",")[2]);

      }
      String myUrl = "https://data.virginia.gov/resource/8bkr-zfqv.json";
      String result = "";
      HttpGetRequest getRequest = new HttpGetRequest();
      result = getRequest.execute(myUrl).get(); //COVID data from VA
      JSONArray jsonHold = new JSONArray(result);
      //Stores the data from URL into array lists
      ArrayList<String> reportDate = new ArrayList<>();
      ArrayList<String> zipcodes = new ArrayList<>();
      ArrayList<String> numCases = new ArrayList<>();
      ArrayList<String> numpcrtesting = new ArrayList<>();
      ArrayList<DataRichmond> dataList = new ArrayList<>();
      for (int i = 0; i < jsonHold.length(); i++) {
        JSONObject obj = jsonHold.getJSONObject(i);
        if (!(obj.isNull("report_date") || obj.isNull("zip_code") || obj.isNull("number_of_cases")
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
          if (indexToZipcode.get(i).equals(d.getZipCodes())
              && !d.getNumberOfCases().equals("Suppressed")) {
            WeightedLatLng dataPoint = new WeightedLatLng(new LatLng(
                Double.valueOf(indexToLat.get(i)),
                Double.valueOf(indexToLong.get(i))),
                Double.valueOf(d.getNumberOfCases())); //passed lat/long and active case data
            wdat.add(dataPoint); //added point ti array
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

  //Takes care of request clogging in the main thread asynchronously
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
