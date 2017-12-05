package kul.andya.mediapick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import kul.andya.media.Gallery;


public class MainActivity extends AppCompatActivity {

    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        helper = new DBHelper(this);
        helper.Create_message_table("yoo_baby");
        helper.Create_Main_Activity_Chat_table();
        helper.Create_Group_Members_table();
        helper.Create_Pending_Messages_table();
        helper.Create_User_Group_table();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Gallery.class);
                intent.putExtra("title", "zing");
                startActivityForResult(intent, 1);
//                final int random = new Random().nextInt(89998) + 10000;
//                String delegate = "yyyy.MM.dd.hh.mm.ss.AA.MMMM.MMM.EEEE.EE";
//                String a = (String) DateFormat.format(delegate, Calendar.getInstance().getTime());
//
//                Log.e("date",a+random);
//
//                helper.add_new_message("yoo_baby",
//                        "sjdgksdjvgsd",
//                        "yoo baby",
//                        "hfgwkug",
//                        "hfgwkug",
//                        "hfgwkug","hfgwkug","hfgwkug","hfgwkug",
//                        "hfgwkug","hfgwkug","hfgwkug","hfgwkug0",
//                        "hfgwkug","rf4rf4",5,2,5,8,9);
//            }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
