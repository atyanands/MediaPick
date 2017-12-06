package kul.andya.media.Adapters;


import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.util.List;

import kul.andya.media.R;


public class BucketsAdapter extends RecyclerView.Adapter<BucketsAdapter.MyViewHolder>{
    private List<String> bucketNames,bitmapList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbnail= view.findViewById(R.id.image);
        }
    }

    public BucketsAdapter(List<String> bucketNames,List<String> bitmapList,Context context) {
        this.bucketNames=bucketNames;
        this.bitmapList = bitmapList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);

        return new MyViewHolder(itemView);
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        bucketNames.get(position);

        final int columns = context.getResources().getInteger(R.integer.gallery_columns);
        final int size = getScreenWidth()/columns;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size,size);
        params.setMargins(5,5,0,0);
        holder.thumbnail.setLayoutParams(params);




        holder.title.setText(bucketNames.get(position));
        Glide.with(context).load("file://"+bitmapList.get(position)).apply(new RequestOptions().centerCrop()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return bucketNames.size();
    }
}

