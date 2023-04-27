package com.muammarahlnn.networkingloopj;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private TextView tvName, tvEmail;
    private ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        ivAvatar = findViewById(R.id.iv_avatar);

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://reqres.in/api/users/1";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                try {
                    JSONObject data = new JSONObject(response).getJSONObject("data");

                    String firstName = data.getString("first_name");
                    String lastName = data.getString("last_name");
                    String fullName = firstName + " " + lastName;
                    tvName.setText(fullName);

                    String email = data.getString("email");
                    tvEmail.setText(email);

                    String avatarUrl = data.getString("avatar");
                    Glide.with(MainActivity.this)
                        .load(avatarUrl)
                        .into(ivAvatar);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // if connection failure
                String errorMessage;
                switch (statusCode) {
                    case 401 -> errorMessage = statusCode + " : Bad Request";
                    case 403 -> errorMessage = statusCode + " : Forbidden";
                    case 404 -> errorMessage = statusCode + " : Not Found";
                    default -> errorMessage = statusCode + " : " + error.getMessage();
                }
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}