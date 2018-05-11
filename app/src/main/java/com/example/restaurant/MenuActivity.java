package com.example.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class MenuActivity extends Activity implements MenuRequest.Callback, AdapterView.OnItemClickListener {

    public String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        query = intent.getStringExtra("search");

        String search = "https://resto.mprog.nl/menu?category=" + query;
        MenuRequest request = new MenuRequest(this);
        request.getMenuItems(this, search);

    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> items) {

    }

    @Override
    public void gotCategoriesError(String message) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MenuItem clickedItem = (MenuItem) parent.getItemAtPosition(position);
        Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
        intent.putExtra("clickedItem",clickedItem);
        startActivity(intent);
    }
}
