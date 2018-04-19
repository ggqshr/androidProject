package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ggq.restaurantfin.MainActivity;
import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.Util.Nettool;
import com.example.ggq.restaurantfin.entity.Customer;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView user_text;
    private LoginFragment loginFragment;
    private String userNumber;
    private BusinessFragment businessFragment;
    private RelativeLayout relativeLayout;
    private CircleImageView user_login;
    private OnFragmentInteractionListener mListener;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        final MainActivity mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        user_text = (TextView) view.findViewById(R.id.user_name_text);
        user_login = (CircleImageView) view.findViewById(R.id.user_head_image);
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mainActivity.checkLogin()) {
                    if (loginFragment == getActivity().getSupportFragmentManager().findFragmentByTag("login")) {
                        loginFragment = new LoginFragment();
                        loginFragment.setLoginOnClickLis(new LoginFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentInteraction(Uri uri) {

                            }

                            @Override
                            public void quit_login() {
                                getActivity().onBackPressed();
                            }

                            @Override
                            public void login(String username, String passwrod, Context context, Handler handler) {
                                new Nettool().log_req(username, passwrod, context, handler);
                            }

                        });

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.replace(R.id.change_all, loginFragment, "login");
                        transaction.hide(fragmentManager.findFragmentByTag("Title"));
                        transaction.hide(fragmentManager.findFragmentByTag("user"));
                        transaction.addToBackStack("main");
                        transaction.commitAllowingStateLoss();

                    } else {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.replace(R.id.change_all, loginFragment, "login");
                        transaction.hide(fragmentManager.findFragmentByTag("Title"));
                        transaction.hide(fragmentManager.findFragmentByTag("user"));
                        transaction.addToBackStack("main");
                        transaction.commitAllowingStateLoss();
                    }
                }
            }
        });
        relativeLayout = (RelativeLayout) view.findViewById(R.id.user_business);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入订单界面
                if (mainActivity.checkLogin()) {
                    Toasty.info(getActivity(), "进入订单界面").show();
                    if (businessFragment == null) {
                        businessFragment = new BusinessFragment(userNumber);
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.change_all, businessFragment, "business")
                                .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                                .hide(getActivity().getSupportFragmentManager().findFragmentByTag("user"))
                                .addToBackStack(null)
                                .commit();
                    } else {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.change_all, businessFragment, "business")
                                .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                                .hide(getActivity().getSupportFragmentManager().findFragmentByTag("user"))
                                .addToBackStack(null)
                                .commit();
                    }
                } else {
                    Toasty.info(getActivity(), "请登录").show();
                }
            }
        });
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

    public void setUserNameText(Customer Name) {
        this.userNumber = Name.getCustomerNumber();
        user_text.setText(Name.getCustomerName());
    }

    public void SetUserName(String user) {
        user_text.setText(user);
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
