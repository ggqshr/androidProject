package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ggq.restaurantfin.MainActivity;
import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.Util.Nettool;
import com.example.ggq.restaurantfin.entity.Customer;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.signin_sign)
    TextView signinSign;
    private LoadingFragment loadingFragment;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView;
    private OnFragmentInteractionListener mListener;
    private ImageButton quit_login_b;
    private Customer loginCustomer;
    private ImageButton submit_button;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                loginCustomer = (Customer) msg.obj;
                ((TextView) getActivity().findViewById(R.id.name)).setText(loginCustomer.getCustomerName());
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setUserFragmentText(loginCustomer);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .remove(loadingFragment)
                        .commit();
                Toasty.success(getActivity(), "登陆成功", 2500).show();
                mainActivity.setLogin(true);
                getActivity().onBackPressed();
            } else if (msg.arg1 == 2) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .remove(loadingFragment)
                        .commit();
                Toasty.error(getActivity(), "用户名或者密码错误", 2500).show();
            } else if (msg.arg1 == 3) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .remove(loadingFragment)
                        .commit();
                Toasty.warning(getActivity(), "用户不存在", 2500).show();
            } else if (msg.arg1 == 4) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .remove(loadingFragment)
                        .commit();
            }
        }
    };

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object)
                    .toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        loadingFragment = new LoadingFragment();

        setSignOnclick();

        submit_button = (ImageButton) view.findViewById(R.id.submit);
        textView = (TextView) view.findViewById(R.id.tv_status);
        //设置背景图片
//        Glide.with(this).load(R.drawable.login_background)
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        view.findViewById(R.id.backgrount).setBackground(resource);
//                    }
//                });

        textView.getLayoutParams().height = getStatusHeight(this.getContext());
        quit_login_b = (ImageButton) view.findViewById(R.id.quit_login);
        quit_login_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.quit_login();
                else {
                    getActivity().onBackPressed();
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//                             .remove(getActivity().getSupportFragmentManager().findFragmentByTag("login")).commit();
//                    Toasty.warning(getActivity(), "你没有设置事件，你僵了", 1000).show();
                }
            }
        });
        boolean check_net = isNetworkConnected(getActivity());
        if (!check_net) {
            Toasty.info(getActivity(), "网络不通哦亲", 2000).show();
        } else {
            Toasty.info(getActivity(), "网络通畅", 2000).show();
        }
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkConnected(getActivity())) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                    .replace(R.id.loading, loadingFragment)
                                    .commit();
                            new Nettool().log_req(username.getText().toString(),
                                    password.getText().toString(), getActivity(), handler);
                        }
                    };
                    thread.start();
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setLoginOnClickLis(OnFragmentInteractionListener m) {
        this.mListener = m;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //检查网络状态
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public void setSignOnclick() {
        signinSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();
                SignFragment signFragment = new SignFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                for (Fragment f :fragments
//                     ) {
//                    Log.d("test",f.getId()+"");
//                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .hide(loginFragment)
                        .detach(getActivity().getSupportFragmentManager().findFragmentByTag("login"))
                        .add(R.id.change_all, signFragment)
                        .addToBackStack("!!")
                        .commit();

            }
        });

    }

    public Customer getLoginCustomer() {
        return loginCustomer;
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

        void quit_login();

        void login(String username, String passwrod, Context context, Handler handler);

    }

}
