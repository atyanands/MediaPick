package kul.andya.media.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import kul.andya.media.R;

public class RecentMediaAdapter
          extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> bitmapList;
    private List<Boolean> selected;
    private Context context;

    public RecentMediaAdapter(List<String> bitmapList, List<Boolean> selected, Context context) {
        this.bitmapList = bitmapList;
        this.context = context;
        this.selected = selected;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RecyclerView.ViewHolder holder;

        assert inflater != null;
        holder = new MyViewHolder(inflater.inflate(R.layout.recent_media_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().override(300,300).centerCrop().skipMemoryCache(true).dontAnimate()).load("file://" + bitmapList.get(position)).into(myViewHolder.thumbnail);
        if (selected.get(position).equals(true)) {
myViewHolder.thumbnail.setPadding(10,10,10,10);
            myViewHolder.check.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.check.setVisibility(View.GONE);
            myViewHolder.thumbnail.setPadding(0,0,0,0);

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
}

