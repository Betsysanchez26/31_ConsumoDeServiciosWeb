package com.example.betsysanchez.a31_consumodeserviciosweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button get;
    public static TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get=findViewById(R.id.btnGet);
        txt=findViewById(R.id.textView);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetJSON process = new GetJSON();
                process.execute();
            }
        });
    }
}
