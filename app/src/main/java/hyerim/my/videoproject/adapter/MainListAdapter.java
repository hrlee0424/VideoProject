package hyerim.my.videoproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.videoproject.MainActivity;
import hyerim.my.videoproject.R;
import hyerim.my.videoproject.object.ItemObject;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    public ArrayList<ItemObject> itemObjects;
    private Context context;
    private HttpURLConnection connection = null;
    private ImageView mThumbnailImage;

    public MainListAdapter(Context context, ArrayList<ItemObject> itemObjects){
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @NonNull
    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlistview_item,null,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainListAdapter.ViewHolder holder, int position) {
        ItemObject item = itemObjects.get(position);
        holder.mtitle.setText(item.title);
        holder.mpublish.setText(item.publishedAt);
        String url = item.url;
        Glide.with(this.context).load(url).into(mThumbnailImage);   //ThumbnailImage load
    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtitle, mpublish;
//        private ImageView mThumbnailImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.title);
            mpublish = itemView.findViewById(R.id.published);
            mThumbnailImage = itemView.findViewById(R.id.thumbnailImage);

        }
    }
}
