package com.example.samplerecyclercontacts;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.ItemEventListener {
    private ContactsAdapter adapter;
    private int             editingItemPosition = -1;
    private EditText        fullNameEt;
    private ImageView       addIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new ContactsAdapter(this);
        recyclerView.setAdapter(adapter);


        fullNameEt = findViewById(R.id.et_main_input);
        addIm      = findViewById(R.id.iv_main_add);
        addIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameStr = fullNameEt.getText().toString().trim();
                if (fullNameStr.length() <= 0) {return;}
                if (editingItemPosition > -1) {
                    adapter.updateContact(fullNameStr, editingItemPosition);
                    editingItemPosition = -1;
                    addIm.setImageResource(R.drawable.ic_add_white_24dp);
                    fullNameEt.getText().clear();
                }
                else {
                    adapter.addContact(fullNameStr);
                    recyclerView.scrollToPosition(0);
                }
                hideKeyboard();
            }
        });
    }

    @Override
    public void onItemClick(String fullName, int position) {

        editingItemPosition = position;
        fullNameEt.setText(fullName);
        addIm.setImageResource(R.drawable.ic_done_white_24dp);

        openKeyboard(fullNameEt);

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void openKeyboard(EditText editText) {
        editText.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }
}
