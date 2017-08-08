package edu.gatech.seclass.glm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import edu.gatech.seclass.glm.GLM;

/**
 * Created by Bj on 10/2/16.
 */

public class DbHelper extends SQLiteOpenHelper {
    private SQLiteDatabase myDB;
    static DbHelper Instance = new DbHelper(GLM.getContext());

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "grocery.sqlite";

    // Constructor
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create a database
    public void createDataBase(Context context) throws IOException {
        boolean dbExist = checkDataBase(context);

        // If the database doesn't exist, make an empty database and copy the data
        if (!dbExist) {
            // Close database
            this.getReadableDatabase().close();

            // Copy the data
            try {
                copyDataBase(context);
            } catch (IOException e) {
                throw new Error("Error copying database.");
            }
        }
    }

    // Check if the database file exists
    private boolean checkDataBase(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);

        return dbFile.exists();
    }

    /**
     * Copy database from asset folder
     * @throws IOException
     */
    private void copyDataBase(Context context) throws IOException {
        // Open your local database as the input stream
        InputStream myInput = context.getAssets().open(DATABASE_NAME);

        // Path to the just created empty database
        String path = context.getApplicationInfo().dataDir + "/databases/";
        String outFileName = path + DATABASE_NAME;

        // Open the empty database as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // Transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /**
     * Open the database
     */
    public void openDataBase() {
        try {
            myDB = this.getWritableDatabase();
        } catch (Exception e) {
            // On update the DB doesn't get released
            myDB = this.getWritableDatabase();
        }
    }

    @Override
    public synchronized void close() {
        if (myDB != null) {
            myDB.close();
        }

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    // When a newer database is found
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If the current version is older
        if (oldVersion < newVersion) {
            Context context = GLM.getContext();
            // Delete current database
            File dbFile = context.getDatabasePath(DATABASE_NAME);
            dbFile.delete();

            // Create the new database
            try {
                copyDataBase(context);
            } catch (IOException e) {
                throw new Error("Error copying database.");
            }
        }
    }
}
