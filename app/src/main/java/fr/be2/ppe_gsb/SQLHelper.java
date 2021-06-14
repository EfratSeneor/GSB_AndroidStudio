package fr.be2.ppe_gsb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "GSB.db";
    private static final String DB_TABLE = "NoteDeForfait";
    public static final String KEY_ID = "ID";
    public static final String KEY_LIBELLE= "LIBELLE";
    public static final String KEY_MONTANT = "MONTANT";
    public static final String KEY_DATEDESAISIT = "DATEDESAISIT";
    public static final String KEY_DATEDEFRAIS = "DATEDEFRAIS";
    public static final String KEY_QUANTITE = "QUANTITE";
    public static final String KEY_TYPEDEFRAIS = "TYPEDEFRAIS";

    private static final String TAG = "SQLHelper";
    private SQLHelper dbHelper;
    private SQLiteDatabase db;

    // private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +" ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT" + ")";
    private static final String CREATE_TABLE = "CREATE TABLE NoteDeForfait (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPEDEFRAIS TEXT, LIBELLE TEXT, QUANTITE INTEGER, MONTANT FLOAT,DATEDEFRAIS TEXT, DATEDESAISIT DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public SQLHelper(Context context){
        super(context,DB_NAME,null,1);
    }

    public SQLHelper open() throws SQLException {
        // mDbHelper = new SQLHelper(mCtx);
        SQLiteDatabase db = this.getReadableDatabase();
        return this;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * Ajoute de données passées en paramètres à la base de données
     *
     * @param typefrais
     * @param libelle
     * @param quantite
     * @param montant
     * @param date
     *
     * @return
     */
    public boolean insertData(String typefrais, String libelle, Integer quantite, Double montant, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DATEDEFRAIS,typefrais);
        contentValues.put("LIBELLE", libelle);
        contentValues.put("QUANTITE", quantite);
        contentValues.put(KEY_MONTANT, montant);
        contentValues.put(KEY_DATEDEFRAIS, date);
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }

    /**
     * Renvoie tous les frais
     *
     * @return mCursor
     */
    public Cursor fetchAllFrais() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(DB_TABLE, new String[] {"rowid _id",KEY_ID,
                        KEY_LIBELLE, KEY_MONTANT, KEY_DATEDESAISIT, KEY_DATEDEFRAIS, KEY_QUANTITE,KEY_TYPEDEFRAIS},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Supprime des informations en fonction d'un id donne
     *
     * @param ID
     *
     * @return
     */
    public boolean  deleteData(Integer ID){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(DB_TABLE, "ID=" + ID, null);

        return result != -1;

    }
}
