package com.example.myapps;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapps.app.AppController;
import com.example.myapps.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class My_Register extends AppCompatActivity {
    String sUsername, sPassword, sFullname, sPhone, sAddress, sGender;
    EditText edtUsername, edtPassword, edtFullname, edtPhone, edtAddress;
    Spinner spinner;
    Button btnRegister;
    int success;

    private static final String TAG = My_Register.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__register);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtFullname = findViewById(R.id.edtFullname);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        spinner = findViewById(R.id.spinner);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sUsername = edtUsername.getText().toString();
                sPassword = edtPassword.getText().toString();
                sFullname = edtFullname.getText().toString();
                sPhone = edtPhone.getText().toString();
                sAddress = edtAddress.getText().toString();
                sGender = spinner.getSelectedItem().toString();

                if (sUsername.isEmpty()) {
                    Toast.makeText(My_Register.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (sPassword.isEmpty()) {
                    Toast.makeText(My_Register.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (sFullname.isEmpty()) {
                    Toast.makeText(My_Register.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (sPhone.isEmpty()) {
                    Toast.makeText(My_Register.this, "Nomor telpon tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (sAddress.isEmpty()) {
                    Toast.makeText(My_Register.this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (sGender.isEmpty()) {
                    Toast.makeText(My_Register.this, "Gender tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    register();
                }
            }
        });
    }

    private void register() {

        String url = Server.REGISTER_URL;
        final ProgressDialog loading = ProgressDialog.show(this, "", "Loading...", true, true);
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/Update", jObj.toString());
                        loading.dismiss();

                        Toast.makeText(My_Register.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        bersih();
                    } else {
                        Toast.makeText(My_Register.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        bersih();
                        loading.dismiss();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                loading.dismiss();
                Toast.makeText(My_Register.this, "Koneksi Gagal, Silahkan coba lagi", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", sUsername);
                params.put("password", sPassword);
                params.put("fullname", sFullname);
                params.put("phone", sPhone);
                params.put("address", sAddress);
                params.put("gender", sGender);

                return params;
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void bersih() {
        edtUsername.setText("");
        edtPassword.setText("");
        edtFullname.setText("");
        edtPhone.setText("");
        edtAddress.setText("");

    }


}
