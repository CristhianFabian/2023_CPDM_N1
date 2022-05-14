package com.example.appmanga;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 3;
    private static final String NOME = "livraria";

    public Banco(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS genero ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " nome TEXT NOT NULL ); "
        );

        db.execSQL( "CREATE TABLE IF NOT EXISTS manga ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " titulo TEXT NOT NULL," +
                " autor TEXT ," +
                " codGenero INT ," +
                " npaginas INT ); "
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antiga, int atual) {
        if(atual == 2){
            sqLiteDatabase.execSQL("delete from genero");
        }
        if (atual==3){
            onCreate(sqLiteDatabase);
        }

    }
}
