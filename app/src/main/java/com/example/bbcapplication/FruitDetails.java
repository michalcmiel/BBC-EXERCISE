package com.example.bbcapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class FruitDetails extends AppCompatActivity {
    TextView textViewShowInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_details);
        textViewShowInfo = findViewById(R.id.textViewShowInfo);
        Intent IntentExtras = getIntent();
        Fruit fruit = (Fruit)IntentExtras.getSerializableExtra("fruit");

        textViewShowInfo.setText(fruit.getFruit_name() +
                                "\nPrice: " + getFullPrice(fruit.getFruit_price()) +
                                "\nWeight: " + gramsToKilos(fruit.getFruit_weight()) + "kg"
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fruit_details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_back) {
            Intent go_back = new Intent(FruitDetails.this, MainActivity.class);
            startActivity(go_back);
        }
        return super.onOptionsItemSelected(item);
    }

    public String getFullPrice(int pennies){
        String fruit_price = "£" + pennies/100 + "." + pennies%100;
        return fruit_price;
    }

    public double gramsToKilos(double grams){
        return grams/1000;
    }
}
