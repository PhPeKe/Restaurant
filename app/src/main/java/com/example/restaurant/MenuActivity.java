package com.example.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MenuActivity extends Activity implements MenuRequest.Callback {

    public String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        query = intent.getStringExtra("search");

        String search = "https://resto.mprog.nl/menu?category=" + query;

    }

    @Override
    public void gotCategories(ArrayList<MenuItem> items) {

    }

    @Override
    public void gotCategoriesError(String message) {

    }
}
