package com.eslamshawky.hp.realdatabase.RealTimeDatabaseFirebase;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Database_Presenter implements Database_Interface.DatabasePresenter {
    Activity activity;
    Database_Interface.DatabaseView view ;
    private RecyclerChat recyclerChat;
    private ArrayList<ChatModel> chatModels=new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message").child("Data");

    public Database_Presenter(Activity activity, Database_Interface.DatabaseView view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void writeData(ChatModel chatModel) {
        myRef.setValue(chatModel.getTextChat());
    }

    @Override
    public void readData( final RecyclerView recyclerView) {
// Read from the database
recyclerChat=new RecyclerChat(activity);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                ChatModel chatModel =new ChatModel(value);
                chatModel.setTextChat(value);
                chatModels.add(chatModel);
                recyclerChat.setChatModels(chatModels);
                recyclerView.setAdapter(recyclerChat);

                recyclerChat.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public Void updateData(ChatModel chatModel) {
        return null;
    }

    @Override
    public void deleteData(ChatModel chatModel) {

    }
}
