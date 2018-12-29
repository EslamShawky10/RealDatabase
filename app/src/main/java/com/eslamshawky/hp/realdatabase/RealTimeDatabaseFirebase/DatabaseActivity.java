package com.eslamshawky.hp.realdatabase.RealTimeDatabaseFirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eslamshawky.hp.realdatabase.R;

public class DatabaseActivity extends AppCompatActivity implements Database_Interface.DatabaseView {

    private RecyclerView recyclerView;
    private EditText editText;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        recyclerView = findViewById(R.id.recyclerView_chat);
        editText = findViewById(R.id.edit_chat);
        send = findViewById(R.id.send_chat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DatabaseActivity.this));

        final Database_Presenter database_presenter = new Database_Presenter(DatabaseActivity.this, this);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatModel model = new ChatModel(editText.getText().toString());
                database_presenter.writeData(model);
            }
        });
        ChatModel chatModel = new ChatModel(editText.getText().toString());
        database_presenter.readData(recyclerView);

    }

    @Override
    public void successful(String message) {
        Toast.makeText(this, "Successful...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(String message) {
        Toast.makeText(this, "Fialed...", Toast.LENGTH_SHORT).show();
    }
}
