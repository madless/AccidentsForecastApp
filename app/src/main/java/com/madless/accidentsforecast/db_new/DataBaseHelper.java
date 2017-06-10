package com.madless.accidentsforecast.db_new;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.madless.accidentsforecast.utils.Const;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by User on 04.06.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    //destination path (location) of our database on device
    private static String DB_PATH = "";
    private SQLiteDatabase db;
    private final Context mContext;

    public DataBaseHelper(Context context) {
        super(context, Const.DB_DATA_FILENAME, null, 1);// 1? Its database Version
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    public void createDataBase(InputStream input) throws IOException {
        //If the database does not exist, copy it from the assets.
        this.getReadableDatabase();
        this.close();
        try {
            //Copy the database from assests
            copyDataBase(input);
            Log.e(TAG, "createDatabase database created");
        }
        catch (IOException mIOException) {
            throw new Error("ErrorCopyingDataBase");
        }
    }

    //Check that the database exists here: /data/data/your package/databases/Da Name
    public boolean isDbExists() {
        File dbFile = new File(DB_PATH + Const.DB_DATA_FILENAME);
        return dbFile.exists();
    }

    //Copy the database from stream
    private void copyDataBase(InputStream input) throws IOException {
//        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + Const.DB_DATA_FILENAME;
        OutputStream output = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = input.read(mBuffer))>0)
        {
            output.write(mBuffer, 0, mLength);
        }
        output.flush();
        output.close();
        input.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        String mPath = DB_PATH + Const.DB_DATA_FILENAME;
        //Log.v("mPath", mPath);
        db = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //db = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return db != null;
    }

    @Override
    public synchronized void close() {
        if(db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}