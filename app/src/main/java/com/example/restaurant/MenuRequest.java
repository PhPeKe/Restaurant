package com.example.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    public Context context;
    public Callback thisActivity = null;

    public interface Callback {
        void gotCategories(ArrayList<MenuItem> items);
        void gotCategoriesError(String message);
    }

    public MenuRequest (Context passedContext) {
        context = passedContext;
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println("Works!2");
        try {
            // Get JSON-Array from response
            JSONArray rawItems = response.getJSONArray("items");

            // Initialize empty list for strings in response
            ArrayList<MenuItem> items = new ArrayList<>();

            // Loop through array to extract strings
            for (int i = 0; i < rawItems.length(); i++) {
                // Initialize item
                MenuItem item = null;

                // Retrieve JSON Object
                JSONObject oneItem = rawItems.getJSONObject(i);

                // Set properties of menuItem
                item.setCategory(oneItem.getString("category"));
                item.setDescription(oneItem.getString("description"));
                item.setImageUrl(oneItem.getString("image_url"));
                item.setName(oneItem.getString("name"));
                item.setPrice(oneItem.getInt("price"));

                // Add item to list of items to give back to main activity
                items.add(item);
            }

            thisActivity.gotCategories(items);

        } catch (JSONException e) {
            // Print error message if something goes wrong while retrieving the request
            e.printStackTrace();
            System.out.println("Error while getting categories");
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        thisActivity.gotCategoriesError(error.getMessage());
    }

    public void getCategories(Callback activity, String search) {
        System.out.println("Works!!!!!!!!!!!!!2");
        thisActivity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(search,null, this, this);
        queue.add(jsonObjectRequest);
    }

}
