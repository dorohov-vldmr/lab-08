package ru.rabiarill.lab_08;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Notes (id INT, txt TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNote(int id, String stxt) {
        String sid = String.valueOf(id);
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Notes VALUES (" + sid + ", '" + stxt + "');";
        db.execSQL(sql);
    }

    public int getMaxId()
    {
        SQLiteDatabase db = getReadableDatabase ();
        String sql = "SELECT MAX(id) FROM Notes;";
        Cursor cur = db.rawQuery (sql, null);
        if (cur.moveToFirst()) return cur.getInt( 0);
        return 0;
    }

    public String getNote(int id) {
        String sid = String.valueOf(id);
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT txt FROM Notes WHERE id = " + sid + ";";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst()) return cur.getString(0);
        return "";
    }

    public void getAllNotes (ArrayList<Note> lst)
    {
        SQLiteDatabase db = getReadableDatabase ();
        String sql = "SELECT id, txt FROM Notes;";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst()) {
            do {
                Note n = new Note();
                n.id = cur.getInt ( 0);
                n.txt = cur.getString( 1);
                lst.add(n);
            } while (cur.moveToNext());
        }
    }

    // TODO: add "alterNote" function to save edited note

    public void alterNote(int id, String txt){
        SQLiteDatabase db = getReadableDatabase ();
        String sql = "UPDATE Notes SET txt ='"+ txt + "' WHERE id="+id+";";
        db.execSQL(sql);
    }
    // TODO: add "deleteNote" function to delete existing note

    public void deleteNote(int id){
        String sql = "DELETE FROM Notes WHERE id='" + id + "';";
        SQLiteDatabase db = getReadableDatabase();

        db.execSQL(sql);

    }


}
