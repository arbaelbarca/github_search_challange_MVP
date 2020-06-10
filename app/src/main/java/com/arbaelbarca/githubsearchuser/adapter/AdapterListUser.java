package com.arbaelbarca.githubsearchuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.githubsearchuser.R;
import com.arbaelbarca.githubsearchuser.model.ItemsItem;
import com.arbaelbarca.githubsearchuser.onclick.OnClickItem;
import com.arbaelbarca.githubsearchuser.utils.BaseViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterListUser extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private ArrayList<ItemsItem> itemArrayList;
    private boolean isLoaderVisible = false;


    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;


    public AdapterListUser(Context context) {
        this.context = context;
        itemArrayList = new ArrayList<>();
    }


    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == itemArrayList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }


    public void setData(ArrayList<ItemsItem> newData) {
        itemArrayList.clear();
        itemArrayList = newData;
        notifyDataSetChanged();
    }

//    public void setData(ArrayList<ItemsItem> postItems) {
//        itemArrayList.addAll(postItems);
//        notifyDataSetChanged();
//    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoaderVisible = true;
        add(new ItemsItem());

    }

    public void removeLoadingFooter() {
        isLoaderVisible = false;
        int position = itemArrayList.size() - 1;
        ItemsItem result = getItem(position);

        if (result != null) {
            itemArrayList.remove(position);
            notifyItemRemoved(position);
        }
    }


    public void add(ItemsItem r) {
        itemArrayList.add(r);
        notifyItemInserted(itemArrayList.size() - 1);
    }


    public void remove(ItemsItem r) {
        int position = itemArrayList.indexOf(r);
        if (position > -1) {
            itemArrayList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoaderVisible = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public void clearList() {
        itemArrayList.clear();
    }

    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public ItemsItem getItem(int position) {
        return itemArrayList.get(position);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_NORMAL) {
            return new ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.layout_item_listuser, viewGroup, false));
        } else if (i == VIEW_TYPE_LOADING) {
            return new ProgressHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_loading, viewGroup, false));
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        holder.onBind(position);
    }


    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends BaseViewHolder {
        TextView txtName;
        ImageView imgUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtUser);
            imgUser = itemView.findViewById(R.id.imgUser);


        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            ItemsItem itemsItem = itemArrayList.get(position);
            txtName.setText(itemsItem.getLogin());

            Glide.with(context)
                    .load(itemsItem.getAvatarUrl())
                    .into(imgUser);

            itemView.setOnClickListener(view -> {
                if (onClickItem != null)
                    onClickItem.clickItemDetail(position);
            });
        }

        @Override
        protected void clear() {

        }
    }


    public class ProgressHolder extends BaseViewHolder {
        public ProgressHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }
    }


}
