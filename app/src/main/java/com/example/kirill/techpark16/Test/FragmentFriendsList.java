package com.example.kirill.techpark16.Test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kirill.techpark16.R;

/**
 * Created by konstantin on 09.04.16.
 */
public class FragmentFriendsList extends android.app.Fragment {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_friend_list, container, false);

        return view;
    }

}
