package com.example.denni.gym;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText ET_FullName,ET_Email,ET_Username,ET_Password,ET_RetypePass;
    private String fullname,email,username,password,conpass;
    private Button mRegister;

    public static final String ROOT_URL = "http://androidcat.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ET_FullName = findViewById(R.id.txtNameReg);
        ET_Email = findViewById(R.id.txtEmailReg);
        ET_Username = findViewById(R.id.txtUsernameReg);
        ET_Password = findViewById(R.id.txtPasswordReg);
        ET_RetypePass = findViewById(R.id.txtConfPasswordReg);

        mRegister = findViewById(R.id.registerBtnReg);

    }
    private void insertUser(){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        //Defining the method insertuer of our interface
        api.insertUser(
                //passing the values by getting it fromeditText
                ET_FullName.getText().toString(),
                ET_Email.getText().toString(),
                ET_Username.getText().toString(),
                ET_Password.getText().toString(),

                //create an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Read th output in the String
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displa the output as a toast
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        Toast.makeText(RegisterActivity.this, output, Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(),Toast.LENGTH_SHORT).show();

                    }
                }
        );


    }


    public void registrationInfo(View view) {
        insertUser();
    }
}
