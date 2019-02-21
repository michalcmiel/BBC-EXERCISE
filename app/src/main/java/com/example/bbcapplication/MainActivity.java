package com.example.bbcapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView RecyclerViewFruits;
    ArrayList<Fruit> fruitList = new ArrayList<Fruit>();
    InfoGetRequests infoGetRequests = new InfoGetRequests(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final long startTime = System.nanoTime();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerViewFruits = findViewById(R.id.fruit_list);
        RecyclerViewFruits.setVisibility(View.GONE);
        getFruitList();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        infoGetRequests.sendStatsGetRequest("display", Long.toString(duration));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reload) {
            fruitList.clear();
            getFruitList();
        }
        return super.onOptionsItemSelected(item);
    }

    public void fillRecycler(){
        FruitsArrayAdapter adapter = new FruitsArrayAdapter(R.layout.list_fruit, fruitList, MainActivity.this);
        RecyclerViewFruits.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        RecyclerViewFruits.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewFruits.setAdapter(adapter);
        RecyclerViewFruits.setVisibility(View.VISIBLE);
    }

    public void getFruitList(){
        final long startTime = System.nanoTime();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        //send load request
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
                        infoGetRequests.sendStatsGetRequest("load", Long.toString(duration));
                        ////////
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("fruit");
                        fruitList.clear();
                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonFruit = jsonArray.getJSONObject(i);
                            fruitList.add(new Fruit(jsonFruit.optString("type"), jsonFruit.getInt("price"), jsonFruit.getInt("weight")));
                        }
                        fillRecycler();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "errr1", Toast.LENGTH_SHORT).show();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "JSON ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}
