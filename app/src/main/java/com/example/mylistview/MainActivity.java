package com.example.mylistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText item;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.myListView);
        ArrayList<String> li = new ArrayList<>();

        item = findViewById(R.id.Item);
        button = findViewById(R.id.button);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,li);
        listView.setAdapter(arrayAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = item.getText().toString();
                if (text.isEmpty() || text.equals(" ")){
                    Toast.makeText(MainActivity.this, "Enter Your Text ", Toast.LENGTH_SHORT).show();
                }
                else {
                    li.add(text.toLowerCase());
                    arrayAdapter.notifyDataSetChanged();
                    item.setText("");
                }
            }
        });


       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               new AlertDialog.Builder(MainActivity.this).setTitle("Delete Item").setMessage("Are you sure you want to delete this Item?")
           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
//                   Toast.makeText(MainActivity.this, ""+li.get(position), Toast.LENGTH_SHORT).show();
                   li.remove(li.get(position));
                   arrayAdapter.notifyDataSetChanged();

               }
           })
           .setNegativeButton("No", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
//                   Toast.makeText(MainActivity.this, ""+li.get(position), Toast.LENGTH_SHORT).show();
               }
           }).show();}
       });

    }
}