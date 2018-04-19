package com.example.ggq.restaurantfin.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ggq.restaurantfin.MainActivity;
import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.databinding.CartFoodItemBinding;
import com.example.ggq.restaurantfin.entity.Combo;
import com.example.ggq.restaurantfin.entity.Food;

import java.util.ArrayList;

/**
 * Created by ggq on 2017/5/26.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    //用来记录是否为head
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private ArrayList<Food> mdate;
    private ArrayList<Combo> cdata;
    private Context context;
    //head 的 view
    private View mHeaderView = null;
    private OnItemClickListener mListener;
    private String baseUrl = "http://192.168.191.1:8080/Hotpotserver/img";

    //构造函数
    public CartAdapter(Context context, ArrayList<Food> muser) {
        this.context = context;
        this.mdate = muser;
    }

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    //在recycleview绘画图形的时候提供head
    public View getHeaderView() {
        return mHeaderView;
    }

    //对外提供设置head的方法
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void addDatas(ArrayList<Food> datas) {
        mdate.addAll(datas);
        notifyDataSetChanged();
    }

    //用来判断是head还是普通的视图
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果为head就直接返回一个head的viewholder
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        CartFoodItemBinding ab = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cart_food_item, parent, false
        );
        ViewHolder viewHolder = new ViewHolder(ab.getRoot());
        viewHolder.setBinding(ab);
        return viewHolder;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        //用来返回真实的position，因为当我们没有设置head的时候总的数量会减1
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //如果是head不用设置其控件的属性
        if (getItemViewType(position) == TYPE_HEADER) return;
        final MainActivity mainActivity = (MainActivity) context;
        final int pos = getRealPosition(holder);
        final Food user = mdate.get(pos);
        Glide.with(context)
                .load(baseUrl + user.getFoodPhoto() + ".jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.drawable.image_not_upload)
                .into(holder.binding.cartFoodPhoto);
        holder.binding.setFood(user);
        holder.binding.cartFoodDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getLayoutPosition();
                mainActivity.setCartPrice(mainActivity.getCartPrice() - user.getFoodPrice() * user.getFoodNum());
                removeData(pos);
            }
        });
        holder.binding.cartFoodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = user.getFoodNum();
                int pos = holder.getLayoutPosition();
                num++;
                mainActivity.setCartPrice(mainActivity.getCartPrice() + user.getFoodPrice());
                user.setFoodNum(num);
                notifyItemChanged(pos);
            }
        });
        holder.binding.cartFoodDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getLayoutPosition();
                int num = user.getFoodNum();
                num--;
                mainActivity.setCartPrice(mainActivity.getCartPrice() - user.getFoodPrice());
                if (num == 0) {
                    removeData(pos);
                } else {
                    user.setFoodNum(num);
                    notifyItemChanged(pos);
                }
            }
        });
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mdate.size() : mdate.size() + 1;
    }

    public void removeData(int position) {
        mdate.remove(position);
        notifyItemRemoved(position);
    }

    interface OnItemClickListener {
        void onItemClick(int position, String data);
    }
//    public void addData(int position) {
//        mdate.add(position,);
//        notifyItemInserted(position);
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CartFoodItemBinding binding;

        ViewHolder(View v) {
            super(v);
            if (itemView == mHeaderView) return;
        }

        public CartFoodItemBinding getBinding() {
            return binding;
        }

        void setBinding(CartFoodItemBinding binding) {
            this.binding = binding;
        }
    }
}

