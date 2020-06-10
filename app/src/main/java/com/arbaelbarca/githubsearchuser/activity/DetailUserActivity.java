package com.arbaelbarca.githubsearchuser.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arbaelbarca.githubsearchuser.R;
import com.arbaelbarca.githubsearchuser.model.ItemsItem;
import com.arbaelbarca.githubsearchuser.utils.Constants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailUserActivity extends AppCompatActivity {

    @BindView(R.id.user_avatar)
    ImageView userAvatar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.user_name_details)
    TextView userNameDetails;
    @BindView(R.id.github_profile_url)
    TextView githubProfileUrl;

    ItemsItem itemsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        ButterKnife.bind(this);

        initial();
    }

    private void initial() {
        itemsItem = getIntent().getParcelableExtra(Constants.DATA_ITEMS);

        if (itemsItem != null) {
            userNameDetails.setText(itemsItem.getLogin());
        }
        githubProfileUrl.setText(itemsItem.getHtmlUrl());

        Glide.with(this)
                .load(itemsItem.getAvatarUrl())
                .into(userAvatar);

    }
}
