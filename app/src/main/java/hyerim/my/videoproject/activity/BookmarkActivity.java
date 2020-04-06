package hyerim.my.videoproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.videoproject.MyApplication;
import hyerim.my.videoproject.R;
import hyerim.my.videoproject.RecyclerViewDecoration;
import hyerim.my.videoproject.adapter.DBAdapter;
import hyerim.my.videoproject.object.ItemObject;

import android.os.Bundle;
import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {
    private ItemObject item;
    private RecyclerView recyclerView;
    private DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        Toolbar toolbar = findViewById(R.id.bm_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.bm_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecyclerViewDecoration spaceDecoration = new RecyclerViewDecoration(30);
        recyclerView.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getApplicationContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        ArrayList<ItemObject> itemObjects = MyApplication.getInstance().dbManager.itemObjects();
        dbAdapter = new DBAdapter(getApplicationContext());
        dbAdapter.setList(itemObjects);
        recyclerView.setAdapter(dbAdapter);
    }


}
