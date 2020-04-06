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

   public void setVideoId(String videoId) {
      this.videoId = videoId;
   }

   public void setPublishedAt(String publishedAt) {
      this.publishedAt = publishedAt;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getVideoId() {
      return videoId;
   }

   public String getPublishedAt() {
      return publishedAt;
   }

   public String getTitle() {
      return title;
   }

   public String getUrl() {
      return url;
   }
}
