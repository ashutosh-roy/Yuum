package com.example.bookio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Display extends AppCompatActivity {
    ImageView image;
    TextView hotelname,price;
    String hotel_name;
    TextView user;
    Button book,signout,cart;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        final Intent intent = getIntent();
        hotel_name = intent.getStringExtra("Hotel name");
        username = intent.getStringExtra("User");
        price = findViewById(R.id.textView5);
        image=findViewById(R.id.imageView4);
        hotelname = findViewById(R.id.textView4);
        user = findViewById(R.id.textView7);
        book = (Button)findViewById(R.id.booking);
        cart = findViewById(R.id.cart);
        signout = findViewById(R.id.button2);
        price.setText(R.string.Rs);
        user.append(username);
        ImgDisplay();
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Display.this,Details.class);
                intent1.putExtra("User",username);
                intent1.putExtra("Hotel name",hotelname.getText().toString());
                intent1.putExtra("Price",price.getText().toString());
                startActivity(intent1);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Display.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }public void book()
    {

    }

    public void ImgDisplay(){
        if(hotel_name.equals("Burger")) {
            image.setImageResource(R.mipmap.burger);
            hotelname.setText(hotel_name);
            price.append("150");
        }
        else if(hotel_name.equals("Omlette")){
            image.setImageResource(R.drawable.egg);
            price.append("100");
        }
        else if(hotel_name.equals("Palak Panner"))
        {
            image.setImageResource(R.drawable.palak_paneer);
            price.append("200");
        }
        else if(hotel_name.equals("Panner Tikka")){
            image.setImageResource(R.drawable.paneer);
            price.append("250");
        }

        hotelname.setText(hotel_name);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.SignOut:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.Close:
                this.finish();
                System.exit(0);
            case R.id.View:
                intent = new Intent(this, Booking.class);
                intent.putExtra("User",username);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
