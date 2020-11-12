package com.nursultan.firebasehomework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>  {
    private List<User> userList;

    public UsersAdapter() {
        userList=new ArrayList<>();
    }

    public void setUserList(List<User> userList)
    {
        this.userList=userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewFN.setText(user.getFirstName());
        holder.textViewLN.setText(user.getLastName());
        holder.textViewAge.setText(String.valueOf(user.getAge()));
        holder.textViewSex.setText(user.getSex());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    protected class UserViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewFN;
        TextView textViewLN;
        TextView textViewAge;
        TextView textViewSex;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFN=itemView.findViewById(R.id.textViewFN);
            textViewLN=itemView.findViewById(R.id.textViewLN);
            textViewAge=itemView.findViewById(R.id.textViewAge);
            textViewSex=itemView.findViewById(R.id.textViewSex);
        }
    }
}
