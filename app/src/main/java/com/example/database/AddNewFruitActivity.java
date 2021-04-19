package com.example.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewFruitActivity extends Activity implements View.OnClickListener {
    private EditText editTextName;
    private EditText editTextColor;
    private EditText editTextTaste;
    private EditText editTextSize;
    private Button buttonSave;
    private Button buttonCancel;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_fruit);

        editTextName = findViewById(R.id.editTextName);
        editTextColor = findViewById(R.id.editTextColor);
        editTextTaste = findViewById(R.id.editTextTaste);
        editTextSize = findViewById(R.id.editTextSize);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        database = new Database(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonSave.getId()) {
            database.addFruit(editTextName.getText().toString(), editTextColor.getText().toString(), editTextTaste.getText().toString(), editTextSize.getText().toString());
            Intent intent = new Intent(AddNewFruitActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == buttonCancel.getId()) {
            finish();
        }
    }
}
