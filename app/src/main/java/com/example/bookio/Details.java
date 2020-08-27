package com.example.bookio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ShareCompat;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Details extends AppCompatActivity {

    TextView datefrom,dateto;
    EditText name,email,phone;
    private int mYear,mMonth,mDay;
    Button checkout,signout;
    Spinner spin;
    String user,hotel_name,price;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ClipboardManager clipboard;
    String pasteData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        checkout = findViewById(R.id.button3);
        signout = findViewById(R.id.button4);
        final Intent intent = getIntent();
        user = intent.getStringExtra("User");
        hotel_name = intent.getStringExtra("Hotel name");
        price = intent.getStringExtra("Price");
//        registerForContextMenu(name);
//        registerForContextMenu(email);
//        registerForContextMenu(phone);
        List<String> options = new ArrayList<String>();
        options.add("1 Adult");
        options.add("1A,1C");
        options.add("2 Adults");
        options.add("2A,1C");
        clipboard= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,options);
        spin.setAdapter(arrayAdapter);
        datefrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Details.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                datefrom.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis() - 1000);
                datePickerDialog.show();

            }
        });
        dateto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Details.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dateto.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis() - 1000);

                datePickerDialog.show();

            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()||phone.getText().toString().isEmpty()||
                    email.getText().toString().isEmpty()||datefrom.getText().toString().equals("Select Date")
                    || dateto.getText().toString().equals("Select Date"))
                    Toast.makeText(Details.this,"All fields are mandatory!",Toast.LENGTH_SHORT).show();
                /*else if(!name.getText().toString().matches("[a-zA-Z]"))
                    Toast.makeText(Details.this,"Invalid name",Toast.LENGTH_SHORT).show();*/
                else if(phone.getText().toString().length()!=10)
                    Toast.makeText(Details.this,"Invalid phone number",Toast.LENGTH_SHORT).show();
                else if(!email.getText().toString().trim().matches(emailPattern))
                    Toast.makeText(Details.this,"Invalid email address",Toast.LENGTH_SHORT).show();
                else{
                    Intent intent1 = new Intent(Details.this,Confirm.class);
                    intent1.putExtra("Name",name.getText().toString());
                    intent1.putExtra("Hotel Name",hotel_name);
                    intent1.putExtra("From",datefrom.getText().toString());
                    intent1.putExtra("To",dateto.getText().toString());
                    intent1.putExtra("Number",spin.getSelectedItem().toString());
                    intent1.putExtra("Price",price);
                    intent1.putExtra("User", user);
                    startActivity(intent1);


                }
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Details.this,MainActivity.class);

                startActivity(intent1);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.SignOut:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.Details:
                intent = new Intent(this, Display.class);
                intent.putExtra("Hotel Name",hotel_name);
                intent.putExtra("User",user);
                startActivity(intent);
                return true;

            case R.id.Color:
                ConstraintLayout layout = findViewById(R.id.lay);
                ColorDrawable viewColor = (ColorDrawable) layout.getBackground();
                int currentBackgroundColor = viewColor.getColor();

                ColorPickerDialogBuilder
                        .with(this)
                        .setTitle("Choose color")
                        .initialColor(currentBackgroundColor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                 Toast.makeText(Details.this,"onColorSelected: 0x" + Integer.toHexString(selectedColor),Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                changeBackgroundColor(selectedColor);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
                return true;
            case R.id.CheckOut:
                if(name.getText().toString().isEmpty()||phone.getText().toString().isEmpty()||
                        email.getText().toString().isEmpty()||datefrom.getText().toString().equals("Select Date")
                        || dateto.getText().toString().equals("Select Date"))
                    Toast.makeText(Details.this,"All fields are mandatory!",Toast.LENGTH_SHORT).show();
                /*else if(!name.getText().toString().matches("[a-zA-Z]"))
                    Toast.makeText(Details.this,"Invalid name",Toast.LENGTH_SHORT).show();*/
                else if(phone.getText().toString().length()!=10)
                    Toast.makeText(Details.this,"Invalid phone number",Toast.LENGTH_SHORT).show();
                else if(!email.getText().toString().trim().matches(emailPattern))
                    Toast.makeText(Details.this,"Invalid email address",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent1 = new Intent(Details.this, Confirm.class);
                    intent1.putExtra("Name", name.getText().toString());
                    intent1.putExtra("Hotel Name", hotel_name);
                    intent1.putExtra("From", datefrom.getText().toString());
                    intent1.putExtra("To", dateto.getText().toString());
                    intent1.putExtra("Number", spin.getSelectedItem().toString());
                    intent1.putExtra("Price", price);
                    intent1.putExtra("User", user);
                    startActivity(intent1);
                }
                return true;
            case R.id.Close:
                this.finish();
                System.exit(0);
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void changeBackgroundColor(int selectedColor) {
        ConstraintLayout layout = findViewById(R.id.lay);
        layout.setBackgroundColor(selectedColor);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.copy: Toast.makeText(this,"Copy",Toast.LENGTH_SHORT).show();
                TextView textView = (TextView) name;
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", textView.getText());
                manager.setPrimaryClip(clipData);
                return true;

            case R.id.lookup: //Toast.makeText(this,"Lookup",Toast.LENGTH_SHORT).show();


                String url = name.getText().toString();
                if(!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;

            case R.id.paste:
                ClipData.Item item1 = clipboard.getPrimaryClip().getItemAt(0);


                pasteData = item1.getText().toString();


                if (pasteData != null) {
                    name.append(pasteData);
                    return true;
                }
                else {

                        // Something is wrong. The MIME type was plain text, but the clipboard does not contain either
                        // text or a Uri. Report an error.
                        Log.e("Bookio", "Clipboard contains an invalid data type");
                        return false;
                }


            case R.id.share: Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                String txt = name.getText().toString();
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType(mimeType)
                        .setChooserTitle("Whom do you want to share text with?")
                        .setText(txt)
                        .startChooser();
                return true;

            default: return super.onContextItemSelected(item);
        }

    }
}
