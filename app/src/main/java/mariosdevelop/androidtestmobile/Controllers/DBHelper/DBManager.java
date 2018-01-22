package mariosdevelop.androidtestmobile.Controllers.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by marioaguilar on 19/01/18.
 */

public class DBManager extends SQLiteOpenHelper {

    public static final String [] MArcas = {"ClaroMusica","ClaroDrive","ClaroMovies","TuentiMusica","TuentiDrive","TuentiMovies","EntelMusica","EntelDrive","EntelMovies"};

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String MARCAS_TABLE_NAME = "marcas";
    public static final String MARCAS_COLUMN_ID = "id";
    public static final String MARCAS_COLUMN_NAME = "name";
    private HashMap hp;

    public DBManager(Context context){
        super(context,DATABASE_NAME,null,1);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Creando la DB


        sqLiteDatabase.execSQL(
                "create table marcas " +
                        "(id integer primary key, name text)"
        );



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS marcas");
        onCreate(sqLiteDatabase);
    }

    public void CreateDataMarcas(){

        this.onUpgrade(this.getReadableDatabase(),1,1);

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues =  new ContentValues();
        for(int i= 0;  i< MArcas.length;i++){
            contentValues.put("name",MArcas[i]);
            db.insert("marcas",null,contentValues);
        }
      //  db.insert("marcas",null,contentValues);
    }

    public ArrayList<String> getData(){

        ArrayList<String> NombresMarcas = new ArrayList<>();

        SQLiteDatabase db   = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from marcas",null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){

            NombresMarcas.add(cursor.getString(cursor.getColumnIndex(MARCAS_COLUMN_NAME)));
            cursor.moveToNext();

        }


        return NombresMarcas;
    }


    private void Oncreate (){

    }


}
