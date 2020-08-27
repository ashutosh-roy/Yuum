package com.example.bookio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText user, password;
    ImageView showpw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button);
        showpw = findViewById(R.id.imageView5);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arr[] = {"Admin","Srishti","Sejal"};
                if(user.getText().toString().equals("") || password.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter the details",Toast.LENGTH_LONG).show();
                else if(password.getText().toString().equals("abc123")) {
                    int flag = 0;
                    for(int i=0;i< arr.length ; i++){
                        if(user.getText().toString().equals(arr[i])){
                            flag = 1;
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Booking.class);
                            intent.putExtra("User",user.getText().toString());
                            startActivity(intent);

                        }
                    }
                    if(flag==0)
                        Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
            }
        });
        showpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    showpw.setImageResource(R.drawable.show);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.getText().length());
                }
                else{
                    showpw.setImageResource(R.drawable.hide);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.getText().length());
                }

            }
        });

    }
}
