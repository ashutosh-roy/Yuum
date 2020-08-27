package com.example.bookio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Confirm extends AppCompatActivity {
    String name,hotel_name,from,to,number,price,user;
    TextView namet,hotelt,fromt,tot,numbert,pricet,log;
    Button confirm,signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        hotel_name = intent.getStringExtra("Hotel Name");
        from = intent.getStringExtra("From");
        to = intent.getStringExtra("To");
        number = intent.getStringExtra("Number");
        price = intent.getStringExtra("Price");
        namet = findViewById(R.id.textView18);
        hotelt = findViewById(R.id.textView19);
        fromt= findViewById(R.id.textView20);
        tot = findViewById(R.id.textView21);
        numbert = findViewById(R.id.textView23);
        pricet = findViewById(R.id.textView22);
        log = findViewById(R.id.textView24);

        user = intent.getStringExtra("User");
        namet.append(name);
        hotelt.append(hotel_name);
        fromt.append(from);
        tot.append(to);
        numbert.append(number);
        pricet.append(price);
        log.append(user);
        confirm = findViewById(R.id.button5);
        signout = findViewById(R.id.button4);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Confirm.this);
                dialog.setMessage("Do you want to confirm booking?");
                dialog.setTitle("Booking Confirmation");
                dialog.setPositiveButton("Book",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(getApplicationContext(),"Booked successfully",Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(Confirm.this,Booking.class);
                                intent1.putExtra("User",user);
                                startActivity(intent1);

                            }
                        });
                dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Booking cancelled",Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(Confirm.this,Booking.class);
                        intent1.putExtra("User",user);
                        startActivity(intent1);
                    }
                });
                AlertDialog alerDialog=dialog.create();
                alerDialog.show();
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Confirm.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm,menu);
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
            case R.id.Confirm:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Confirm.this);
                dialog.setMessage("Do you want to confirm booking?");
                dialog.setTitle("Booking Confirmation");
                dialog.setPositiveButton("Book",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(getApplicationContext(),"Booked successfully",Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(Confirm.this,Booking.class);
                                intent1.putExtra("User",user);
                                startActivity(intent1);

                            }
                        });
                dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Booking cancelled",Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(Confirm.this,Booking.class);
                        intent1.putExtra("User",user);
                        startActivity(intent1);
                    }
                });
                AlertDialog alerDialog=dialog.create();
                alerDialog.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
