package com.example.ggq.restaurantfin;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ggq.restaurantfin.FragmentBag.AboutAppFragment;
import com.example.ggq.restaurantfin.FragmentBag.BeginFragment;
import com.example.ggq.restaurantfin.FragmentBag.BottomFragment;
import com.example.ggq.restaurantfin.FragmentBag.CartFragment;
import com.example.ggq.restaurantfin.FragmentBag.ChangePwdFragment;
import com.example.ggq.restaurantfin.FragmentBag.ContentFragment;
import com.example.ggq.restaurantfin.FragmentBag.LoginFragment;
import com.example.ggq.restaurantfin.FragmentBag.UserFragment;
import com.example.ggq.restaurantfin.FragmentBag.titleFragment;
import com.example.ggq.restaurantfin.Util.Nettool;
import com.example.ggq.restaurantfin.View.SlideView;
import com.example.ggq.restaurantfin.entity.Combo;
import com.example.ggq.restaurantfin.entity.Customer;
import com.example.ggq.restaurantfin.entity.Food;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private static boolean isLogin;
    private static int Flag = 0;
    private static Fragment nowFragment;
    //    private TextView menu_name;
//    private TextView menu_idcard;
//    private TextView menu_key;
//    private TextView menu_ifm;
//    private TextView menu_power;
//    private ImageView menu_name_image;
//    private ImageView menu_idcard_image;
//    private ImageView menu_key_image;
//    private ImageView menu_ifm_image;
//    private ImageView menu_power_image;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private int flag = 0;
    private int INF = 1000000000;
    int xxx = INF;
    private titleFragment titleFragment;
    private LoginFragment loginFragment;
    private Customer loginCustomer;
    private BottomFragment bottomFragment;
    private CartFragment cartFragment;
    private ContentFragment contentFragment;
    private BeginFragment beginFragment;
    //启动界面的隐藏
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.arg1 == 1) {
                transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                transaction.remove(beginFragment)
                        .commit();
            }
        }
    };
    private UserFragment userFragment;
    private SlideView slideView;
    private ArrayList<Food> cartFoodData;
    private ArrayList<Combo> cartComboData;

    public static void setLogin(boolean result) {
        isLogin = result;
    }

    public static boolean checkLogin() {
        return isLogin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        isLogin = false;
        fragmentManager = getSupportFragmentManager();
        slideView = (SlideView) findViewById(R.id.id_menu);

        slideView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                xxx = (int) motionEvent.getX();
                switch (action) {
                    case MotionEvent.ACTION_MOVE:
                        if (!slideView.getIsOpen()) {
                            if (xxx == INF) {
                                xxx = (int) motionEvent.getX();
                            }
                            if (xxx > 900)
                                return true;
                        }
                }
                return false;
            }
        });
        //初始化所有fragment
        transaction = fragmentManager.beginTransaction();
        if (savedInstanceState != null) {
            bottomFragment = (BottomFragment) fragmentManager.findFragmentByTag("Bottom");
            contentFragment = (ContentFragment) fragmentManager.findFragmentByTag("Content");
            titleFragment = (com.example.ggq.restaurantfin.FragmentBag.titleFragment) fragmentManager.findFragmentByTag("Title");
            loginFragment = (LoginFragment) fragmentManager.findFragmentByTag("login");
            beginFragment = (BeginFragment) fragmentManager.findFragmentByTag("begin");
            userFragment = (UserFragment) fragmentManager.findFragmentByTag("user");
            cartFragment = (CartFragment) fragmentManager.findFragmentByTag("cart");
        }
        {
            bottomFragment = new BottomFragment();
            contentFragment = new ContentFragment();
            titleFragment = new titleFragment();
            beginFragment = new BeginFragment();
            userFragment = new UserFragment();
            cartFragment = new CartFragment();
            transaction.add(R.id.TitleF, titleFragment, "Title");
            transaction.add(R.id.contentF, userFragment, "user");
            transaction.add(R.id.contentF, cartFragment, "cart");
            transaction.add(R.id.contentF, contentFragment, "Content");
            transaction.add(R.id.BottomF, bottomFragment, "Bottom");
            transaction.add(R.id.change_all, beginFragment, "begin");
            //将 fragment添加进framlayout
            transaction.commit();
        }
        nowFragment = contentFragment;
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.arg1 = 1;
                handler.sendMessage(message);
                super.run();
            }
        }.start();
        //初始化titleFragment
        titleFragment.setOnClickItemEvent(new titleFragment.OntitleFragmentInteractionListener() {
            @Override
            public void onFragmentInteraction(Uri uri) {

            }

            @Override
            public void user_head_click() {
                if (loginFragment == null) {
                    loginFragment = new LoginFragment();
                    loginFragment.setLoginOnClickLis(new LoginFragment.OnFragmentInteractionListener() {
                        @Override
                        public void onFragmentInteraction(Uri uri) {

                        }

                        @Override
                        public void quit_login() {
                            onBackPressed();
                        }

                        @Override
                        public void login(String username, String passwrod, Context context, Handler handler) {
                            new Nettool().log_req(username, passwrod, context, handler);
                        }

                    });


                    transaction = fragmentManager.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.replace(R.id.change_all, loginFragment, "login");
                    transaction.hide(titleFragment);
                    transaction.hide(contentFragment);
                    transaction.addToBackStack("main");
                    transaction.commitAllowingStateLoss();

                } else {
                    transaction = fragmentManager.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.replace(R.id.change_all, loginFragment, "login");
                    transaction.hide(titleFragment);
                    transaction.hide(contentFragment);
                    transaction.addToBackStack("11");
                    transaction.commit();
                }

            }

            @Override
            public void setting_click() {
                Toasty.success(MainActivity.this, "这是activity点击的1111", 1000).show();
                int num = fragmentManager.getBackStackEntryCount();
                String numString = "++++++++++++++++++++++++++++++++++Fragment回退栈数量：" + num;
                Log.d("Fragment", numString);
                for (int i = 0; i < num; i++) {
                    FragmentManager.BackStackEntry backstatck = fragmentManager.getBackStackEntryAt(i);
                    Log.d("Fragment", backstatck.getName());
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void toggle() {
        slideView.toggle();
    }

    /*
    初始化所有菜单的点击事件
     */
    public void initMenu(View v) {
        int id = v.getId();
        if (!isLogin) {

            if (id == R.id.name || id == R.id.item_imag) {
                slideView.toggle();
                if (loginFragment == null) {
                    loginFragment = new LoginFragment();
                    loginFragment.setLoginOnClickLis(new LoginFragment.OnFragmentInteractionListener() {
                        @Override
                        public void onFragmentInteraction(Uri uri) {

                        }

                        @Override
                        public void quit_login() {
                            onBackPressed();
                        }

                        @Override
                        public void login(String username, String passwrod, Context context, Handler handler) {
                            new Nettool().log_req(username, passwrod, context, handler);
                        }

                    });
                    transaction = fragmentManager.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.replace(R.id.change_all, loginFragment, "login");
                    transaction.addToBackStack("main");
                    transaction.commitAllowingStateLoss();

                } else {
                    transaction = fragmentManager.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.replace(R.id.change_all, loginFragment, "login");
                    transaction.addToBackStack("11");
                    transaction.commit();
                }
            } else if (id == R.id.power_text || id == R.id.power_image) {
                this.finish();
            } else if (id == R.id.ifm_image || id == R.id.ifm_text) {
                toggle();
                AboutAppFragment aboutAppFragment = new AboutAppFragment();
                transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.change_all, aboutAppFragment, "aboutApp");
                transaction.hide(titleFragment);
                transaction.hide(contentFragment);
                transaction.addToBackStack("main");
                transaction.commitAllowingStateLoss();
            } else {
                Toasty.info(MainActivity.this, "请登录", 2500).show();
            }
        } else {
            if (id == R.id.key_image || id == R.id.key_text) {
                toggle();
                ChangePwdFragment changePwdFragment = new ChangePwdFragment();
                transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.change_all, changePwdFragment, "changepwd");
                transaction.hide(titleFragment);
                transaction.hide(contentFragment);
                transaction.addToBackStack("main");
                transaction.commitAllowingStateLoss();
            } else if (id == R.id.ifm_image || id == R.id.ifm_text) {
                toggle();
                AboutAppFragment aboutAppFragment = new AboutAppFragment();
                transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.change_all, aboutAppFragment, "aboutApp");
                transaction.hide(titleFragment);
                transaction.hide(contentFragment);
                transaction.addToBackStack("main");
                transaction.commitAllowingStateLoss();
            } else if (id == R.id.power_image || id == R.id.power_text) {
                this.finish();
            } else if (id == R.id.loginout_image || id == R.id.loginout_text) {
                TextView textView = (TextView) findViewById(R.id.name);
                textView.setText("请登录");
                SetUserName("请登录");
                Toasty.info(MainActivity.this, "账号退出成功", 1500).show();
                isLogin = false;
                slideView.toggle();
            }
        }
    }

    public Fragment getNowFragment() {
        return nowFragment;
    }

    public void setNowFragment(Fragment fragment) {
        nowFragment = fragment;
    }

    /*
    用于向购物车中加入数据
     */
    public void addToFoodCart(Food food) {
        cartFragment.addToFoodCart(food);
    }

    public void addToComboCart(Combo combo) {
        cartFragment.addToComboCart(combo);
    }

    public float getCartPrice() {
        return cartFragment.getSumPrice();
    }

    public void setCartPrice(float c) {
        cartFragment.setSumPrice(c);
    }

    //获取设置当前登陆用户

    public Customer getLoginCustomer() {
        return loginFragment.getLoginCustomer();
    }

    public void setUserFragmentText(Customer name) {
        userFragment.setUserNameText(name);
    }

    public void SetUserName(String name) {
        userFragment.SetUserName(name);
    }
}
