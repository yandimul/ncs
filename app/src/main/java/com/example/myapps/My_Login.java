package com.example.myapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapps.session.Config;
import com.example.myapps.util.Server;

import java.util.HashMap;
import java.util.Map;

public class My_Login extends AppCompatActivity {
    String sUsername, sPassword;
    EditText edtUsername, edtPassword;
    Button btnLogin, btnRegister;

    private boolean loggedIn = false;


    private void bersih() {
        edtUsername.setText("");
        edtPassword.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__login);

        SharedPreferences sharedPreferences = My_Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sUsername = sharedPreferences.getString(Config.USERNAME_SHARED_PREF, "");

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sUsername = edtUsername.getText().toString().trim();
                sPassword = edtPassword.getText().toString().trim();

                if (sUsername.isEmpty()) {
                    Toast.makeText(My_Login.this, "Username tidak ditemukan", Toast.LENGTH_SHORT).show();
                } else if (sPassword.isEmpty()) {
                    Toast.makeText(My_Login.this, "Password tidak ditemukan", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(My_Login.this, My_Register.class);
                startActivity(i);
                bersih();
            }
        });

    }

    public void login() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.lOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);

                    editor.putString(Config.USERNAME_SHARED_PREF, sUsername);
                    editor.putString(Config.PASSWORD_SHARED_PREF, sPassword);

                    editor.commit();

                    if (sUsername != null && !sUsername.isEmpty() && !sUsername.equals("null")) {
                        Intent intent = new Intent(My_Login.this, My_Home.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(My_Login.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(My_Login.this, "Connection error, Please try again", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Config.KEY_USER, sUsername);
                params.put(Config.KEY_PASS, sPassword);
//                params.put("Type", sType);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        if (loggedIn) {
            Intent intent = new Intent(My_Login.this, My_Home.class);
            startActivity(intent);
        }

    }

    // @Override
    // public void onBackPressed(){
    //      Intent intent = new Intent(My_Login.this, My_Register.class);
    //     startActivity(intent);
    // }


}
