package com.arbaelbarca.githubsearchuser.baseactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void hideView(View view) {
        view.setVisibility(View.GONE);
    }

    public void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
