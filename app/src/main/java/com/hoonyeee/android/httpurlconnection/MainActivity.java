package com.hoonyeee.android.httpurlconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editUrl;
    Button btnGet;
    TextView tv;
    String url,result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUrl = findViewById(R.id.editUrl);
        tv = findViewById(R.id.tv);
        btnGet = findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = editUrl.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = Remote.getScreen(url);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(result);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
