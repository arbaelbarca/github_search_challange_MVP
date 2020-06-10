package com.arbaelbarca.githubsearchuser.presenter;

import android.content.Context;

import com.arbaelbarca.githubsearchuser.model.ItemsItem;
import com.arbaelbarca.githubsearchuser.view.MainContract;

import java.util.ArrayList;

public class ListUserPresenter implements MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener {
    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;

    private Context context;

    public ListUserPresenter(MainContract.MainView mainView, Context context, MainContract.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
        this.context = context;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onClickButton() {

    }

    @Override
    public void requestFromDataServer() {
        getNoticeIntractor.getNoticeArrayList(this, "arba", "1", false);
    }

    @Override
    public void refreshData(String textName, String orderPage, boolean isLoading) {
        getNoticeIntractor.getNoticeArrayList(this, textName, orderPage, isLoading);

    }


    @Override
    public void onFinished(ArrayList<ItemsItem> modelListUsers) {
        if (mainView != null) {
            mainView.setDataToRecyclerView(modelListUsers);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (mainView != null)
            mainView.onResponseFailure(t);
    }
}
