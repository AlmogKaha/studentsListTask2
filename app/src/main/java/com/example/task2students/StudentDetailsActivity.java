package com.example.task2students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.task2students.model.Model;
import com.example.task2students.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {
    Student student;
    Model data = Model.instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        setDetails();
        int position = getIntent().getIntExtra("position", 0);

        Button editBtn = findViewById(R.id.studentDetail_edit_button);

        editBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditStudentActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        if(student.getId() == null){
            data.deleteStudent(student);
            finish();
        } else {
         setDetails();
        }
    }

    private void setDetails(){
        int position = getIntent().getIntExtra("position", 0);
        student = data.getAllStudents().get(position);
        TextView nameText = findViewById(R.id.studentDetails_nameText);
        TextView idText = findViewById(R.id.studentDetails_idText);
        TextView phoneText = findViewById(R.id.studentDetails_phoneText);
        TextView addressText = findViewById(R.id.studentDetails_addressText);
        CheckBox checkBox = findViewById(R.id.studentDetails_checkBox);

        nameText.setText(student.getName());
        idText.setText(student.getId());
        phoneText.setText(student.getPhone());
        addressText.setText(student.getAddress());
        checkBox.setChecked(student.isChecked());
    }
}