package com.example.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView textFruit;
    private Button buttonAddFruit;
    private Button buttonDelete;

    AlertDialog.Builder alertDialog;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textFruit = findViewById(R.id.textFruit);
        buttonAddFruit = findViewById(R.id.buttonAddFruit);
        buttonDelete = findViewById(R.id.buttonDelete);

        alertDialog = new AlertDialog.Builder(this);

        database = new Database(this);

        showFruits();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == buttonAddFruit.getId()) {
            Intent intent = new Intent(MainActivity.this, AddNewFruitActivity.class);
            startActivity(intent);
        }

        if (v.getId() == buttonDelete.getId()) {
            removeFruit();
        }

        showFruits();
    }

    private void removeFruit() {
        alertDialog.setMessage("Czy na pewno chcesz usunąć dane?")
                .setCancelable(false)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        database.deleteAllFruits();
                        Toast.makeText(getApplicationContext(),"Dane zostały usunięte",
                                Toast.LENGTH_SHORT).show();
                        showFruits();
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"Dane nie zostały usunięte",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alert = alertDialog.create();

        alert.setTitle("Baza Danych");
        alert.show();
    }

    private void showFruits() {
        StringBuilder builder = new StringBuilder();
        Cursor cursor = database.getAllFruits();

        while(cursor.moveToNext()) {
            builder.append("\n ID: "+cursor.getInt(0));
            builder.append("\n Nazwa owocu: "+cursor.getString(1));
            builder.append("\n Kolor: "+ cursor.getString(2));
            builder.append("\n Smak: "+cursor.getString(3));
            builder.append("\n Wielkość: "+ cursor.getString(4));
        }
        textFruit.setText(builder.toString());
    }

}