package com.example.ggq.listviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Recycle extends AppCompatActivity {

    private RecyclerView rv;
    private HashMap<String, ArrayList<String>> listview;
    private ArrayList<Integer> pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        rv = (RecyclerView) findViewById(R.id.recycleview);
        rv.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        initView();
        rv.setAdapter(new MyRecyclerAdapter(this, listview, pic, 1));
        rv.addItemDecoration(new DividerItemDecoration(this, android.R.attr.subtypeExtraValue));
    }

    public void initView() {
        listview = new HashMap<>();
        ArrayList<String> l = null;
        l = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            l.add("i'm " + i);

        }
        listview.put("textview1", l);
        l = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            l.add(i + "@@@");
        }
        listview.put("textview2", l);
        pic = new ArrayList<>();
        pic.add(R.mipmap.ic_launcher1);
        pic.add(R.mipmap.ic_launcher2);
        pic.add(R.mipmap.ic_launcher3);
    }

    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
        private Context context;
        private HashMap<String, ArrayList<String>> map;
        private LayoutInflater inflater;
        private ArrayList<Integer> pic;
        private int textviewNum;
        ArrayList<String> ll;
        ArrayList<String> l2;


        public MyRecyclerAdapter(Context context, HashMap<String, ArrayList<String>> list, ArrayList<Integer> p
                , int num) {
            this.context = context;
            this.map = list;
            this.pic = p;
            inflater = LayoutInflater.from(context);
            this.textviewNum = num;
            ll = map.get("textview1");
            l2 = map.get("textview2");
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.imageView.setImageResource(pic.get(position));

            holder.textView1.setText(ll.get(position));

            holder.textView2.setText(l2.get(position));
        }

        @Override
        public int getItemCount() {
            return pic.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView1;
            private TextView textView2;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                textView1 = (TextView) itemView.findViewById(R.id.textview1);
                textView2 = (TextView) itemView.findViewById(R.id.textview2);
            }
        }
    }

    class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

        private Drawable mDivider;

        private int mOrientation;

        public DividerItemDecoration(Context context, int orientation) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(VERTICAL_LIST);
        }

        public void setOrientation(int orientation) {
//            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
//                throw new IllegalArgumentException("invalid orientation");
//            }
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
