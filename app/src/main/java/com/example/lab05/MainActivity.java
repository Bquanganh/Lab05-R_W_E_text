package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button buttonRead, buttonWrite;
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRead= findViewById(R.id.buttonRead);
        buttonWrite = findViewById(R.id.buttonWrite);
        editText = findViewById(R.id.editText);

        buttonRead.setOnClickListener(this);
        buttonWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.buttonRead){
            readData();
        }else if (v.getId()==R.id.buttonWrite){
            writeData();
        }

    }



    private void readData() {
        try {
            FileInputStream in = openFileInput("NewTextFile.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data = "";
            StringBuilder builder = new StringBuilder();
            while ((data = reader.readLine())!=null){
                builder.append(data);
                builder.append("\n");
            }
            in.close();
            editText.setText(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeData() {
        try {
            FileOutputStream out = openFileOutput("NewTextFile.txt",0);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(editText.getText().toString());
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}