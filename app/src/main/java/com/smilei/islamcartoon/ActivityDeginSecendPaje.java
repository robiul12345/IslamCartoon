package com.smilei.islamcartoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smilei.islamcartoon.adpter.Adpter;
import com.smilei.islamcartoon.itemactivity.activity.AbdulBari_Bangla_Activity;
import com.smilei.islamcartoon.itemactivity.activity.AbdulBari_EnglishActivity;
import com.smilei.islamcartoon.itemactivity.activity.IslamShortActivity;
import com.smilei.islamcartoon.itemactivity.activity.KahanyaActivity;
import com.smilei.islamcartoon.itemactivity.activity.OmorActivity;
import com.smilei.islamcartoon.model.Model;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDeginSecendPaje extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    ImageView imageView;
    TextView textView;
    private RecyclerView omor_recyclerView, abdul_bari_recyclerView, kahanya_recyclerview, english_abdul_bari_recyclerview, nursery_recyclerview, Grandma_recyclerview;
    private Adpter adpter, adpter1, adpter2, adpter3, adpter4, adpter5;
    private ArrayList<Model> list, list1, list2, list3, list4, list5;
    private TextView omor_see, abdulbari_bangla_see, abdulbari_english_see, nursery_see, kahanya_see, Grandma_see;

    String grandma = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId=PLXRActLQ03obdakLcxDejbQiuoCa81RZS&key=AIzaSyAnW0Lk1sZTLKTVEtvI6URzj4YbHe-6fiY";
    String omar = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId=PLw37Cz21Cob3af_q1t42OyQoe6GLYnzts&key=AIzaSyAnW0Lk1sZTLKTVEtvI6URzj4YbHe-6fiY";
    String abdulbaribangla = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId=PL4R-ERikKFx2Ms7n0TTtEHGKLTVA482or&key=AIzaSyAnW0Lk1sZTLKTVEtvI6URzj4YbHe-6fiY";
    String abdulbarienglish = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId=PL4R-ERikKFx3qXB5YBLQwiWMeWS6Fg4kn&key=AIzaSyAnW0Lk1sZTLKTVEtvI6URzj4YbHe-6fiY";
    String nursery = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId=PLXRActLQ03oZqXCXtNhNHg-uvuJCStyAX&key=AIzaSyAnW0Lk1sZTLKTVEtvI6URzj4YbHe-6fiY";
    String kahanya = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId=PLVMLDG1Rj50WSfepXXAm9I9RKNa2OcDBk&key=AIzaSyAnW0Lk1sZTLKTVEtvI6URzj4YbHe-6fiY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degin_secend_paje);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.imageview_Id);
        textView = findViewById(R.id.title_Id);
        intent = getIntent();

        String videoId = intent.getStringExtra("videoid");

        Picasso.get().load(intent.getStringExtra("image")).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityDeginSecendPaje.this, YoutubePlayActivity.class);
                i.putExtra("videoid", videoId);
                startActivity(i);
            }
        });
        textView.setText(getIntent().getStringExtra("title"));
        // = intent.getStringExtra("uriid");


        abdulbari_Recyclerview();
        omor_Recyclerview();
        Grandma_Recyclerview();


        islam_Recyclerview();
        englis_abdulbari_Recyclerview();
        kahanya_Recyclerview();

        SeeAllitemTextView();

        abdulbari_bangla_see.setOnClickListener(this);
        abdulbari_english_see.setOnClickListener(this);
        nursery_see.setOnClickListener(this);
        kahanya_see.setOnClickListener(this);
        omor_see.setOnClickListener(this);
        Grandma_see.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void SeeAllitemTextView() {
        omor_see = findViewById(R.id.see_all_omor);
        abdulbari_english_see = findViewById(R.id.see_all_abdulbari_english);
        abdulbari_bangla_see = findViewById(R.id.see_all_abdulbari_bangla);
        nursery_see = findViewById(R.id.see_all_nursery);
        kahanya_see = findViewById(R.id.see_all_kahanya);
        Grandma_see = findViewById(R.id.see_all_Grandma);

    }

    private void abdulbari_Recyclerview() {
        list = new ArrayList<>();
        abdul_bari_recyclerView = findViewById(R.id.abdul_bari_recyclerviewId);
        adpter = new Adpter(ActivityDeginSecendPaje.this, list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        abdul_bari_recyclerView.setLayoutManager(layoutManager);
        abdul_bari_recyclerView.setAdapter(adpter);
        abdulbarifetchdata();
    }

    private void abdulbarifetchdata() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, abdulbaribangla,
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

                                Model md2 = new Model();
                                if (i != 1 && i != 2) {
                                    md2.setVideoId(resources.getString("videoId"));
                                    Log.i("msg", "Id " + resources.getString("videoId"));
                                    md2.setTitle(jsonsnippet.getString("title"));
                                    md2.setUri(jsonthumbnail.getString("url"));
                                    list.add(md2);
                                }

                            }
                            if (list.size() > 0) {
                                adpter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityDeginSecendPaje.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }


    private void omor_Recyclerview() {
        list1 = new ArrayList<>();
        omor_recyclerView = findViewById(R.id.omor_recyclerviewId);
        adpter1 = new Adpter(ActivityDeginSecendPaje.this, list1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        omor_recyclerView.setLayoutManager(layoutManager);
        omor_recyclerView.setAdapter(adpter1);
        omorfetchdata();
    }

    private void omorfetchdata() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, omar,
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

                                Model md2 = new Model();
                                if (i != 1 && i != 2) {
                                    md2.setVideoId(resources.getString("videoId"));
                                    Log.i("msg", "Id " + resources.getString("videoId"));
                                    md2.setTitle(jsonsnippet.getString("title"));
                                    md2.setUri(jsonthumbnail.getString("url"));
                                    list1.add(md2);
                                }

                            }
                            if (list1.size() > 0) {
                                adpter1.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityDeginSecendPaje.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void kahanya_Recyclerview() {
        list2 = new ArrayList<>();
        kahanya_recyclerview = findViewById(R.id.kahanya_recyclerviewId);
        adpter2 = new Adpter(ActivityDeginSecendPaje.this, list2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        kahanya_recyclerview.setLayoutManager(layoutManager);
        kahanya_recyclerview.setAdapter(adpter2);
        kahanyafetchdata();
    }

    private void kahanyafetchdata() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, kahanya,
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

                                Model md2 = new Model();
                                if (i != 1 && i != 2) {
                                    md2.setVideoId(resources.getString("videoId"));
                                    Log.i("msg", "Id " + resources.getString("videoId"));
                                    md2.setTitle(jsonsnippet.getString("title"));
                                    md2.setUri(jsonthumbnail.getString("url"));
                                    list2.add(md2);
                                }

                            }
                            if (list2.size() > 0) {
                                adpter2.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityDeginSecendPaje.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void englis_abdulbari_Recyclerview() {
        list3 = new ArrayList<>();
        english_abdul_bari_recyclerview = findViewById(R.id.english_abdul_recyclerviewId);
        adpter3 = new Adpter(ActivityDeginSecendPaje.this, list3);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        english_abdul_bari_recyclerview.setLayoutManager(layoutManager);
        english_abdul_bari_recyclerview.setAdapter(adpter3);
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

                                Model md2 = new Model();
                                if (i != 1 && i != 2) {
                                    md2.setVideoId(resources.getString("videoId"));
                                    Log.i("msg", "Id " + resources.getString("videoId"));
                                    md2.setTitle(jsonsnippet.getString("title"));
                                    md2.setUri(jsonthumbnail.getString("url"));
                                    list3.add(md2);
                                }

                            }
                            if (list3.size() > 0) {
                                adpter3.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityDeginSecendPaje.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }


    private void islam_Recyclerview() {
        list4 = new ArrayList<>();
        nursery_recyclerview = findViewById(R.id.nursery_recyclerviewId);
        adpter4 = new Adpter(ActivityDeginSecendPaje.this, list4);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        nursery_recyclerview.setLayoutManager(layoutManager);
        nursery_recyclerview.setAdapter(adpter4);
        islam_abdulbarifetchdata();
    }

    private void islam_abdulbarifetchdata() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, nursery,
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

                                Model md2 = new Model();
                                if (i != 1 && i != 2) {
                                    md2.setVideoId(resources.getString("videoId"));
                                    Log.i("msg", "Id " + resources.getString("videoId"));
                                    md2.setTitle(jsonsnippet.getString("title"));
                                    md2.setUri(jsonthumbnail.getString("url"));
                                    list4.add(md2);
                                }

                            }
                            if (list4.size() > 0) {
                                adpter4.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityDeginSecendPaje.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void Grandma_Recyclerview() {
        list5 = new ArrayList<>();
        Grandma_recyclerview = findViewById(R.id.Grandma_recyclerviewId);
        adpter5 = new Adpter(ActivityDeginSecendPaje.this, list5);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        Grandma_recyclerview.setLayoutManager(layoutManager);
        Grandma_recyclerview.setAdapter(adpter5);
        Grandmafetchdata();
    }

    private void Grandmafetchdata() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, grandma,
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

                                Model md2 = new Model();
                                if (i != 1 && i != 2) {
                                    md2.setVideoId(resources.getString("videoId"));
                                    Log.i("msg", "Id " + resources.getString("videoId"));
                                    md2.setTitle(jsonsnippet.getString("title"));
                                    md2.setUri(jsonthumbnail.getString("url"));
                                    list5.add(md2);
                                }

                            }
                            if (list5.size() > 0) {
                                adpter5.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityDeginSecendPaje.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.see_all_abdulbari_bangla:
                startActivity(new Intent(getApplicationContext(), AbdulBari_Bangla_Activity.class));

                break;
            case R.id.see_all_abdulbari_english:
                startActivity(new Intent(getApplicationContext(), AbdulBari_EnglishActivity.class));

                break;
            case R.id.see_all_nursery:
                startActivity(new Intent(getApplicationContext(), IslamShortActivity.class));

                break;
            case R.id.see_all_omor:
                startActivity(new Intent(getApplicationContext(), OmorActivity.class));

                break;
            case R.id.see_all_kahanya:
                startActivity(new Intent(getApplicationContext(), KahanyaActivity.class));

                break;
        }

    }


}