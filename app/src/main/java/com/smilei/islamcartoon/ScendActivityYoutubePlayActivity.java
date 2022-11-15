package com.smilei.islamcartoon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.smilei.islamcartoon.adpter.Adpter;
import com.smilei.islamcartoon.itemactivity.activity.AbdulBari_Bangla_Activity;
import com.smilei.islamcartoon.itemactivity.activity.AbdulBari_EnglishActivity;
import com.smilei.islamcartoon.itemactivity.activity.GrandmaActivity;
import com.smilei.islamcartoon.itemactivity.activity.IslamShortActivity;
import com.smilei.islamcartoon.itemactivity.activity.KahanyaActivity;
import com.smilei.islamcartoon.itemactivity.activity.OmorActivity;
import com.smilei.islamcartoon.model.Model;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ScendActivityYoutubePlayActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlayerStateChangeListener, YouTubePlayer.PlaybackEventListener {
    private AdView mAdView;
    Intent intent;
    //  ImageView imageView;
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
    String videoId;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scend_youtube_play);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        intent = getIntent();
        videoId = intent.getStringExtra("videoid");
        youTubePlayerView = findViewById(R.id.player);
        youTubePlayerView.initialize("AIzaSyAnW0Lk1sZTLKTVEtvI6URzj4YbHe-6fiY", this);


        //   imageView = findViewById(R.id.imageview_Id);
        textView = findViewById(R.id.title_Id);
        intent = getIntent();

        String videoId = intent.getStringExtra("videoid");

      /*  Picasso.get().load(intent.getStringExtra("image")).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScendActivityYoutubePlayActivity.this, YoutubePlayActivity.class);
                i.putExtra("videoid", videoId);
                startActivity(i);
            }
        });
        textView.setText(getIntent().getStringExtra("title"));
        // = intent.getStringExtra("uriid");


       */
        SeeAllitemTextView();
        abdulbari_bangla_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AbdulBari_Bangla_Activity.class));

            }
        });
        abdulbari_english_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AbdulBari_EnglishActivity.class));

            }
        });
        omor_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OmorActivity.class));

            }
        });
        nursery_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), IslamShortActivity.class));

            }
        });
        kahanya_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), KahanyaActivity.class));

            }
        });
        Grandma_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GrandmaActivity.class));

            }
        });
        abdulbari_Recyclerview();
        omor_Recyclerview();
        Grandma_Recyclerview();
        islam_Recyclerview();
        englis_abdulbari_Recyclerview();
        kahanya_Recyclerview();


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
        adpter = new Adpter(ScendActivityYoutubePlayActivity.this, list);

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

                Toast.makeText(ScendActivityYoutubePlayActivity.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }


    private void omor_Recyclerview() {
        list1 = new ArrayList<>();
        omor_recyclerView = findViewById(R.id.omor_recyclerviewId);
        adpter1 = new Adpter(ScendActivityYoutubePlayActivity.this, list1);

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

                Toast.makeText(ScendActivityYoutubePlayActivity.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void kahanya_Recyclerview() {
        list2 = new ArrayList<>();
        kahanya_recyclerview = findViewById(R.id.kahanya_recyclerviewId);
        adpter2 = new Adpter(ScendActivityYoutubePlayActivity.this, list2);

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

                Toast.makeText(ScendActivityYoutubePlayActivity.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void englis_abdulbari_Recyclerview() {
        list3 = new ArrayList<>();
        english_abdul_bari_recyclerview = findViewById(R.id.english_abdul_recyclerviewId);
        adpter3 = new Adpter(ScendActivityYoutubePlayActivity.this, list3);

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

                Toast.makeText(ScendActivityYoutubePlayActivity.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }


    private void islam_Recyclerview() {
        list4 = new ArrayList<>();
        nursery_recyclerview = findViewById(R.id.nursery_recyclerviewId);
        adpter4 = new Adpter(ScendActivityYoutubePlayActivity.this, list4);

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

                Toast.makeText(ScendActivityYoutubePlayActivity.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void Grandma_Recyclerview() {
        list5 = new ArrayList<>();
        Grandma_recyclerview = findViewById(R.id.Grandma_recyclerviewId);
        adpter5 = new Adpter(ScendActivityYoutubePlayActivity.this, list5);

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

                Toast.makeText(ScendActivityYoutubePlayActivity.this, "Error!!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        if (!b) {
            youTubePlayer.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }
}