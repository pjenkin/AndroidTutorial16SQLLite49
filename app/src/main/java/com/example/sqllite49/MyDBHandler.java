package com.example.sqllite49;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// for using a SQLite database
public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // 1 for 1st version (eg for change in version if field added, e.g.)
    private static final String DATABASE_NAME = "products.db";
    // name of db file which'll be kept on device
    public static final String TABLE_PRODUCTS = "products";
    // name of table(s) in db
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "_productname";     // 1 line per column/field

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
//    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    // lots of Alt+Insert for implementations

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // set up table (first time) if db file absent https://stackoverflow.com/a/21881993/11365317
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "("
        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + COLUMN_PRODUCTNAME + " TEXT "
        + ");" ;
        db.execSQL(query);
    }

    // https://www.sqlite.org/draft/lang_createtable.html

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // run on local device when a different DATABASE_VERSION is stated (eg after app upgrade)
        // https://stackoverflow.com/a/21881993/11365317
        // delete existing db and then re-make (via onCreate)
        String query = "DROP TABLE IF EXISTS " + TABLE_PRODUCTS;
        db.execSQL(query);
        onCreate(db);
    }


    // Add a new row to the database, with specified product
    public void addProduct(Product product)
    {
        // ContentValues https://developer.android.com/reference/android/content/ContentValues
        // ContentValues (from android.context) for get'ting and put'ting values from/to db

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    // Delete a specified product from the database, using productname text
    public void deleteProduct(String productname)
    {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_PRODUCTS + " WHERE "
                + COLUMN_PRODUCTNAME + " =\" " + productname + "\"";
        db.execSQL(query);
    }

}
