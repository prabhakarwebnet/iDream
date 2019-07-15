package com.idream.assignment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.idream.assignment.R;
import com.idream.assignment.adapter.YoutubeVideoAdapter;
import com.idream.assignment.model.YoutubeVideoModel;
import com.idream.assignment.utils.Constants;

import java.util.List;

public class YoutubePlayerActivity extends YouTubeBaseActivity   {
    private static final String URL_PRODUCTS = "http://www.webnetcreatives.com/hollytree/app/Hiragana.php";
    private static final String TAG = YoutubePlayerActivity.class.getSimpleName();
    private String videoID;
    private YouTubePlayerView youTubePlayerView;
    private RecyclerView recyclerView;
    private YoutubeVideoAdapter youtubeVideoAdapter;
    private List<YoutubeVideoModel> youtubeVideoModelArrayList;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_player_activity);

       // Intent intent = getIntent();
       // id = intent.getIntExtra("sub_category_id",id);
       // load_data_from_server(id);
       // setUpRecyclerView();
       // populateRecyclerView();

        //get the video id
        videoID = getIntent().getStringExtra("video_id");
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        initializeYoutubePlayer();
    }

//    private void setUpRecyclerView() {
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//    }


//    private void populateRecyclerView() {
//        youtubeVideoModelArrayList = new ArrayList<>();
//        youtubeVideoAdapter = new YoutubeVideoAdapter(this, (ArrayList<YoutubeVideoModel>) youtubeVideoModelArrayList);
//        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(this, new RecyclerViewOnClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//                //start youtube player activity by passing selected video id via intent
//                startActivity(new Intent(YoutubePlayerActivity.this, YoutubePlayerActivity.class)
//                        .putExtra("video_id", youtubeVideoModelArrayList.get(position).getVideoId()));
//
//            }
//        }));
//        recyclerView.setAdapter(youtubeVideoAdapter);
//
//    }


//    private void load_data_from_server(int id) {
//
//        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
//            @Override
//            protected Void doInBackground(Integer... integers) {
//
//                OkHttpClient client = new OkHttpClient();
//                okhttp3.Request request = new okhttp3.Request.Builder()
//                        .url("http://www.webnetcreatives.com/hollytree/meranihongo/app/videoNew1.php?id="+integers[0])
//                        .build();
//                try {
//                    okhttp3.Response response = client.newCall(request).execute();
//
//                    JSONArray array = new JSONArray(response.body().string());
//
//                    for (int i=0; i<array.length(); i++){
//
//                        JSONObject object = array.getJSONObject(i);
//
//                        YoutubeVideoModel data = new YoutubeVideoModel(object.getString("video_url"),object.getString("video_category"),object.getString("video_title"),
//                                object.getString("video_duration"));
//
//                        youtubeVideoModelArrayList.add(data);
//                    }
//
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    System.out.println("End of content");
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                youtubeVideoAdapter.notifyDataSetChanged();
//            }
//        };
//
//        task.execute(id);
//    }
//    /**
//     * initialize the youtube player
//     */
    private void initializeYoutubePlayer() {
        youTubePlayerView.initialize(Constants.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                boolean wasRestored) {

                //if initialization success then load the video id to youtube player
                if (!wasRestored) {
                    //set the player style here: like CHROMELESS, MINIMAL, DEFAULT
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    //load the video
                    youTubePlayer.loadVideo(videoID);

                    //OR

                    //cue the video
                    youTubePlayer.cueVideo(videoID);

                    //if you want when activity start it should be in full screen uncomment below comment
                      youTubePlayer.setFullscreen(true);

                    //If you want the video should play automatically then uncomment below comment
                    //  youTubePlayer.play();

                    //If you want to control the full screen event you can uncomment the below code
                    //Tell the player you want to control the fullscreen change
                   /*player.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                    //Tell the player how to control the change
                    player.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean arg0) {
                            // do full screen stuff here, or don't.
                            Log.e(TAG,"Full screen mode");
                        }
                    });*/

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                //print or show error if initialization failed
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.noteedit_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

}
