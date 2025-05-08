package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UserInputActivity extends AppCompatActivity {

    EditText etName, etContacts, etAge, etInterests, etSkills;
    Button btnSave;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);

        etName = findViewById(R.id.etName);
        etContacts = findViewById(R.id.etContacts);
        etAge = findViewById(R.id.etAge);
        etInterests = findViewById(R.id.etInterests);
        etSkills = findViewById(R.id.etSkills);
        btnSave = findViewById(R.id.btnSave);

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        // Загружаем данные в EditText
        loadData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", etName.getText().toString());
                editor.putString("contacts", etContacts.getText().toString());
                editor.putString("age", etAge.getText().toString());
                editor.putString("interests", etInterests.getText().toString());
                editor.putString("skills", etSkills.getText().toString());
                editor.apply();

                // Переход к MainActivity
                Intent intent = new Intent(UserInputActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadData() {
        String name = sharedPreferences.getString("name", "");
        String contacts = sharedPreferences.getString("contacts", "");
        String age = sharedPreferences.getString("age", "");
        String interests = sharedPreferences.getString("interests", "");
        String skills = sharedPreferences.getString("skills", "");

        etName.setText(name);
        etContacts.setText(contacts);
        etAge.setText(age);
        etInterests.setText(interests);
        etSkills.setText(skills);
    }
}
