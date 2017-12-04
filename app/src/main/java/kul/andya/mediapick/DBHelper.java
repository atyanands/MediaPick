package kul.andya.mediapick;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;


/**
 * Created by Hp on 3/18/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase mDB;
    static final String ROW_ID = "id";

    static final String SERVER_MSG_ID = "server_msg_id";
    static final String LOCAL_MSG_ID = "local_msg_id";

    static final String TABLE_NAME = "table_name";

    static final String GROUP_MESSAGE_TOPIC = "group_message_topic";
    static final String SENDER_NAME = "sender_name";
    static final String MESSAGE_TEXT = "message_text";
    static final String GROUP_NAME = "group_name";
    static final String CHAT_LABEL = "chat_label";

    static final String TIME_IN_STRING = "time_in_string";

    static final String GRAVITY = "gravity";
    static final String MESSAGE_ENTITY_TYPE = "entity";

    static final String MESSAGE_TYPE = "message_type";
    static final String MESSAGE_DELIVERY_STATUS = "message_delivery_status";


    static final String MESSAGE_TYPE_CONTACT_NAME = "message_type_contact_name";
    static final String MESSAGE_TYPE_CONTACT_NUMBER = "message_type_contact_number";
    static final String MESSAGE_TYPE_CONTACT_EMAIL = "message_type_contact_email";
    static final String MESSAGE_TYPE_CONTACT_PROFILE_IMAGE = "message_type_contact_image";

    static final String MESSAGE_TYPE_VOICE_MESSAGE_LENGTH = "message_type_voice_message_length";

    static final String SERVER_FILE_PATH = "server_file_path";
    static final String LOCAL_FILE_PATH = "local_file_path";

    static final String TOTAL_UNREAD_MESSAGES = "unread";
    static final String TOKEN = "token";
    static final String IS_CHAT_MUTE = "mute";

    static final String MAIN_ACTIVITY_TABLE = "user_group_table";
    static final String USER_OR_GROUP_TABLE = "user_or_group_table";
    static final String GROUP_MEMBERS_TABLE = "group_member_table";
    static final String PENDING_MESSAGES_TABLE = "pending_messages_table";

    static final int DB_VERSION = '1';
    static final String DB_NAME = "d_DB";


    Context mcontext;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mcontext = context;
    }

    //TABLE CREATION
    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    //TABLE UPGRADE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    public void Create_message_table(String table_name) {
        try {
            mDB = this.getWritableDatabase();
            final String tabel = "CREATE TABLE " + table_name + " (" +
                    ROW_ID + " INTEGER PRIMARY KEY, " +
                    MESSAGE_ENTITY_TYPE + " INTEGER NOT NULL, " +
                    SERVER_MSG_ID + " TEXT NOT NULL, " +
                    LOCAL_MSG_ID + " TEXT NOT NULL, " +
                    GROUP_NAME + " TEXT NOT NULL, " +
                    SENDER_NAME + " TEXT NOT NULL, " +
                    MESSAGE_TEXT + " TEXT NOT NULL, " +
                    TIME_IN_STRING + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_CONTACT_NAME + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_CONTACT_NUMBER + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_CONTACT_EMAIL + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_CONTACT_PROFILE_IMAGE + " TEXT NOT NULL, " +

                    MESSAGE_TYPE_VOICE_MESSAGE_LENGTH + " INTEGER NOT NULL, " +

                    GRAVITY + " INTEGER NOT NULL, " +
                    LOCAL_FILE_PATH + " TEXT NOT NULL, " +
                    MESSAGE_TYPE + " INTEGER NOT NULL, " +
                    SERVER_FILE_PATH + " TEXT NOT NULL, " +
                    MESSAGE_DELIVERY_STATUS + " INTEGER NOT NULL, " +
                    GROUP_MESSAGE_TOPIC + " TEXT NOT NULL, " +
                    CHAT_LABEL + " TEXT NOT NULL ); ";
            Log.e("table",tabel);
            mDB.execSQL(tabel);
            mDB.close();
            mDB = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Create_Main_Activity_Chat_table() {
        try {
            mDB = this.getWritableDatabase();
            mDB.execSQL("CREATE TABLE " + MAIN_ACTIVITY_TABLE + " ( " +
                    ROW_ID + " INTEGER PRIMARY KEY, " +
                    TABLE_NAME + " TEXT NOT NULL, " +
                    MESSAGE_ENTITY_TYPE + " INTEGER NOT NULL, " +
                    SERVER_MSG_ID + " TEXT NOT NULL, " +
                    LOCAL_MSG_ID + " TEXT NOT NULL, " +
                    GROUP_NAME + " TEXT NOT NULL, " +
                    SENDER_NAME + " TEXT NOT NULL, " +
                    MESSAGE_TEXT + " TEXT NOT NULL, " +
                    TIME_IN_STRING + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_CONTACT_NAME + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_CONTACT_NUMBER + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_CONTACT_EMAIL + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_CONTACT_PROFILE_IMAGE + " TEXT NOT NULL, " +
                    MESSAGE_TYPE_VOICE_MESSAGE_LENGTH + " INTEGER NOT NULL, " +
                    GRAVITY + " INTEGER NOT NULL, " +
                    LOCAL_FILE_PATH + " TEXT NOT NULL, " +
                    MESSAGE_TYPE + " INTEGER NOT NULL, " +
                    SERVER_FILE_PATH + " TEXT NOT NULL, " +
                    MESSAGE_DELIVERY_STATUS + " INTEGER NOT NULL, " +
                    GROUP_MESSAGE_TOPIC + " TEXT NOT NULL, " +
                    CHAT_LABEL + " TEXT NOT NULL); ");
            mDB.close();
            mDB = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Create_User_Group_table() {
        try {
            mDB = this.getWritableDatabase();
            mDB.execSQL("CREATE TABLE " + USER_OR_GROUP_TABLE + " ( " +
                    ROW_ID + " INTEGER PRIMARY KEY, " +
                    TABLE_NAME + " TEXT NOT NULL," +
                    GROUP_NAME + " TEXT NOT NULL, " +
                    SENDER_NAME + " TEXT NOT NULL, " +
                    TOTAL_UNREAD_MESSAGES + " INTEGER, " +
                    TOKEN + " TEXT, " +
                    IS_CHAT_MUTE + " INTEGER, " +
                    CHAT_LABEL + " TEXT NOT NULL ); ");
            mDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Create_Group_Members_table() {
        try {
            mDB = this.getWritableDatabase();
            mDB.execSQL("CREATE TABLE " + GROUP_MEMBERS_TABLE + " ( " +
                    ROW_ID + " INTEGER PRIMARY KEY, " +
                    TABLE_NAME + " TEXT NOT NULL," +
                    GROUP_NAME + " TEXT NOT NULL, " +
                    SENDER_NAME + " TEXT NOT NULL); ");
            mDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Create_Pending_Messages_table() {
        try {
            mDB = this.getWritableDatabase();
            mDB.execSQL("CREATE TABLE " + PENDING_MESSAGES_TABLE + " ( " +
                    ROW_ID + " INTEGER PRIMARY KEY, " +
                    TABLE_NAME + " TEXT NOT NULL," +
                    LOCAL_MSG_ID + " TEXT NOT NULL)");
            mDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add_new_message(String table_name,
                                String server_msg_id,
                                String local_msg_id,
                                String group_name,
                                String sender_name,
                                String message_text,
                                String time_in_string,
                                String local_file_path,
                                String server_file_path,
                                String group_message_topic,
                                String chat_label,
                                String msg_type_contact_name,
                                String msg_type_contact_number,
                                String msg_type_contact_email,
                                String msg_type_contact_dp,
                                int entity,
                                int gravity,
                                int message_type,
                                int voice_msg_length,
                                int delivery_status) {
        try {
            mDB = this.getWritableDatabase();
            Cursor d = null;
            try {
                String whereClause = SERVER_MSG_ID + " = ?";
                String[] whereArgs = new String[]{server_msg_id};
                String[] columns = {ROW_ID,SERVER_MSG_ID,LOCAL_MSG_ID};
                d = mDB.query(table_name,columns,whereClause,whereArgs,null,null,null);

               } catch (Exception e) {
                Log.e("hiee","baby");
                e.printStackTrace();
            }
            if (!(d.getCount() > 0)) {
                Log.e("rewquesr", "nthing found");
                ContentValues cv = new ContentValues();
                cv.put(MESSAGE_ENTITY_TYPE, entity);
                cv.put(SERVER_MSG_ID, server_msg_id);
                cv.put(LOCAL_MSG_ID, local_msg_id);
                cv.put(GROUP_NAME, group_name);
                cv.put(SENDER_NAME, sender_name);
                cv.put(MESSAGE_TEXT, message_text);
                cv.put(TIME_IN_STRING, time_in_string);
                cv.put(MESSAGE_TYPE_CONTACT_NAME, msg_type_contact_name);
                cv.put(MESSAGE_TYPE_CONTACT_NUMBER, msg_type_contact_number);
                cv.put(MESSAGE_TYPE_CONTACT_EMAIL, msg_type_contact_email);
                cv.put(MESSAGE_TYPE_CONTACT_PROFILE_IMAGE, msg_type_contact_dp);
                cv.put(MESSAGE_TYPE_VOICE_MESSAGE_LENGTH, voice_msg_length);
                cv.put(GRAVITY, gravity);
                cv.put(LOCAL_FILE_PATH, local_file_path);
                cv.put(SERVER_FILE_PATH, server_file_path);
                cv.put(MESSAGE_TYPE, message_type);
                cv.put(MESSAGE_DELIVERY_STATUS, delivery_status);
                cv.put(GROUP_MESSAGE_TOPIC, group_message_topic);
                cv.put(CHAT_LABEL, chat_label);

                mDB.insert(table_name, ROW_ID, cv);

//                Intent intent = new Intent(C.PUSH_NOTIFICATION);
//                // You can also include some extra data.
//                intent.putExtra("message", "This is my message!");
//
//                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
                add_new_chatList(table_name,
                        server_msg_id,
                        local_msg_id,
                        group_name,
                        sender_name,
                        message_text,
                        time_in_string,
                        local_file_path,
                        server_file_path,
                        group_message_topic,
                        chat_label,
                        msg_type_contact_name,
                        msg_type_contact_number,
                        msg_type_contact_email,
                        msg_type_contact_dp,
                        entity,
                        gravity,
                        message_type,
                        voice_msg_length,
                        delivery_status);

//            if (gravity == Gravity.LEFT) {
//                mFirebaseFunctions.Get_token(msg_id,sender,"delivered");
//            }

            }
            d.close();
            Intent intent = new Intent("pushNotification");
            intent.putExtra("message", "This is my message!");
            LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
            mDB.close();
            mDB = null;
        } catch (Exception e) {

        }

    }

    public void add_new_chatList(String table_name,
                                 String server_msg_id,
                                 String local_msg_id,
                                 String group_name,
                                 String sender_name,
                                 String message_text,
                                 String time_in_string,
                                 String local_file_path,
                                 String server_file_path,
                                 String group_message_topic,
                                 String chat_label,
                                 String msg_type_contact_name,
                                 String msg_type_contact_number,
                                 String msg_type_contact_email,
                                 String msg_type_contact_dp,
                                 int entity,
                                 int gravity,
                                 int message_type,
                                 int voice_msg_length,
                                 int delivery_status) {
        if (message_text.length() > 30) {
            message_text = message_text.substring(0, 30);
        }
        try {
            mDB = this.getWritableDatabase();
            Cursor d = null;
            try {
                String whereClause = TABLE_NAME + " = ?";
                String[] whereArgs = new String[]{table_name};
                String[] columns = {ROW_ID,TABLE_NAME,SERVER_MSG_ID,LOCAL_MSG_ID};
                d = mDB.query(MAIN_ACTIVITY_TABLE,columns,whereClause,whereArgs,null,null,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("rewquesr", "nthing found");
            ContentValues cv = new ContentValues();
            cv.put(TABLE_NAME, table_name);
            cv.put(MESSAGE_ENTITY_TYPE, entity);
            cv.put(SERVER_MSG_ID, server_msg_id);
            cv.put(LOCAL_MSG_ID, local_msg_id);
            cv.put(GROUP_NAME, group_name);
            cv.put(SENDER_NAME, sender_name);
            cv.put(MESSAGE_TEXT, message_text);
            cv.put(TIME_IN_STRING, time_in_string);
            cv.put(MESSAGE_TYPE_CONTACT_NAME, msg_type_contact_name);
            cv.put(MESSAGE_TYPE_CONTACT_NUMBER, msg_type_contact_number);
            cv.put(MESSAGE_TYPE_CONTACT_EMAIL, msg_type_contact_email);
            cv.put(MESSAGE_TYPE_CONTACT_PROFILE_IMAGE, msg_type_contact_dp);
            cv.put(MESSAGE_TYPE_VOICE_MESSAGE_LENGTH, voice_msg_length);
            cv.put(GRAVITY, gravity);
            cv.put(LOCAL_FILE_PATH, local_file_path);
            cv.put(SERVER_FILE_PATH, server_file_path);
            cv.put(MESSAGE_TYPE, message_type);
            cv.put(MESSAGE_DELIVERY_STATUS, delivery_status);
            cv.put(GROUP_MESSAGE_TOPIC, group_message_topic);
            cv.put(CHAT_LABEL, chat_label);

            if ((d.getCount() > 0)) {
                String[] args = {table_name};
                mDB.update(MAIN_ACTIVITY_TABLE, cv, TABLE_NAME + " =?", args);

            } else {
                mDB.insert(MAIN_ACTIVITY_TABLE, ROW_ID, cv);
            }
            d.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cursor get_chat_list(String label) {

        try {
            mDB = this.getWritableDatabase();
            if (label.toString().equals("All")) {
                String[] columns = {
                        ROW_ID, TABLE_NAME, SERVER_MSG_ID, LOCAL_MSG_ID ,GROUP_NAME,
                        SENDER_NAME, MESSAGE_TEXT, TIME_IN_STRING,
                        GROUP_MESSAGE_TOPIC, MESSAGE_ENTITY_TYPE, GRAVITY, MESSAGE_TYPE,
                        MESSAGE_DELIVERY_STATUS, MESSAGE_TYPE_VOICE_MESSAGE_LENGTH,
                        MESSAGE_TYPE_CONTACT_NAME, MESSAGE_TYPE_CONTACT_NUMBER,
                        MESSAGE_TYPE_CONTACT_EMAIL};


                return mDB.query(MAIN_ACTIVITY_TABLE,
                        columns,
                        null,
                        null,
                        null,
                        null,
                        TIME_IN_STRING + " DESC");
            } else {
                String whereClause = CHAT_LABEL + " = ?";
                String[] whereArgs = new String[]{label};
                String[] columns = {
                        ROW_ID, TABLE_NAME, SERVER_MSG_ID,LOCAL_MSG_ID, GROUP_NAME,
                        SENDER_NAME, MESSAGE_TEXT, TIME_IN_STRING,
                        GROUP_MESSAGE_TOPIC, MESSAGE_ENTITY_TYPE, GRAVITY, MESSAGE_TYPE,
                        MESSAGE_DELIVERY_STATUS, MESSAGE_TYPE_VOICE_MESSAGE_LENGTH,
                        MESSAGE_TYPE_CONTACT_NAME, MESSAGE_TYPE_CONTACT_NUMBER,
                        MESSAGE_TYPE_CONTACT_EMAIL};
                try {
                    return mDB .query(MAIN_ACTIVITY_TABLE,
                                    columns,
                                    whereClause,
                                    whereArgs,
                                    null,
                                    null,
                                    TIME_IN_STRING + " DESC");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void close_cursor() {
        this.close();
        mDB = null;
    }


}
