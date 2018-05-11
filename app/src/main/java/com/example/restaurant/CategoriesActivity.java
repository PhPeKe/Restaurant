package com.example.restaurant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends Activity implements CategoriesRequest.Callback{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 0, categories);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
