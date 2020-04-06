package hyerim.my.videoproject.asyncktask;

import android.content.Context;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONObject;
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
    private String apiurl, part, channelId, order, url, maxResults;
    public String nextPageToken, prevPageToken = "";
    private boolean firstPage;
    private static final String key = "AIzaSyCIgi0uGLGXEAJ_AG8uqCbllZUfhDrxcQY";   //API 키 번호

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
            part = "snippet";
            channelId = "UCyn-K7rZLXjGl7VXGweIlcA";     //채널 ID
            order = "date";         //최근 항목 부터 리스트
            maxResults = "10";      //최대 항목 수
            url = apiurl + "key=" + key + "&part=" + part + "&channelId=" + channelId + "&order=" + order + "&maxResults=" + maxResults;

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request)
                    .execute();

            String result = response.body().string();

            JSONObject obj = new JSONObject(result);
            nextPageToken = (String)obj.get("nextPageToken");
//            prevPageToken = (String)obj.get("prevPageToken");
            if (firstPage == true){
                prevPageToken = (String)obj.get("prevPageToken");
            }

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
                JSONObject mdefault = (JSONObject)thumbnails.get("medium");
                String murl = (String) mdefault.get("url");     //url 파싱(thumbnail)

                itemObjects.add(new ItemObject(id,publish,title,murl));
                firstPage = true;
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
