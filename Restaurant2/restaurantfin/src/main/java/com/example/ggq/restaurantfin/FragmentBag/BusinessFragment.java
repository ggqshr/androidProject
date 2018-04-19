package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ggq.restaurantfin.Adapter.BusinessAdp;
import com.example.ggq.restaurantfin.Adapter.FoodCheckAdp;
import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.Util.Nettool;
import com.example.ggq.restaurantfin.View.FullyLinearLayoutManager;
import com.example.ggq.restaurantfin.entity.Business;
import com.example.ggq.restaurantfin.entity.FoodCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BusinessFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BusinessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String customerNumber;
    private ArrayList<Business> businesses;
    private RecyclerView BusinessR;
    private RecyclerView FoodCheckR;
    private ArrayList<FoodCheck> foodChecks;
    private BusinessAdp businessAdp;
    private FoodCheckAdp foodCheckAdp;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                ArrayList<String> strings = (ArrayList<String>) msg.obj;
                Gson gson = new Gson();
                ArrayList<Business> businesse1 = gson.fromJson(strings.get(0), new TypeToken<ArrayList<Business>>() {
                }.getType());
                businesses.addAll(businesse1);
                ArrayList<FoodCheck> foodCheck1 = gson.fromJson(strings.get(1), new TypeToken<ArrayList<FoodCheck>>() {
                }.getType());
                foodChecks.addAll(foodCheck1);
                businessAdp.notifyDataSetChanged();
                foodCheckAdp.notifyDataSetChanged();
            } else if (msg.arg1 == 2) {
                Toasty.error(getActivity(), "服务器出现问题了，请稍候再试").show();
            }
        }
    };
    private OnFragmentInteractionListener mListener;

    public BusinessFragment() {
        // Required empty public constructor
    }

    public BusinessFragment(String customerNumber) {
        // Required empty public constructor
        this.customerNumber = customerNumber;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusinessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessFragment newInstance(String param1, String param2) {
        BusinessFragment fragment = new BusinessFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        businesses = new ArrayList<>();
        foodChecks = new ArrayList<>();
        businessAdp = new BusinessAdp(getActivity(), businesses);
        foodCheckAdp = new FoodCheckAdp(getActivity(), foodChecks);
        GetBusiness();
        BusinessR = (RecyclerView) view.findViewById(R.id.business_combo);
        FoodCheckR = (RecyclerView) view.findViewById(R.id.business_foodcheck);
        BusinessR.setAdapter(businessAdp);
        BusinessR.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        BusinessR.addItemDecoration(new ContentFragment.DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        BusinessR.setNestedScrollingEnabled(false);
        FoodCheckR.setAdapter(foodCheckAdp);
        FoodCheckR.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        FoodCheckR.addItemDecoration(new ContentFragment.DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        FoodCheckR.setNestedScrollingEnabled(false);
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

    public void GetBusiness() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                new Nettool().getBusinessAsCustomerNumber(getActivity(), handler, customerNumber);
            }
        }.start();
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
}
