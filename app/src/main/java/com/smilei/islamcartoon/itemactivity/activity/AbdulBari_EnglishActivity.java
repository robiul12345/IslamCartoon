package com.smilei.islamcartoon.itemactivity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.smilei.islamcartoon.R;
import com.smilei.islamcartoon.itemactivity.adapter.Costom_item_Adpter;
import com.smilei.islamcartoon.itemactivity.model.Model_item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AbdulBari_EnglishActivity extends AppCompatActivity {
    private AdView mAdView;
    Costom_item_Adpter adpter;
    ArrayList<Model_item> model_item;
    RecyclerView recyclerView;
    String abdulbarienglish = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId=PL4R-ERikKFx3qXB5YBLQwiWMeWS6Fg4kn&key=AIzaSyAnW0Lk1sZTLKTVEtvI6URzj4YbHe-6fiY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abdul_bari__english);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Englis_activity_abdulbari_Recyclerview();
    }
    private void Englis_activity_abdulbari_Recyclerview() {
        model_item = new ArrayList<>();
        recyclerView = findViewById(R.id.activity_abdul_bari_english_Id);
        adpter = new Costom_item_Adpter(AbdulBari_EnglishActivity.this, model_item);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adpter);
        english_abdulbarifetchdata();
    }

    private void english_abdulbarifetchdata() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, abdulbarienglish,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("items");
                            // Log.i("msg", "len " + jsonArray.length());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                //JSONObject jsonvideoid = jsonObject1.getJSONObject("id");
                                // Log.i("msg", "id " + jsonArray.getJSONObject(i));
                                JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                                JSONObject jsonthumbnail = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");
                                JSONObject resources = jsonsnippet.getJSONObject("resourceId");

                                Model_item md2 = new Model_item();
                                if (i != 1 && i != 2) {
                                    md2.setVideoId(resources.getString("videoId"));
                                    Log.i("msg", "Id " + resources.getString("videoId"));
                                    md2.setTitle(jsonsnippet.getString("title"));
                                    md2.setUri(jsonthumbnail.getString("url"));
                                    model_item.add(md2);
                                }

                            }
                            if (model_item.size() > 0) {
                                adpter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AbdulBari_EnglishActivity.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

}