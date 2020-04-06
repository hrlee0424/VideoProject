package hyerim.my.videoproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.videoproject.MyApplication;
import hyerim.my.videoproject.dbmanager.DBManager;
import hyerim.my.videoproject.activity.PlayActivity;
import hyerim.my.videoproject.R;
import hyerim.my.videoproject.object.ItemObject;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    public ArrayList<ItemObject> itemObjects;
    private Context context;
    private ImageView mThumbnailImage, img_boomark;
    private ItemObject item, itemNo;
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
        item = itemObjects.get(position);
        holder.mtitle.setText(Html.fromHtml(item.title));
        holder.mpublish.setText(item.publishedAt);
        holder.videonum = item.videoId;
//        holder.num.setText(item.videoId);
        itemNo = item;
        String url = item.url;
        Glide.with(this.context).load(url).into(mThumbnailImage);   //ThumbnailImage load
    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mtitle, mpublish, num;
        String videonum;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.title);
            mpublish = itemView.findViewById(R.id.published);
//            num = itemView.findViewById(R.id.videonum);
            mThumbnailImage = itemView.findViewById(R.id.thumbnailImage);
            img_boomark = itemView.findViewById(R.id.img_boomark);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlayActivity.class);
                    intent.putExtra("videoid",videonum);
                    v.getContext().startActivity(intent);
                }
            });

            img_boomark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.getInstance().dbManager.insert(itemObjects.get(getPosition()));
                    Toast.makeText(context, "즐겨찾기에 저장되었습니다.", Toast.LENGTH_LONG).show();
//                    img_boomark.setImageResource();
                }
            });
        }
    }
}
