package com.idream.assignment.main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idream.assignment.R;
import com.idream.assignment.adapter.YoutubeVideoAdapter;
import com.idream.assignment.model.YoutubeVideoModel;
import com.idream.assignment.utils.RecyclerViewOnClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private YoutubeVideoAdapter youtubeVideoAdapter;
    private List<YoutubeVideoModel> youtubeVideoModelArrayList;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("categoryId"));
        load_data_from_server(id);
        setUpRecyclerView();
        populateRecyclerView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        initCollapsingToolbar();

        try {
            Glide.with(this).load(R.drawable.youtube).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    /**
     * populate the recyclerview and implement the click event here
     */
    private void populateRecyclerView() {
        youtubeVideoModelArrayList = new ArrayList<>();
        youtubeVideoAdapter = new YoutubeVideoAdapter(this, (ArrayList<YoutubeVideoModel>) youtubeVideoModelArrayList);
        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(this, new RecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //start youtube player activity by passing selected video id via intent
                startActivity(new Intent(MainActivity.this, YoutubePlayerActivity.class)
                        .putExtra("video_id", youtubeVideoModelArrayList.get(position).getVideoId())
                        .putExtra("sub_category_id",id)
                );

            }
        }));
        recyclerView.setAdapter(youtubeVideoAdapter);
    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;

    }


    private void load_data_from_server(int id) {

        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://www.webnetcreatives.com/hollytree/meranihongo/app/videoNew_Class.php?id="+integers[0])
                        .build();
                try {
                    okhttp3.Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        YoutubeVideoModel data = new YoutubeVideoModel(object.getString("video_url"),object.getString("video_category"),object.getString("video_title"),
                                object.getString("video_duration"));

                        youtubeVideoModelArrayList.add(data);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                youtubeVideoAdapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        Intent intent = getIntent();
        final String mActionBarTitle = intent.getStringExtra("actionBarTitle");


        collapsingToolbar.setTitle(mActionBarTitle);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(mActionBarTitle);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(mActionBarTitle);
                    isShow = false;
                }
            }
        });
    }

}

