package com.example.denni.gym;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
    private Button BTN_Login;
    private EditText ET_Username,ET_Password;
    private TextView txtregister;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BTN_Login = findViewById(R.id.loginBtnLogin);
        ET_Username = findViewById(R.id.txtUsernameLogin);
        ET_Password = findViewById(R.id.txtPasswordLogin);
        txtregister = findViewById(R.id.txtregiter);

        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    public void loginToProfile(View view) {
        username = ET_Username.getText().toString();
        password = ET_Password.getText().toString();


        LoginBackgroundTask loginBackgrondTask = new LoginBackgroundTask();
        loginBackgrondTask.execute(username,password);
        finish();
    }
    class LoginBackgroundTask extends AsyncTask<String, Void, String>
    {
        String login_url;
        @Override
        protected void onPreExecute() {
            login_url = "http://androidcat.000webhostapp.com/Login.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String uname = args[0];
            String upass = args[1];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(uname, "UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(upass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = " ";

                while ((line = bufferedReader.readLine()) != null)
                {
                    response +=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));

        }
    }
}
