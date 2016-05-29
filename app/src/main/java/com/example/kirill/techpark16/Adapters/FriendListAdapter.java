package com.example.kirill.techpark16.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirill.techpark16.CircleTransform;
import com.example.kirill.techpark16.Fragments.FragmentSingleFriend;
import com.example.kirill.techpark16.Friend;
import com.example.kirill.techpark16.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kirill on 07.05.16
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.PersonViewHolder>{

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        ImageView personPhoto;
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    List<Friend> persons;
    private FragmentManager fragmentManager;
    Context context;

    public FriendListAdapter(Context context, FragmentManager fragmentManager, List<Friend> persons){
        this.context = context;
        this.persons = persons;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friendlist_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, final int i) {
        personViewHolder.personName.setText(persons.get(i).getFullName());
        personViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentSingleFriend newFragment = FragmentSingleFriend.getInstance(persons.get(i).getFriendId(),
                        persons.get(i).getFirstName(), persons.get(i).getLastName());
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentPlace, newFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Picasso.with(context).load(persons.get(i).getPhotoUrl()).transform(new CircleTransform())
                .placeholder(R.drawable.placeholder_light)
                .into(personViewHolder.personPhoto);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}