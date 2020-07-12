package com.example.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videodemo.API.APIService;
import com.example.videodemo.API.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateLoginActivity extends AppCompatActivity {
    EditText editTextCreateUserName, editTextCreatePass, editTextEmail, editTextPhone, editTextTwitter, editTextFb;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);
        init();
        onClickSignUp();
    }

    private void onClickSignUp() {
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(editTextCreateUserName.getText());
                String password = String.valueOf(editTextCreatePass.getText());
                DataService dataService = APIService.getService();
                Call<String> callback = dataService.InsertUser(username, password);

                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        if(result.equals("available")){
                            Toast.makeText(CreateLoginActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(CreateLoginActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(CreateLoginActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void init() {
        editTextCreateUserName = findViewById(R.id.txtusername);
        editTextCreatePass = (EditText) findViewById(R.id.txtpassword);
        editTextEmail = findViewById(R.id.txtemail);
        editTextPhone = findViewById(R.id.txtphone);
        editTextTwitter = findViewById(R.id.txttwitter);
        editTextFb = findViewById(R.id.txtfb);
        buttonSignUp = findViewById(R.id.btnSignup);
    }
}