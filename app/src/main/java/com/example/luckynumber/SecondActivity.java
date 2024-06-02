package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    EditText et ;
    Button bt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et = findViewById(R.id.out);
        bt = findViewById(R.id.btn1);

        Intent i = getIntent();
        int max = i.getIntExtra("range",0);

        String result = String.valueOf(getRandom(max));
        et.setText(result);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(result);
            }
        });
    }

    public int getRandom(int max){
        Random random = new Random();
        // The +1 ensures the range is inclusive of max
        return random.nextInt(max);
    }

    public void shareData(String result){
        //Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT,"I got Lucky Today");
        i.putExtra(Intent.EXTRA_TEXT,"My lucky Number is : "+ result);

        startActivity(Intent.createChooser(i,"Choose a Platform"));
    }
}