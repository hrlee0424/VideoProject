package hyerim.my.videoproject.object;

import android.content.ClipData;

import org.json.JSONObject;

public class ItemObject {
   public String videoId, publishedAt, title, url;

   public ItemObject(String videoId, String publishedAt, String title, String url){
      super();
      this.videoId = videoId;
      this.publishedAt = publishedAt;
      this.title = title;
      this.url = url;
   }
}
