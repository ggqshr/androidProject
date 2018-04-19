package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ggq.restaurantfin.MainActivity;
import com.example.ggq.restaurantfin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BottomFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BottomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView textView;
    private ImageButton menu;
    private ImageButton user;
    private ContentFragment contentFragment;
    private CartFragment cartFragment;
    private UserFragment userFragment;
    private ImageButton shoping_cart;
    private int flag = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public BottomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomFragment newInstance(String param1, String param2) {
        BottomFragment fragment = new BottomFragment();
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
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menu = (ImageButton) getActivity().findViewById(R.id.menu_button);
        user = (ImageButton) getActivity().findViewById(R.id.user);
        shoping_cart = (ImageButton) getActivity().findViewById(R.id.shopping_cart);
        textView = (TextView) getActivity().findViewById(R.id.titleTag);
        if (flag == 0) {
            init_button();
            flag = 1;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    //设置底部按钮的点击效果以及点击事件
    public void init_button() {
        final MainActivity mainActivity = (MainActivity) getActivity();
        menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.mipmap.pressed_hotpot);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundResource(R.mipmap.hotpot_icon);
                }
                return false;
            }
        });
        user.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.mipmap.pressed_user);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundResource(R.mipmap.usser_icon);
                }
                return false;
            }
        });
        shoping_cart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.mipmap.pressed_shopping_cart);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundResource(R.mipmap.shopping_cart_icon);
                }
                return false;
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mainActivity.getNowFragment().equals(contentFragment)) {
                    if (contentFragment == null) {
                        contentFragment = (ContentFragment) getActivity().getSupportFragmentManager().findFragmentByTag("Content");
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .hide(mainActivity.getNowFragment())
                                .show(contentFragment)
                                .commit();
                    } else {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .hide(mainActivity.getNowFragment())
                                .show(contentFragment)
                                .commit();
                    }
                    mainActivity.setNowFragment(contentFragment);
                    textView.setText("主界面");
                }
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mainActivity.getNowFragment().equals(userFragment)) {
                    if (userFragment == null) {
                        userFragment = (UserFragment) getActivity().getSupportFragmentManager().findFragmentByTag("user");
                        getActivity().getSupportFragmentManager()
                                .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .hide(getActivity().getSupportFragmentManager().findFragmentByTag("cart"))
                                .hide(mainActivity.getNowFragment())
                                .show(userFragment)
                                .commit();
                    } else {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .hide(mainActivity.getNowFragment())
                                .show(userFragment)
                                .commit();
                    }
                    mainActivity.setNowFragment(userFragment);
                    textView.setText("用户");
                }
            }
        });
        shoping_cart.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                if (!mainActivity.getNowFragment().equals(cartFragment)) {
                    if (cartFragment == null) {
                        cartFragment = (CartFragment) getActivity().getSupportFragmentManager().findFragmentByTag("cart");
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .hide(mainActivity.getNowFragment())
                                .show(cartFragment)
                                .commit();
                    } else {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .hide(mainActivity.getNowFragment())
                                .show(cartFragment)
                                .commit();
                    }
                    mainActivity.setNowFragment(cartFragment);
                    textView.setText("购物车");
                }
            }
        });
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

    }
}
