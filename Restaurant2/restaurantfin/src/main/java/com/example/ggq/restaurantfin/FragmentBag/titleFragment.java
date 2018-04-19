package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ggq.restaurantfin.MainActivity;
import com.example.ggq.restaurantfin.R;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link titleFragment.OntitleFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link titleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class titleFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageButton user_head;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OntitleFragmentInteractionListener mListener;

    public titleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment titleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static titleFragment newInstance(String param1, String param2) {
        titleFragment fragment = new titleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnClickItemEvent(OntitleFragmentInteractionListener s) {
        this.mListener = s;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        user_head = (ImageButton) view.findViewById(R.id.user_head);
        user_head.setOnClickListener(this);
        return view;
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //设置标题栏按钮的点击事件，判断是否借口回调了，
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_head:
                if (mListener != null) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    boolean isLogin = mainActivity.checkLogin();
                    if (isLogin) {
                        mainActivity.toggle();
                    } else {
                        mListener.user_head_click();
                    }
                    break;
                } else {
                    Toasty.success(getActivity(), "这是头像", Toast.LENGTH_SHORT).show();
                    break;
                }

        }
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
    public interface OntitleFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void user_head_click();

        void setting_click();
    }

}
