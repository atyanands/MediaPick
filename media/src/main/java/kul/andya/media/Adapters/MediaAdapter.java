package kul.andya.media.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import kul.andya.media.OpenGallery;
import kul.andya.media.R;

import static java.security.AccessController.getContext;


public class MediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> bitmapList;
    private List<Boolean> selected;
    private Context context;

    public MediaAdapter(List<String> bitmapList, List<Boolean> selected, Context context) {
        this.bitmapList = bitmapList;
        this.context = context;
        this.selected = selected;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RecyclerView.ViewHolder holder = null;

        assert inflater != null;
        holder = new MyViewHolder(inflater.inflate(R.layout.media_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        final int columns = context.getResources().getInteger(R.integer.gallery_media_columns);

        final int size = getScreenWidth()/columns;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size,size);
        params.setMargins(5,5,0,0);
        myViewHolder.thumbnail.setLayoutParams(params);



        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().centerCrop().skipMemoryCache(true).dontAnimate()).load("file://" + bitmapList.get(position)).into(myViewHolder.thumbnail);
        if (selected.get(position).equals(true)) {
            myViewHolder.thumbnail.setPadding(10,10,15,10);
            myViewHolder.check.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.thumbnail.setPadding(0,0,0,0);

            myViewHolder.check.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail, check;

        public MyViewHolder(View view) {
            super(view);

            thumbnail = view.findViewById(R.id.image);
            check = view.findViewById(R.id.image2);


        }
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}

