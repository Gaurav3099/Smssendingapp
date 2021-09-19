package com.gaurav.smssendingapp;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etPhone, etMessage;
    Button btnSendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etMessage = (EditText) findViewById(R.id.etMessage);
        btnSendSms = (Button) findViewById(R.id.btnSendSms);

        int res = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (res == PackageManager.PERMISSION_GRANTED)
        {
          btnSendSms.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String phone = etPhone.getText().toString();
                  String message = etMessage.getText().toString();
                  if (phone.length() != 10){
                      etPhone.setError("invalid");
                      etPhone.requestFocus();
                      return;}
                  if (message.length() == 0){
                      etMessage.setError("empty");
                      etMessage.requestFocus();return;
                  }
                  SmsManager sm = SmsManager.getDefault();
                  sm.sendTextMessage(phone, null, message, null, null);
              }
          });

        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1234);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1234 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            btnSendSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = etPhone.getText().toString();
                    String message = etMessage.getText().toString();
                    if (phone.length() != 10){
                        etPhone.setError("invalid");
                        etPhone.requestFocus();
                        return;}
                    if (message.length() == 0){
                        etMessage.setError("empty");
                        etMessage.requestFocus();return;
                    }
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(phone, null, message, null, null);
                }
            });

        }
    }
}
