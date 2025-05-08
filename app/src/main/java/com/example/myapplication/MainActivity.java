package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView personName, personContacts, personAge, personInterests, personSkills;
    Button btnEdit, btnShare;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personName = findViewById(R.id.personName);
        personContacts = findViewById(R.id.personContacts);
        personAge = findViewById(R.id.personAge);
        personInterests = findViewById(R.id.personInterests);
        personSkills = findViewById(R.id.personSkills);
        btnEdit = findViewById(R.id.btnEdit);
        btnShare = findViewById(R.id.btnShare);

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        loadData();

        personContacts.setOnClickListener(v -> {
            String phoneNumber = sharedPreferences.getString("contacts", "").trim();
            if (!phoneNumber.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Номер телефона не указан", Toast.LENGTH_SHORT).show();
            }
        });

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserInputActivity.class);
            startActivity(intent);
        });

        btnShare.setOnClickListener(v -> shareProfile());
    }

    public void loadData() {
        String name = sharedPreferences.getString("name", "Не задано");
        String contacts = sharedPreferences.getString("contacts", "Не задано");
        String age = sharedPreferences.getString("age", "Не задано");
        String interests = sharedPreferences.getString("interests", "Не заданы");
        String skills = sharedPreferences.getString("skills", "Не заданы");

        personName.setText("ФИО: " + name);
        personContacts.setText("Контакты: " + contacts);
        personAge.setText("Возраст: " + age);
        personInterests.setText("Интересы: " + interests);
        personSkills.setText("Навыки: " + skills);
    }

    private void shareProfile() {
        String name = sharedPreferences.getString("name", "Не задано");
        String contacts = sharedPreferences.getString("contacts", "Не задано");
        String age = sharedPreferences.getString("age", "Не задано");
        String interests = sharedPreferences.getString("interests", "Не заданы");
        String skills = sharedPreferences.getString("skills", "Не заданы");

        String shareText = "Контактная информация:\n" +
                "ФИО: " + name + "\n" +
                "Контакты: " + contacts + "\n" +
                "Возраст: " + age + "\n" +
                "Интересы: " + interests + "\n" +
                "Навыки: " + skills;

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Поделиться профилем"));
    }
}