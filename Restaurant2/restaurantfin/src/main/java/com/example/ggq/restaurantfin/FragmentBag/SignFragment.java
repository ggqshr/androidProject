package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.Util.Nettool;
import com.example.ggq.restaurantfin.databinding.FragmentSignBinding;
import com.example.ggq.restaurantfin.entity.Customer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SignFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private LoadingFragment loadingFragment;
    private Message message;
    private FragmentSignBinding fragmentSignBinding;
    private HashMap<String, String> errormap;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                Customer customer = (Customer) msg.obj;
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .hide(loadingFragment)
                        .commit();
                Toasty.success(getActivity(), "注册成功，请登录").show();
                getActivity().onBackPressed();
            } else if (msg.arg1 == 2) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .hide(loadingFragment)
                        .commit();
                errormap = new HashMap<>();
                errormap.put("usernum", "用户账户已经存在！");
                fragmentSignBinding.setErrormap(errormap);
                fragmentSignBinding.executePendingBindings();
            } else if (msg.arg1 == 3) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .hide(loadingFragment)
                        .commit();
            }
        }
    };

    public SignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSignBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign, container, false);
        View view = inflater.inflate(R.layout.fragment_sign, container, false);
        fragmentSignBinding.quitSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        initbutton();
        return fragmentSignBinding.getRoot();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //初始化所有按钮的点击事件以及出错处理
    public void initbutton() {
        fragmentSignBinding.tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = false;
                errormap = new HashMap<String, String>();
                String usernum = fragmentSignBinding.userNumber.getText().toString();
                Pattern p1 = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}");
                Matcher m1 = p1.matcher(usernum);
                if (!m1.matches()) {
                    flag = true;
                    errormap.put("usernum", "账号名格式不对");
                }
                String userpwd = fragmentSignBinding.pwd.getText().toString();
                String userpwdag = fragmentSignBinding.agpwd.getText().toString();
                if (!userpwd.equals(userpwdag)) {
                    flag = true;
                    errormap.put("agpwd", "两次密码输入不相同");
                }
                String name = fragmentSignBinding.username.getText().toString();
                RadioButton viewById = (RadioButton) fragmentSignBinding.getRoot().findViewById(fragmentSignBinding.sex.getCheckedRadioButtonId());
                String usersex = viewById.getText().toString();
                String userphone = fragmentSignBinding.phone.getText().toString();
                Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
                Matcher m = p.matcher(userphone);
                if (!m.matches()) {
                    flag = true;
                    errormap.put("phone", "手机号格式不对");
                }
                fragmentSignBinding.setErrormap(errormap);
                if (!flag) {
                    final Customer customer = new Customer(usernum, userpwd, name, usersex, userphone);
                    if (loadingFragment == null) {
                        loadingFragment = new LoadingFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .add(R.id.loading_place, loadingFragment)
                                .commit();
                    } else {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .show(loadingFragment)
                                .commit();
                    }
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                new Nettool().Sign_req(customer, getActivity(), handler);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
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
        void onFragmentInteraction(Uri uri);
    }
}
