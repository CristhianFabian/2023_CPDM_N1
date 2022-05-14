package com.example.appmanga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MangaDAO {

    public static void inserir(Context context, Manga manga){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("titulo", manga.getTitulo());
        valores.put("autor", manga.getAutor());
        valores.put("codGenero", manga.getGenero().getId());
        db.insert("manga", null, valores);
        db.close();
    }
    public static void editar(Context context, Manga manga){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("titulo", manga.getTitulo());
        valores.put("autor", manga.getAutor());
        valores.put("codGenero", manga.getGenero().getId());
        db.update("manga", valores, "id = " + manga.getId(), null);
        db.close();
    }
    public static void excluir(Context context, int idManga){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues valores = new ContentValues();
        db.delete("manga", "id = " + idManga, null);
        db.close();
    }


    public static List<Manga> getMangas(Context context){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery( " SELECT m.id, m.titulo, m.autor, g.id, g.nome "+
                                         " FROM manga m inner join genero g on g.id = m.codGenero "+
                                         " ORDER BY m.titulo ", null );

        List<Manga> lista = new ArrayList<>();

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            do{
                Genero g = new Genero();
                g.setId(  cursor.getInt( 3 ) );
                g.setNome(  cursor.getString( 4 ) );
                Manga m =  new Manga();
                m.setId(cursor.getInt(0));
                m.setTitulo(cursor.getString(1));
                m.setAutor(cursor.getString(2));
                m.setGenero( g );
                lista.add( m );
            }while (cursor.moveToNext());
        }
        return lista;
    }

}
