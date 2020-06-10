package com.arbaelbarca.githubsearchuser.presenter;

import android.content.Context;

import com.arbaelbarca.githubsearchuser.model.ModelListUser;
import com.arbaelbarca.githubsearchuser.network.NetworkApi;
import com.arbaelbarca.githubsearchuser.view.MainContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenterImpl implements MainContract.GetNoticeIntractor, MainContract.presenter {

    private Context context;
    private String perPage;

    public ListPresenterImpl(Context context, String perPage) {
        this.context = context;
        this.perPage = perPage;
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onClickButton() {

    }

    @Override
    public void requestFromDataServer() {

    }

    @Override
    public void refreshData(String textSearch, String orderPage, boolean isLoading) {
        getNoticeArrayList((OnFinishedListener) this, textSearch, orderPage, isLoading);

    }

    @Override
    public void getNoticeArrayList(OnFinishedListener onFinishedListener, String textSearch, String orderPage, boolean isLoading) {
        NetworkApi.getInstance()
                .getAPI()
                .getListUser(textSearch, "", orderPage, perPage)
                .enqueue(new Callback<ModelListUser>() {
                    @Override
                    public void onResponse(Call<ModelListUser> call, Response<ModelListUser> response) {
                        if (response.body() != null) {
                            onFinishedListener.onFinished(response.body().getItems());
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelListUser> call, Throwable t) {
                        onFinishedListener.onFailure(t);
                    }
                });
    }


}
