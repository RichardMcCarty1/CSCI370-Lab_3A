package com.example.lab3a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private ArrayList<Student> students = new ArrayList<Student>();
    private class Student {
    private String fName;
    private String lName;
    private String major;
    public Student(String firstName, String lastName, String Major) {
        this.fName = firstName;
        this.lName = lastName;
        this.major = Major;
    }
    public String getFName() {
        return this.fName;
    }
    public String getLName() {
        return this.lName;
    }
    public String getMajor() {
        return this.major;
    }
    public void setFName(String input) {
        this.fName = input;
    }
    public void setLName(String input) {
        this.lName = input;
    }
    public void setMajor(String input) {
        this.major = input;
    }

    }
    private class StudentAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mInflator;
        private ArrayList<Student> mDataSource;
        public StudentAdapter(Context context, ArrayList studentlist) {
            mDataSource = studentlist;
            mContext = context;
            mInflator = (LayoutInflater) mContext.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        }
        @Override
        public int getCount() {
            return mDataSource.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataSource.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = mInflator.inflate(R.layout.list_view_row, parent, false);
            TextView lastName = rowView.findViewById(R.id.last_name);
            TextView firstName = rowView.findViewById(R.id.first_name);
            TextView major = rowView.findViewById(R.id.major);
            ImageView icon = rowView.findViewById(R.id.thumbnail);

            Student student = (Student) getItem(position);
            lastName.setText(student.getLName());
            firstName.setText(student.getFName());
            major.setText(student.getMajor());
            icon.setImageResource(R.drawable.ic_baseline_account_circle_24);
            return rowView;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.student);
        students.add(new Student("Bob", "NotBob", "CSCI"));
        students.add(new Student("Matilda", "NotMatilda", "DS"));
        students.add(new Student("Carol", "NotCarol", "CIS"));
        students.add(new Student("Who", "NotWho", "Marketing"));
        students.add(new Student("Kyle", "NotKyle", "SomeRandomMajor"));
        StudentAdapter adapter = new StudentAdapter(getApplicationContext(), students);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Short Click", Toast.LENGTH_SHORT).show();
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}