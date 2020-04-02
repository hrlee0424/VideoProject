package hyerim.my.videoproject.asyncktask;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import hyerim.my.videoproject.adapter.MainListAdapter;
import hyerim.my.videoproject.object.ItemObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YoutubeAsyncTask extends AsyncTask {
    private String TAG = YoutubeAsyncTask.class.getSimpleName();
    public ArrayList<ItemObject> itemObjects;
    public MainListAdapter mainListAdapter;
    private Context context;
    private ArrayList<String> list = new ArrayList<>();
    private String apiurl, key, part, q, order, url;
//    String[] OptionParams = new String[4];

    public YoutubeAsyncTask(Context context, MainListAdapter mainListAdapter, ArrayList<ItemObject> itemObject) {
        this.context = context;
        this.mainListAdapter = mainListAdapter;
        this.itemObjects = itemObject;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            OkHttpClient client = new OkHttpClient();
            apiurl = "https://www.googleapis.com/youtube/v3/search?";
            key = "AIzaSyCIgi0uGLGXEAJ_AG8uqCbllZUfhDrxcQY";
            part = "snippet";
            q = "백종원의요리비책";
            order = "date";

            url = apiurl + "key=" + key + "&part=" + part + "&q=" + q + "&order=" + order;

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request)
                    .execute();

            String result = response.body().string();

            JSONObject obj = new JSONObject(result);
            JSONArray arr = (JSONArray) obj.get("items");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject item = (JSONObject) arr.get(i);
                JSONObject kind = (JSONObject)item.get("id");
                String id = (String)kind.get("videoId");    //videoId 파싱
                JSONObject snippet = (JSONObject) item.get("snippet");
                String mpublish = (String)snippet.get("publishedAt");    //publishedAt 파싱
                String publish = mpublish.substring(0,10);      //publishedAt 날짜만 자르기
                String title = (String) snippet.get("title");       //title 파싱
                JSONObject thumbnails = (JSONObject) snippet.get("thumbnails");
                JSONObject mdefault = (JSONObject)thumbnails.get("default");
                String murl = (String) mdefault.get("url");     //url 파싱


                itemObjects.add(new ItemObject(id,publish,title,murl));

        }
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        mainListAdapter.notifyDataSetChanged();
    }
}
