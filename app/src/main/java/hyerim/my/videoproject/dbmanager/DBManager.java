package hyerim.my.videoproject.dbmanager;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import hyerim.my.videoproject.object.ItemObject;

public class DBManager extends SQLiteOpenHelper {
    private String videoId, publishedAt, title, url;

    public DBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE itemtable (" +
                "title TEXT, " +
                "videoId TEXT, " +
                "publishedAt TEXT, " +
                "url TEXT, " +
                "PRIMARY KEY(videoId)" +
                ");"
        );
    }

    public long insert(ItemObject object){
        ContentValues values = new ContentValues();
        values.put("title", String.valueOf(Html.fromHtml(object.title)));
        values.put("videoId", object.videoId);
        values.put("publishedAt", object.publishedAt);
        values.put("url", object.url);

        long ret = getWritableDatabase().insert("itemtable", null, values);
        return ret;
    }

    public long delete(String title){
        return  getWritableDatabase().delete("itemtable", "title=?", new String[]{title});

    }

    public ArrayList<ItemObject> itemObjects(){
        ArrayList<ItemObject> itemObjects = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query("itemtable",null,null,null, null,null,null);
        while (cursor.moveToNext()) {
            ItemObject itemObject = new ItemObject(null, null, null, null);
            itemObject.title=cursor.getString(cursor.getColumnIndex("title"));
            itemObject.videoId = cursor.getString(cursor.getColumnIndex("videoId"));
            itemObject.publishedAt = cursor.getString(cursor.getColumnIndex("publishedAt"));
            itemObject.url = cursor.getString(cursor.getColumnIndex("url"));
            itemObjects.add(itemObject);
        }
        return  itemObjects;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
