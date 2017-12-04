package kul.andya.media.Adapters;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.List;

import kul.andya.media.R;


public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder>{
    private List<String> bitmapList;
    private List<Boolean> selected;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail,check;
        public TextView shape_text;

        public MyViewHolder(View view) {
            super(view);

            thumbnail= view.findViewById(R.id.image);
            check= view.findViewById(R.id.image2);
            shape_text = view.findViewById(R.id.text_view_shape);

        }
    }
    public MediaAdapter(List<String> bitmapList,List<Boolean> selected, Context context) {
        this.bitmapList = bitmapList;
        this.context=context;
        this.selected=selected;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().centerCrop().skipMemoryCache(true).dontAnimate()).load("file://"+bitmapList.get(position)).into(holder.thumbnail);
        if(selected.get(position).equals(true)){
            holder.shape_text.setBackground(context.getResources().getDrawable(R.drawable.shape_selected));
        }else{
            holder.shape_text.setBackground(context.getResources().getDrawable(R.drawable.shape_unselected));        }

    }

    @Override
   public int getItemCount() {
        return bitmapList.size();
    }
}

