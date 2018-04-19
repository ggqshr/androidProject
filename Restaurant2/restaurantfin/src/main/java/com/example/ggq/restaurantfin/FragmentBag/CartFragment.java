package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ggq.restaurantfin.Adapter.CartAdapter;
import com.example.ggq.restaurantfin.Adapter.CartComboAdapter;
import com.example.ggq.restaurantfin.MainActivity;
import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.Util.Nettool;
import com.example.ggq.restaurantfin.View.FullyLinearLayoutManager;
import com.example.ggq.restaurantfin.entity.Combo;
import com.example.ggq.restaurantfin.entity.Food;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button cart_But_All;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView_combo;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Food> foods;
    private ArrayList<Combo> combos;
    private TextView cart_price;
    private float sumPrice = 0f;
    private CartComboAdapter cartComboAdapter;
    private CartAdapter cartAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg2 == 1) {
                //购物车中不存在相同的食物
                Food food = (Food) msg.obj;
                foods.add(food);
                sumPrice += food.getFoodPrice() * food.getFoodNum();
                cart_price.setText(sumPrice + "");
                cartAdapter.notifyDataSetChanged();
            } else if (msg.arg2 == 3) {
                //购物车中已经存在相同的食物
                Food food = (Food) msg.obj;
                sumPrice += food.getFoodPrice();
                cart_price.setText(sumPrice + "");
                cartAdapter.notifyDataSetChanged();
            } else if (msg.arg2 == 2) {
                //将套餐加入到购物车
                Combo combo = (Combo) msg.obj;
                combos.add(combo);
                sumPrice += combo.getComboPrice();
                cart_price.setText(sumPrice + "");
                cartComboAdapter.notifyDataSetChanged();
            } else if (msg.arg1 == 1) {
                Toasty.success(getActivity(), "结账成功，欢迎下次光临", 1500).show();
                foods.clear();
                combos.clear();
                cart_price.setText("0");
                cartAdapter.notifyDataSetChanged();
                cartComboAdapter.notifyDataSetChanged();
            }
        }
    };

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.cart_Recycle);
        recyclerView_combo = (RecyclerView) view.findViewById(R.id.cart_Recycle_combo);
        combos = new ArrayList<>();
        foods = new ArrayList<>();
        cart_price = (TextView) view.findViewById(R.id.cart_price);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        recyclerView_combo.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView_combo.setNestedScrollingEnabled(false);
        cartAdapter = new CartAdapter(getActivity(), foods);
        cartComboAdapter = new CartComboAdapter(getActivity(), combos);
        cart_But_All = (Button) view.findViewById(R.id.cart_buy_all);
        cart_But_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MainActivity mainActivity = (MainActivity) getActivity();
                if (!mainActivity.checkLogin()) {
                    Toasty.info(getActivity(), "请登录后再进行操作").show();
                } else {
                    final HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("food", foods);
                    map.put("combo", combos);
                    map.put("customer", mainActivity.getLoginCustomer());
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("确认提交么，您一共需要花费" + cart_price.getText() + "元");
                    builder.setTitle("确认");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    new Nettool().submitAllThing(getActivity(), handler, map);
                                }
                            }.start();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }
            }
        });
        recyclerView.addItemDecoration(new ContentFragment.DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView_combo.addItemDecoration(new ContentFragment.DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(cartAdapter);
        recyclerView_combo.setAdapter(cartComboAdapter);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public Food[] otherData() {
        Food[] customers = new Food[5];
        Food food;
        for (int i = 0; i < 10; i++) {
            food = new Food();
            food.setFoodName("i+sss");
            food.setFoodPrice(2.12f + i);
            customers[i] = food;
        }
        return customers;
    }

//    public void initdata() {
//        foods = new ArrayList<>();
//        Food food;
//        for (int i = 0; i < 10; i++) {
//            food = new Food();
//            food.setFoodName(i + "xhs");
//            food.setFoodNumber(i + "");
//            food.setFoodPrice(12.1f);
//            food.setFoodNum(3);
//            foods.add(food);
//        }
//        combos = new ArrayList<>();
//        Combo combo = new Combo();
//        combo.setComboName("香锅鱼");
//        combo.setComboPrice(12f);
//        combo.setComboPhoto("/img/1");
//        combos.add(combo);
//    }

    public void addToFoodCart(Food food) {
        Message message = new Message();
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getFoodNumber().equals(food.getFoodNumber())) {
                int s = foods.get(i).getFoodNum();
                foods.get(i).setFoodNum(++s);
                message.arg2 = 3;
                message.obj = foods.get(i);
                handler.sendMessage(message);
                return;
            }
        }
        message.obj = food;
        message.arg2 = 1;
        handler.sendMessage(message);
    }

       public void addToComboCart(Combo combo) {
        Message message = new Message();
        for (int i = 0; i < combos.size(); i++) {
            if (combos.get(i).getComboNumber().equals(combo.getComboNumber())) {
                Toasty.warning(getActivity(), "此套餐已存在购物车中").show();
                return;
            }
        }
        Toasty.success(getActivity(), "购物车成功").show();
        message.obj = combo;
        message.arg2 = 2;
        handler.sendMessage(message);
    }

    public float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
        cart_price.setText(this.sumPrice + "");
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
