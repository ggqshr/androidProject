package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ggq.restaurantfin.Adapter.testadp;
import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.Util.Nettool;
import com.example.ggq.restaurantfin.entity.Combo;
import com.example.ggq.restaurantfin.entity.Food;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String foodType;
    private String mParam2;
    private String type;
    private ArrayList<Food> foods;
    private ArrayList<Combo> combos;
    private RecyclerView recyclerView;
    private testadp testadp;
    private testadp comboAdp;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                ArrayList<Food> food1 = (ArrayList<Food>) msg.obj;
                foods.addAll(food1);
                testadp.notifyDataSetChanged();
            } else if (msg.arg1 == 2) {
                ArrayList<Combo> combo1 = (ArrayList<Combo>) msg.obj;
                combos.addAll(combo1);
                comboAdp.notifyDataSetChanged();
            }
        }
    };

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    public ListFragment(String foodType, String type) {
        this.foodType = foodType;
        this.type = type;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        foods = new ArrayList<>();
        combos = new ArrayList<>();
        if (type.equals("food")) {
            getFoodAsType(foodType);
        } else {
            getCombo();
        }
        if (type.equals("combo")) {
            comboAdp = new testadp(getActivity(), null, combos, "combo");
            recyclerView.setAdapter(comboAdp);
        } else {
            testadp = new testadp(getActivity(), foods, null, "food");
            recyclerView.setAdapter(testadp);
        }

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

    public void getFoodAsType(final String foodType) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                new Nettool().getFoodAsType(getActivity(), handler, foodType);
            }
        }.start();

    }

    public void getCombo() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                new Nettool().getAllCombo(getActivity(), handler);
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
