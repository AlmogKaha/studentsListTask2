package com.example.task2students;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.task2students.model.Model;
import com.example.task2students.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText nameEt = findViewById(R.id.addStudent_name_editText);
        EditText idEt = findViewById(R.id.addStudent_id_editText);
        EditText phoneEt = findViewById(R.id.addStudent_phone_editText);
        EditText addressEt = findViewById(R.id.addStudent_address_editText);
        CheckBox checkBox = findViewById(R.id.addStudent_checkBox);
        Button saveBtn = findViewById(R.id.addStudent_save_button);
        Button cancelBtn = findViewById(R.id.addStudent_cancel_btn);

        saveBtn.setOnClickListener(view -> {
            Model.instance().addStudent(new Student(nameEt.getText().toString(), idEt.getText().toString(),
                    phoneEt.getText().toString(), addressEt.getText().toString(), checkBox.isChecked()));

            setResult(200, getIntent());
            finish();
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}