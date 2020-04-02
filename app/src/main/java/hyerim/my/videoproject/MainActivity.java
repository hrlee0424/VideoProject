package hyerim.my.videoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.videoproject.adapter.MainListAdapter;
import hyerim.my.videoproject.asyncktask.YoutubeAsyncTask;
import hyerim.my.videoproject.object.ItemObject;

import android.content.ClipData;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainListAdapter mainListAdapter;
    private ArrayList<ItemObject> itemObjects;
    private ConnectivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInternetState();   //인터넷 연결 확인

        RecyclerView recyclerView = findViewById(R.id.mainlistview);
        itemObjects = new ArrayList<>();
        mainListAdapter = new MainListAdapter(getApplicationContext(),itemObjects);

        //recyclerview 구분선
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mainListAdapter);

        new YoutubeAsyncTask(getApplicationContext(),mainListAdapter, itemObjects).execute();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {

                }
            }
        });
    }

    //인터넷 연결 확인 메소드
    private void checkInternetState() {
        manager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        assert manager != null;
        if (!(manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected())){
            new AlertDialog.Builder(this)
                    .setMessage("인터넷과 연결이 되어있지 않습니다.")
                    .setCancelable(false)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                        }
                    }).show();
        }
    }


}
