package com.arbaelbarca.githubsearchuser;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.githubsearchuser.activity.DetailUserActivity;
import com.arbaelbarca.githubsearchuser.adapter.AdapterListUser;
import com.arbaelbarca.githubsearchuser.baseactivity.BaseActivity;
import com.arbaelbarca.githubsearchuser.model.ItemsItem;
import com.arbaelbarca.githubsearchuser.onclick.OnClickItem;
import com.arbaelbarca.githubsearchuser.presenter.ListPresenterImpl;
import com.arbaelbarca.githubsearchuser.presenter.ListUserPresenter;
import com.arbaelbarca.githubsearchuser.view.MainContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.arbaelbarca.githubsearchuser.utils.Constants.DATA_ITEMS;

public class MainActivity extends BaseActivity implements MainContract.MainView, View.OnClickListener {

    ListUserPresenter presenter;
    AdapterListUser adapterListUser;
    MainContract.MainView mainView;

    @BindView(R.id.txtSearchUser)
    EditText txtSearchUser;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rvListUser)
    RecyclerView rvListUser;

    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.llNotFound)
    LinearLayout llNotFound;
    @BindView(R.id.imgSearch)
    ImageView imgClose;


    private boolean isLastPage = false;
    private int per_page = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    private int PAGE_START = 1;
    private int currentPage = PAGE_START;
    String getTextSearch;
    ArrayList<ItemsItem> itemArrayList = new ArrayList<>();

    OnClickItem onClickItem = pos -> {
        ItemsItem itemsItem = itemArrayList.get(pos);
        Intent intent = new Intent(getApplicationContext(), DetailUserActivity.class);
        intent.putExtra(DATA_ITEMS, itemsItem);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initial();


        presenter = new ListUserPresenter(this, this, new ListPresenterImpl(MainActivity.this, ""));
        presenter.requestFromDataServer();

        txtSearchUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getTextSearch = charSequence.toString();
                if (getTextSearch.length() > 0) {
                    addSearchText(getTextSearch);
                    showView(rvListUser);
                    showView(imgClose);
                    hideView(llNotFound);
                } else {
                    hideView(rvListUser);
                    hideView(imgClose);
                    showView(llNotFound);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imgClose.setOnClickListener(this);


    }


    private void addSearchText(String charSequence) {
        itemArrayList.clear();
        presenter.refreshData(charSequence, String.valueOf(PAGE_START), isLoading);
    }

    private void initial() {
        linearLayoutManager = new LinearLayoutManager(this);
        rvListUser.setLayoutManager(linearLayoutManager);
        rvListUser.setHasFixedSize(true);

//        setupPagination();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<ItemsItem> modelListUsers) {
        adapterListUser = new AdapterListUser(this);
        itemArrayList = modelListUsers;
        adapterListUser.setData(itemArrayList);
        rvListUser.setAdapter(adapterListUser);
        adapterListUser.setOnClickItem(onClickItem);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        presenter.onFailure(throwable);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

//    private void setupPagination() {
//        rvListUser.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
//            @Override
//            public int getTotalPageCount() {
//                return per_page;
//            }
//
//            @Override
//            protected void loadMoreItems() {
//                isLoading = true;
//                currentPage++;
//
//                new Handler().postDelayed(() -> loadNextPage(), 1000);
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });
//    }

    void loadNextPage() {
        adapterListUser.addLoadingFooter();
        presenter.refreshData(getTextSearch, String.valueOf(currentPage), isLoading);
        isLoading = false;
    }

    @Override
    public void onClick(View view) {
        if (view == imgClose) {
            txtSearchUser.setText("");
        }
    }
}
