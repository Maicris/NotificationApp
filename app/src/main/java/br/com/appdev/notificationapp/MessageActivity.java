package br.com.appdev.notificationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        txtMessage = (TextView) findViewById(R.id.txtMessage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String msg = getIntent().getStringExtra("msg");
        txtMessage.setText(msg);
    }
}
