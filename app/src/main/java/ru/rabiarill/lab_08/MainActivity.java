package ru.rabiarill.lab_08;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lstctl;
    ArrayList<Note> lst = new ArrayList<>();
    ArrayAdapter<Note> adp;

    Context ctx;

    void update_list()
    {
        lst.clear();
        g.notes.getAllNotes(lst);
        adp.notifyDataSetChanged() ;
    }
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstctl = findViewById(R.id.listNotes);

        g.notes = new DB(this, "Notes.db", null, 1);
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lst);

        lstctl.setAdapter(adp);

        ctx = this;

        lstctl.setOnItemClickListener((parent, view, position, id) ->{
            Note n = adp.getItem(position);
            Intent i = new Intent(ctx, MainActivity2.class); i.putExtra( "note-id", n.id);
            i.putExtra("note-txt", n.txt);
            startActivityForResult(i, 1);
         });
        update_list();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        update_list();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        //return super.onCreateOptions/enu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.new_item: {
                int nid = g.notes.getMaxId() + 1;

                g.notes.addNote(nid, "Hello, world!");
                update_list();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }


}