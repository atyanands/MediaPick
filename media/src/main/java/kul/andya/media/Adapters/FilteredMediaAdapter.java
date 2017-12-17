package kul.andya.media.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.File;

import kul.andya.media.EditImage;
import kul.andya.media.GPUImage.GPUImageFilter;
import kul.andya.media.GPUImage.GPUImageView;
import kul.andya.media.R;
import kul.andya.media.Widgets.GPUImageFilterTools;


public class FilteredMediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private GPUImageFilter mFilter;
    private GPUImageFilterTools.FilterAdjuster mFilterAdjuster;
    public FilteredMediaAdapter(Context context) {
        this.context = context;


    }

    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        return new MyViewHolder(inflater.inflate(R.layout.filtered_media_item, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;


        final int columns = context.getResources().getInteger(R.integer.gallery_media_columns);

        final int size = getScreenWidth() / columns;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
        params.setMargins(5, 5, 0, 0);
        myViewHolder.gpuImageView.setLayoutParams(params);


        myViewHolder.gpuImageView.setImage(EditImage.imageSelected);

        GPUImageFilter filter = GPUImageFilterTools.createFilterForType(context, GPUImageFilterTools.FilterList.filters.get(position));
        switchFilterTo(filter,myViewHolder.gpuImageView);
        myViewHolder.gpuImageView.requestRender();


    }
    @Override
    public int getItemCount() {

        return GPUImageFilterTools.FilterList.filters.size();
    }

    private void switchFilterTo(final GPUImageFilter filter,GPUImageView mGPUImageView) {
        if (mFilter == null
                || (filter != null && !mFilter.getClass().equals(filter.getClass()))) {
            mFilter = filter;
            mGPUImageView.setFilter(mFilter);
            mFilterAdjuster = new GPUImageFilterTools.FilterAdjuster(mFilter);

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        GPUImageView gpuImageView;

        MyViewHolder(View view) {
            super(view);
            gpuImageView = view.findViewById(R.id.filteredgpuimage);

        }
    }


}


