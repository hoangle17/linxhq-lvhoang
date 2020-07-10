package com.example.videodemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButtonFb;
    EditText editTextUsername;
    EditText editTextPass;
    Button buttonSignIn;
    TextView textViewSignUp, textViewTitle, textViewTitleBlow;

    CallbackManager mCallbackManager;
    String user_lastname;
    String user_firstname;
    String user_email;
    private FirebaseAuth mAuth;

    public ArrayList<User> userArrayList = new ArrayList<>();
    int move = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCallbackManager = CallbackManager.Factory.create();

        init();
        setLoginButton();
        clickSignIn();

        FacebookSdk.sdkInitialize(LoginActivity.this);
//        mAuth = FirebaseAuth.getInstance();
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        loginButtonFb = findViewById(R.id.login_button);
        loginButtonFb.setReadPermissions("email", "public_profile");
        loginButtonFb.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickSignIn() {
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService = APIService.getService();
                Call<List<User>> callback = dataService.GetAllUser();
                callback.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        userArrayList = (ArrayList<User>) response.body();
                        for (int i = 0; i < userArrayList.size(); i++) {
                            if (userArrayList.get(i).getUsername().equals(editTextUsername.getText().toString()) &&
                                    userArrayList.get(i).getPassword().equals(editTextPass.getText().toString())) {
                                move++;
                            }
                        }
                        if (move == 1) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                            startActivity(intent);
                            move = 0;
                        } else {
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                            //editTextpassword.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please sign in continue", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLoginButton() {
//        loginButtonFb.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(JSONObject me, GraphResponse response) {
//
//                                if (response.getError() != null) {
//                                    // handle error
//                                } else {
//
//                                    user_lastname = me.optString("last_name");
//                                    user_firstname = me.optString("first_name");
//                                    user_email = response.getJSONObject().optString("email");
//
//                                    //editTextusername.setText(user_email);
//
//                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công dưới tên " + user_firstname + " " + user_lastname, Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "last_name,first_name,email");
//                request.setParameters(parameters);
//                request.executeAsync();
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//    }

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