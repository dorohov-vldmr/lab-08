package ru.rabiarill.lab_08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText txtctl;
    int nid;
    String ntxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtctl = findViewById(R.id.txt_content_act2);

        Intent i = getIntent();
        nid = i.getIntExtra("note-id", 0);
        ntxt = i.getStringExtra("note-txt");

        txtctl.setText (ntxt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu (menu);
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();

            switch (id) {
                case R.id.item_save: {
                    // TODO: get text from text box, modify note, show toast "note saved" and exit
                    g.notes.alterNote(nid, txtctl.getText().toString());
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Запись сохранена", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;
                }
                case R.id.item_delete: {
                    // TODO: alert dialog with yes/no confirmation
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("Write your message here.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Да",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // TODO: delete note, show toast "note deleted" and exit activity
                                    g.notes.deleteNote(nid);
                                    finish();
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "Нет",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                    return true;
                }
            }

            return super.onOptionsItemSelected(item);
        }

}