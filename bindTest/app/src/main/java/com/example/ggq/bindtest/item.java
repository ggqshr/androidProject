package com.example.ggq.bindtest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ggq.bindtest.util.user;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link item.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link item#newInstance} factory method to
 * create an instance of this fragment.
 */
public class item extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View v;
    private OnFragmentInteractionListener mListener;

    public item() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment item.
     */
    // TODO: Rename and change types and number of parameters
    public static item newInstance(String param1, String param2) {
        item fragment = new item();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("@@@@@","onCreate");
    }
    private ArrayList<user> muser ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.recycle);
        muser = initdata();
        rv.setAdapter(new s.myad(this.getContext(),muser));
        rv.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        Log.d("@@@@@","onCreateView");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_item, container, false);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("@@@@@","onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("@@@@@","onDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }
    public ArrayList<user> initdata()
    {
        ArrayList<user> muser = new ArrayList<>();
        user user = new user();
        user.setDsc("ggq");
        user.setName("222");
        user.setImage(R.mipmap.ic_launcher_round);

        muser.add(user);
        user = new user();
        user.setDsc("2");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("3");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("4");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("5");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("6");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("7");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("8");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("9");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("10");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);
        return muser;
    }
}
