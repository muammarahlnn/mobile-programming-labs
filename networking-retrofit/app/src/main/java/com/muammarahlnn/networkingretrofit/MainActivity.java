package com.muammarahlnn.networkingretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Call<DataResponse> client = ApiConfig.getApiService().getUser("1");
        client.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        UserResponse userResponse = response.body().getData();
                        String fullName = userResponse.getFirstName() + " " + userResponse.getLastName();
                        tvName.setText(fullName);
                        tvEmail.setText(userResponse.getEmail());
                        Glide.with(MainActivity.this)
                            .load(userResponse.getAvatarUrl())
                            .into(ivAvatar);
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}