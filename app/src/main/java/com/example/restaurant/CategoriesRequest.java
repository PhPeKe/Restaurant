package com.example.restaurant;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest extends JsonObjectRequest {
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    Context context;

    public CategoriesRequest(Context context) {

    }

    public CategoriesRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public void onResponse(JSONObject response) {

    }

    public void onErrorResponse(VolleyError error) {

    }

    public void getCategories() {

    }

}
