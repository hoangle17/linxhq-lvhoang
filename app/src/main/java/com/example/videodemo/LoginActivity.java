package com.example.videodemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButtonFb;
    EditText editTextUsername;
    EditText editTextPass;
    Button buttonSignIn;
    TextView textViewSignUp, textViewTitle, textViewTitleBlow;
    CallbackManager callbackManager;
    String user_lastname;
    String user_firstname;
    String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        init();
        setLoginButton();
    }

    private void setLoginButton() {
        loginButtonFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {

                                if (response.getError() != null) {
                                    // handle error
                                } else {

                                    user_lastname = me.optString("last_name");
                                    user_firstname = me.optString("first_name");
                                    user_email = response.getJSONObject().optString("email");

                                    //editTextusername.setText(user_email);

                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công dưới tên " + user_firstname + " " + user_lastname, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "last_name,first_name,email");
                request.setParameters(parameters);
                request.executeAsync();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        loginButtonFb = findViewById(R.id.login_button);
        editTextUsername = findViewById(R.id.UsernameLogin);
        editTextPass = findViewById(R.id.Password);
        textViewTitle = findViewById(R.id.Title);
        textViewTitleBlow = findViewById(R.id.SubTitle);
        buttonSignIn = findViewById(R.id.LoginButton);
        textViewSignUp = findViewById(R.id.SignInTextView);
    }
}