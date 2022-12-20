package com.example.task2students;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.task2students.model.Model;
import com.example.task2students.model.Student;

public class EditStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Model data = Model.instance();
        int position = getIntent().getIntExtra("position", 0);
        Student student = data.getAllStudents().get(position);
        EditText nameEt = findViewById(R.id.editStudent_name_editText);
        EditText idEt = findViewById(R.id.editStudent_id_editText);
        EditText phoneEt = findViewById(R.id.editStudent_phone_editText);
        EditText addressEt = findViewById(R.id.editStudent_address_editText);
        CheckBox checkBox = findViewById(R.id.editStudent_checkBox);
        Button deleteBtn = findViewById(R.id.EditStudent_delete_button);
        Button saveBtn = findViewById(R.id.EditStudent_save_button);
        Button cancelBtn = findViewById(R.id.EditStudent_cancel_button);

        nameEt.setText(student.getName());
        idEt.setText(student.getId());
        phoneEt.setText(student.getPhone());
        addressEt.setText(student.getAddress());
        checkBox.setChecked(student.isChecked());

        cancelBtn.setOnClickListener(view -> finish());

        deleteBtn.setOnClickListener(view -> {
            student.setId(null);
            finish();
        });

        saveBtn.setOnClickListener(view -> {
            Student newStudent = new Student(nameEt.getText().toString(), idEt.getText().toString(),
                    phoneEt.getText().toString(), addressEt.getText().toString(), checkBox.isChecked());
            data.updateStudent(position, newStudent);
            finish();
        });
    }
}