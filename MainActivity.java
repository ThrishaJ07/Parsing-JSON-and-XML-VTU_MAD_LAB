package com.example.parsingjsonandxml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button parseXmlBtn,parseJsonBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseXmlBtn=(Button) findViewById(R.id.main_parse_xml_data);
        parseXmlBtn.setOnClickListener(this);
        parseJsonBtn=(Button)findViewById(R.id.main_parse_json_data);
        parseJsonBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(parseXmlBtn))
        {
            //for connection
            Intent intent=new Intent(this,ViewDataActivity.class);
            intent.putExtra("datatype","xml");
            startActivity(intent);
        }
        else if(view.equals(parseJsonBtn))
        {
            Intent intent=new Intent(this,ViewDataActivity.class);
            intent.putExtra("datatype","json");
            startActivity(intent);

        }
    }
}