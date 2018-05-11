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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    public Context context;
    public Callback thisActivity = null;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest (Context passedContext) {
        context = passedContext;
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println("Works!");
        try {
            // Get JSON-Array from response
            JSONArray rawCategories = response.getJSONArray("categories");

            // Initialize empty list for strings in response
            ArrayList<String> categories = new ArrayList<>();

            // Loop through array to extract strings
            for (int i = 0; i < rawCategories.length(); i++) {
                categories.add(rawCategories.getString(i));
            }

            thisActivity.gotCategories(categories);

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

    public void getCategories(Callback activity) {
        System.out.println("Works!!!!!!!!!!!!!");
        thisActivity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories",null, this, this);
        queue.add(jsonObjectRequest);
    }

}
