package hyerim.my.videoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private String TAG = PlayActivity.class.getSimpleName();
    public static String API = "AIzaSyCIgi0uGLGXEAJ_AG8uqCbllZUfhDrxcQY";
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer ytp ;
    private String videoId = "";
    private YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        videoId = intent.getStringExtra("videoid");
        youTubePlayerView = findViewById(R.id.youtubeplayer);
        youTubePlayerView.initialize(API, this);
        Log.i(TAG, "onCreate: " + videoId);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        ytp = youTubePlayer;
        ytp.loadVideo(videoId);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getApplicationContext(), "Initialization Fail", Toast.LENGTH_LONG).show();
    }
}
