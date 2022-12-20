package com.example.task2students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.task2students.model.Model;
import com.example.task2students.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Student> data;
    RecyclerView list;
    StudentRecyclerAdapter studentRecyclerAdapter = new StudentRecyclerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addStudentButton = findViewById(R.id.main_add_student_button);

        addStudentButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });

        data = Model.instance().getAllStudents();

        list = findViewById(R.id.main_students_recycler_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(studentRecyclerAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume(){
        super.onResume();
        studentRecyclerAdapter.notifyDataSetChanged();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        TextView idTextView;
        CheckBox checkBox;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.studentRow_name);
            idTextView = itemView.findViewById(R.id.studentRow_id);
            checkBox = itemView.findViewById(R.id.studentRow_checkBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int)checkBox.getTag();
                    Student student = data.get(position);
                    student.setChecked(checkBox.isChecked());
                }
            });
        }

        public void bind(Student student, int position){
            nameTextView.setText(student.getName());
            idTextView.setText(student.getId());
            checkBox.setChecked(student.isChecked());
            checkBox.setTag(position);
        }
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent,false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = list.getChildLayoutPosition(view);
                    Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                    intent.putExtra("position", itemPosition);
                    startActivity(intent);
                }
            });
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student student = data.get(position);
            holder.bind(student, position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}