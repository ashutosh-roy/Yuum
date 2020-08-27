package com.example.bookio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class Booking extends AppCompatActivity {
    String user;
    TextView textView;
    ImageView hotel1,hotel2,hotel3,hotel4;
    TextView name1,name2,name3,name4;
    CardView n1, n2;
    Switch s;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        final Intent intent = getIntent();
        user = intent.getStringExtra("User");
        n1 = findViewById(R.id.nonveg1);
        n2 = findViewById(R.id.nonveg2);
        s =  findViewById(R.id.switch1);
        hotel1 = findViewById(R.id.image);
        hotel2 = findViewById(R.id.image2);
        hotel3 = findViewById(R.id.image3);
        hotel4 = findViewById(R.id.image4);

        name1 = findViewById(R.id.hotel_name1);
        name2 = findViewById(R.id.hotel_name2);
        name3 = findViewById(R.id.hotel_name3);
        name4 = findViewById(R.id.hotel_name4);



        String text = "Hey "+user;
        textView = findViewById(R.id.textView3);
        textView.setText(text);

    }
    public void onClick(View view){
        Intent intent = new Intent(this,Display.class);
        if(view.getId()==R.id.image)
            intent.putExtra("Hotel name", name1.getText().toString());
        else if(view.getId()==R.id.image2)
            intent.putExtra("Hotel name", name2.getText().toString());
        else if(view.getId()==R.id.image3)
            intent.putExtra("Hotel name", name3.getText().toString());
        else if(view.getId()==R.id.image4)
            intent.putExtra("Hotel name", name4.getText().toString());

        intent.putExtra("User",user);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_booking,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SignOut:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.Close:
                this.finish();
                System.exit(0);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void vegonly(View view)
    {
        if(s.isChecked()==true) {
            n1.setVisibility(View.GONE);
            n2.setVisibility(View.GONE);
        }
        else
        {
            n1.setVisibility(View.VISIBLE);
            n2.setVisibility(View.VISIBLE);
        }
    }

}
