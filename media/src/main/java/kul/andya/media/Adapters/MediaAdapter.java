package kul.andya.media.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import kul.andya.media.R;


public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder>{
    private List<String> bitmapList;
    private List<Boolean> selected;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RoundedImageView thumbnail,check;

        public MyViewHolder(View view) {
            super(view);

            thumbnail= (RoundedImageView) view.findViewById(R.id.image);
            check= (RoundedImageView) view.findViewById(R.id.image2);
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

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().override(153,160).centerCrop().skipMemoryCache(true).dontAnimate()).load("file://"+bitmapList.get(position)).into(holder.thumbnail);
        if(selected.get(position).equals(true)){
            holder.check.setVisibility(View.VISIBLE);
            holder.check.setAlpha(150);
        }else{
            holder.check.setVisibility(View.GONE);
        }

    }

    @Override
   public int getItemCount() {
        return bitmapList.size();
    }
}

