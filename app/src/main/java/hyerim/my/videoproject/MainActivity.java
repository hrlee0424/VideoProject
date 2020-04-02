package hyerim.my.videoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.videoproject.adapter.MainListAdapter;
import hyerim.my.videoproject.asyncktask.YoutubeAsyncTask;
import hyerim.my.videoproject.object.ItemObject;

import android.content.ClipData;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainListAdapter mainListAdapter;
    private ArrayList<ItemObject> itemObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.mainlistview);
        itemObjects = new ArrayList<>();
        mainListAdapter = new MainListAdapter(getApplicationContext(),itemObjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mainListAdapter);

        new YoutubeAsyncTask(getApplicationContext(),mainListAdapter, itemObjects).execute();


    }
}
