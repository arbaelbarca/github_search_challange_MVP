package com.arbaelbarca.githubsearchuser.view;

import android.app.LauncherActivity;

import com.arbaelbarca.githubsearchuser.model.ItemsItem;
import com.arbaelbarca.githubsearchuser.model.ModelListUser;

import java.util.ArrayList;

public interface MainContract {

    interface presenter {
        void onDestroy();

        void onClickButton();

        void requestFromDataServer();

        void refreshData(String textSearch, String orderPage, boolean isLoading);
    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<ItemsItem> modelListUsers);

        void onResponseFailure(Throwable throwable);

    }

    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<ItemsItem> modelListUsers);

            void onFailure(Throwable t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener, String textSearch, String orderPage, boolean isLoading);

    }
}
