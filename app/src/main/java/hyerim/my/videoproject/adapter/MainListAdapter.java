package hyerim.my.videoproject.adapter;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.videoproject.R;
import hyerim.my.videoproject.object.ItemObject;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    public ArrayList<ItemObject> itemObjects;
    private Context context;
    private HttpURLConnection connection = null;

    public MainListAdapter(Context context, ArrayList<ItemObject> itemObjects){
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @NonNull
    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.mainlistview_item,parent,false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlistview_item,null,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainListAdapter.ViewHolder holder, int position) {
        ItemObject item = itemObjects.get(position);
        holder.mtitle.setText(item.title);
        holder.mpublish.setText(item.publishedAt);
    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }

    /*
    *         URL url = null;
        try {
            url = new URL(item.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setDoInput(true);
        try {
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        holder.mThumbnailImage.setImageBitmap(bitmap);
        * */
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtitle, mpublish;
        private ImageView mThumbnailImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.title);
            mpublish = itemView.findViewById(R.id.published);
            mThumbnailImage = itemView.findViewById(R.id.thumbnailImage);

        }
    }
}
