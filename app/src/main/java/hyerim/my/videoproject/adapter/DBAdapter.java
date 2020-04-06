package hyerim.my.videoproject.adapter;

import android.content.Context;
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
import hyerim.my.videoproject.R;
import hyerim.my.videoproject.object.ItemObject;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.ViewHolder> {
    public ArrayList<ItemObject> itemObjects;
    private Context context;
    private ImageView mThumbnailImage, mbookmark;
    public DBAdapter(Context context){this.context = context;}


    public void setList(ArrayList<ItemObject> item){
        itemObjects = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DBAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmarklistview_item, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DBAdapter.ViewHolder holder, int position) {
        ItemObject item = itemObjects.get(position);
        holder.mtitle.setText(Html.fromHtml(item.title));
        holder.mpublish.setText(item.publishedAt);
        holder.videonum = item.videoId;
//        holder.num.setText(item.videoId);
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.bm_title);
            mpublish = itemView.findViewById(R.id.bm_published);
//            num = itemView.findViewById(R.id.videonum);
            mThumbnailImage = itemView.findViewById(R.id.bm_thumbnailImage);
            mbookmark = itemView.findViewById(R.id.bm_img_boomark);

            mbookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.getInstance().dbManager.delete(mtitle.getText().toString());
                    Toast.makeText(context,"즐겨찾기가 해제되었습니다.", Toast.LENGTH_SHORT).show();
                    itemObjects = MyApplication.getInstance().dbManager.itemObjects();
                    setList(itemObjects);
                }
            });

        }
    }
}
