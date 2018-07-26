package com.kartikeya.firebasetest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserList extends ArrayAdapter<users> {

    private Activity context;
    private List<users> usersList;

    public UserList(Activity context, List<users> usersList){
        super(context, R.layout.list_layout, usersList);
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisiViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) lisiViewItem.findViewById(R.id.textView);
        TextView textViewType = (TextView) lisiViewItem.findViewById(R.id.textView2);

        users user = usersList.get(position);

        textViewName.setText(user.getUserName());
        textViewType.setText(user.getPermissionType());

        return lisiViewItem;
    }


}
