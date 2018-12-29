package com.eslamshawky.hp.realdatabase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditActivity extends AppCompatActivity {
private EditText name,email,phone,age;
private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final String Name = getIntent().getStringExtra("name");
        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        age = findViewById(R.id.edit_age);
        phone = findViewById(R.id.edit_phone);
        save = findViewById(R.id.save_data);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        Query query = myRef.child("Students").orderByChild("name").equalTo(Name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Student student = snapshot.getValue(Student.class);
                    name.setText(student.getName());
                    email.setText(student.getEmail());
                    phone.setText(student.getPhone());
                    age.setText(student.getAge());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Student student = new Student();
            student.setName(name.getText().toString());
            student.setEmail(email.getText().toString());
            student.setPhone(phone.getText().toString());
            student.setAge(age.getText().toString());
            Query query1 = myRef.child("Students").orderByChild("name").equalTo(Name);
            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                        snapshot.getRef().setValue(student);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    });
    }
}
