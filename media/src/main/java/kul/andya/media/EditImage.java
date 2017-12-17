package kul.andya.media;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;

import kul.andya.media.Adapters.FilteredMediaAdapter;
import kul.andya.media.Widgets.GPUImageFilterTools;


public class EditImage extends Activity {

    private static final int REQUEST_PICK_IMAGE = 1;
    public static Bitmap imageSelected ;
    private RecyclerView recyclerView;



    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

        recyclerView = findViewById(R.id.recycler_view);

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_PICK_IMAGE);

    }
    void useImage(Uri uri)
    {
        try {

            imageSelected = getResizedBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri),100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //use the bitmap as you like

    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case REQUEST_PICK_IMAGE:
                if (resultCode == RESULT_OK) {

                    useImage(data.getData());
                    GPUImageFilterTools.load_filters();
                    FilteredMediaAdapter mAdapter = new FilteredMediaAdapter(getApplicationContext());
                    final int columns = getResources().getInteger(R.integer.gallery_media_columns);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),columns);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setItemViewCacheSize(30);
                    recyclerView.getItemAnimator().setChangeDuration(0);
                    recyclerView.setAdapter(mAdapter);

                } else {
                    finish();
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


}

