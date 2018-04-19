package com.example.ggq.restaurantfin.FragmentBag;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.ggq.restaurantfin.Adapter.testadp;
import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.Util.Nettool;
import com.example.ggq.restaurantfin.entity.Food;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Food> foods;
    private ImageButton meatButton;
    private ImageButton vegetableButton;
    private ImageButton soupButton;
    private ListFragment meatF;
    private ListFragment vegetableF;
    private ListFragment soupF;
    private ListFragment noodleF;
    private ListFragment comboF;
    private ListFragment drunkF;

    private ImageButton noodleButton;
    private ImageButton comboButton;
    private ImageButton drunkButton;
    private testadp testadp;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                ArrayList<Food> food1 = (ArrayList<Food>) msg.obj;
                foods.clear();
                foods.addAll(food1);
                testadp.notifyDataSetChanged();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            } else if (msg.arg1 == 2) {
                swipeRefreshLayout.setRefreshing(false);
            }

        }
    };

    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
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
        final View view = inflater.inflate(R.layout.fragment_content, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.Refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.Recycle);
//        Glide.with(getActivity()).load(R.drawable.menubackg)
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        view.findViewById(R.id.mine).setBackground(resource);
//                    }
//                });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Nettool().getHotFood(getActivity(), handler);
            }
        });
        initdata();
        testadp = new testadp(getActivity(), foods, null, "food");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final View head = inflater.from(getActivity()).inflate(R.layout.fragment_head, recyclerView, false);
        meatButton = (ImageButton) head.findViewById(R.id.meat);
        vegetableButton = (ImageButton) head.findViewById(R.id.vegetable);
        soupButton = (ImageButton) head.findViewById(R.id.soup);
        noodleButton = (ImageButton) head.findViewById(R.id.noodle);
        comboButton = (ImageButton) head.findViewById(R.id.combo);
//        drunkButton = (ImageButton) head.findViewById(R.id.drunk);
        initButton();
        Glide.with(getActivity()).load(R.drawable.menubackg)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        head.findViewById(R.id.mine).setBackground(resource);
                    }
                });
        testadp.setHeaderView(head);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(testadp);
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

    public void initdata() {
        foods = new ArrayList<>();
        Food food;
        for (int i = 0; i < 5; i++) {
            food = new Food();
            food.setFoodNumber(i + "");
            food.setFoodName(i + "xhs");
            food.setFoodPrice(12.1f);
            foods.add(food);
        }
    }

    //初始化所有head中的按钮
    public void initButton() {
        meatButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setAlpha(0.5f);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1f);
                }
                return false;
            }
        });
        meatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (meatF == null) {
                    meatF = new ListFragment("meat", "food");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, meatF, "meat")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, meatF, "meat")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        vegetableButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setAlpha(0.5f);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1f);
                }
                return false;
            }
        });
        vegetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vegetableF == null) {
                    vegetableF = new ListFragment("vegetable", "food");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, vegetableF, "vegetable")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, vegetableF, "vegetable")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        soupButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setAlpha(0.5f);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1f);
                }
                return false;
            }
        });
        soupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soupF == null) {
                    soupF = new ListFragment("soup", "food");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, soupF, "soup")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, soupF, "soup")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        noodleButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setAlpha(0.5f);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1f);
                }
                return false;
            }
        });
        noodleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noodleF == null) {
                    noodleF = new ListFragment("noodle", "food");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, noodleF, "noodle")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, noodleF, "noodle")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        comboButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setAlpha(0.5f);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1f);
                }
                return false;
            }
        });
        comboButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comboF == null) {
                    comboF = new ListFragment("combo", "combo");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, comboF, "combo")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.change_all, comboF, "combo")
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Title"))
                            .hide(getActivity().getSupportFragmentManager().findFragmentByTag("Content"))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
//        drunkButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    view.setAlpha(0.5f);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    view.setAlpha(1f);
//                }
//                return false;
//            }
//        });
//        drunkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toasty.warning(getActivity(), "!!!").show();
//            }
//        });
    }

    //提供测试数据
    public Food[] otherData() {
        Food[] customers = new Food[5];
        Food food;
        for (int i = 0; i < 5; i++) {
            food = new Food();
            food.setFoodName("i+sss");
            food.setFoodPrice(2.12f + i);
            customers[i] = food;
        }
        return customers;
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

    static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
        private final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };
        private Drawable mDivider;

        private int mOrientation;

        public DividerItemDecoration(Context context, int orientation) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent) {

            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }

        }


        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }
}
