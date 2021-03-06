package kul.andya.media;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


import java.util.ArrayList;
import java.util.List;

import kul.andya.media.Adapters.MediaAdapter;
import kul.andya.media.Fragments.ViewImageFragment;
import kul.andya.media.Fragments.ViewVideosFragment;

public class OpenGallery extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MediaAdapter mAdapter;
    private List<String> mediaList=new ArrayList<>();
    public static List<Boolean> selected=new ArrayList<>();
    public static ArrayList<String> imagesSelected=new ArrayList<>();
    public static String parent;
    public static String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_open_gallery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        setTitle(Gallery.title);

        if(imagesSelected.size()>0){
            setTitle(String.valueOf(imagesSelected.size()));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        parent=getIntent().getExtras().getString("FROM");
        mediaList.clear();
        selected.clear();
        if(parent.equals("Images")){
            mediaList.addAll(ViewImageFragment.imagesList);
            selected.addAll(ViewImageFragment.selected);
        }else{
            mediaList.addAll(ViewVideosFragment.videosList);
            selected.addAll(ViewVideosFragment.selected);
        }
        populateRecyclerView();
    }


    private void populateRecyclerView() {
        for(int i=0;i<selected.size();i++){
            if(imagesSelected.contains(mediaList.get(i))){
                selected.set(i,true);
            }else {
                selected.set(i,false);
            }
        }
        mAdapter = new MediaAdapter(mediaList,selected,getApplicationContext());
        final int columns = getResources().getInteger(R.integer.gallery_media_columns);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),columns);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(!selected.get(position).equals(true)){
                    imagesSelected.add(mediaList.get(position)+ "kfhgdfksehrtkdyfcgdkdkbsgfvakhdb"+parent);
                }else {
                    if(imagesSelected.indexOf(mediaList.get(position))!= -1) {
                        imagesSelected.remove(imagesSelected.indexOf(mediaList.get(position)+ "kfhgdfksehrtkdyfcgdkdkbsgfvakhdb"+parent));
                    }
                }
                Gallery.selectionTitle=imagesSelected.size();
                selected.set(position,!selected.get(position));
                mAdapter.notifyItemChanged(position);
                if(imagesSelected.size()!=0){
                     setTitle(String.valueOf(imagesSelected.size()));
                }else{
                    setTitle(Gallery.title);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

}

