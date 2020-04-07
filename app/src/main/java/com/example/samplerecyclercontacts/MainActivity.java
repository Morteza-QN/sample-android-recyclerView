package com.example.samplerecyclercontacts;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new ContactsAdapter();
        recyclerView.setAdapter(adapter);


        final EditText fullNameEt = findViewById(R.id.et_main_input);
        ImageView      addIm      = findViewById(R.id.iv_main_add);
        addIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameStr = fullNameEt.getText().toString().trim();
                if (fullNameStr.length() <= 0) {return;}
                adapter.addContact(fullNameStr);
                recyclerView.scrollToPosition(0);

            }
        });
    }
}
