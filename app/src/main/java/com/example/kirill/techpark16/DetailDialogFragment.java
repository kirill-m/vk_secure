package com.example.kirill.techpark16;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;

;

/**
 * Created by kirill on 02.04.16
 */
public class DetailDialogFragment extends ListFragment {
    public static String USER_ID = "user_id";
    public static String IN_LIST = "inList";
    public static String OUT_LIST = "outList";

    ArrayList<String> inList = new ArrayList<>();
    ArrayList<String> outList = new ArrayList<>();
    int id = 0;


    EditText text;
    ListView listView;
    Button send;

    public static DetailDialogFragment getInstance(int user_id, ArrayList<String> inList, ArrayList<String> outList){
        DetailDialogFragment detailDialogFragment = new DetailDialogFragment();
//        Log.i("inList2", String.valueOf((inList.get(2))));
        Bundle bundle = new Bundle();
        bundle.putInt(USER_ID, user_id);
        bundle.putStringArrayList(IN_LIST, inList);
        bundle.putStringArrayList(OUT_LIST, outList);



        detailDialogFragment.setArguments(bundle);
        return detailDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_detail_fragment, null);

        inList = getArguments().getStringArrayList(IN_LIST);
        outList = getArguments().getStringArrayList(OUT_LIST);
        id = getArguments().getInt(USER_ID);

        text = (EditText) view.findViewById(R.id.textmsg);
        listView = (ListView) view.findViewById(R.id.listmsg);

        listView.setAdapter(new DetailDialogAdapter(view.getContext(), inList, outList));

        send = (Button) view.findViewById(R.id.sendmsg);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VKRequest request = new VKRequest("messages.send", VKParameters.from(VKApiConst.USER_ID, id,
                        VKApiConst.MESSAGE, text.getText().toString()));

                request.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        System.out.println("Сообщение отправлено");
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.single_dialog_title);
        getActivity().findViewById(R.id.toolbar).findViewById(R.id.toolbar_button_sett).setVisibility(View.VISIBLE);
    }

}
