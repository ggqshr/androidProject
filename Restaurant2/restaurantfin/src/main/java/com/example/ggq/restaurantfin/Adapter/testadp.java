package com.example.ggq.restaurantfin.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ggq.restaurantfin.BR;
import com.example.ggq.restaurantfin.MainActivity;
import com.example.ggq.restaurantfin.R;
import com.example.ggq.restaurantfin.databinding.ItemBinding;
import com.example.ggq.restaurantfin.entity.Combo;
import com.example.ggq.restaurantfin.entity.Food;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by ggq on 2017/5/24.
 */
public class testadp extends RecyclerView.Adapter<testadp.ViewHolder> {
    //用来记录是否为head
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private ArrayList<Food> mdate;
    private ArrayList<Combo> combos;
    private String type;
    private Context context;
    //head 的 view
    private View mHeaderView;
    private OnItemClickListener mListener;
    private String baseUrl = "http://192.168.191.1:8080/Hotpotserver/img";

    //构造函数
    public testadp(Context context, ArrayList<Food> muser, ArrayList<Combo> c, String type) {
        this.context = context;
        this.mdate = muser;
        this.combos = c;
        this.type = type;
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
    public testadp.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果为head就直接返回一个head的viewholder
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        ItemBinding ab = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item, parent, false
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
    public void onBindViewHolder(final testadp.ViewHolder holder, int position) {
        //如果是head不用设置其控件的属性
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);
        if (type.equals("food")) {
            final Food user = mdate.get(pos);
            Log.d("@@",baseUrl + user.getFoodPhoto() + ".jpg");
            Glide.with(context)
                    .load(baseUrl + user.getFoodPhoto() + ".jpg")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.image_not_upload)
                    .into(holder.binding.foodPhoto);
            holder.binding.setVariable(BR.food, user);
            holder.binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toasty.success(context, "添加购物车成功", 1000).show();
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.addToFoodCart(user);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toasty.success(context, user.getFoodPrice() + "").show();
                }
            });
            holder.binding.executePendingBindings();
        } else {
            final Combo combo = combos.get(pos);
            Glide.with(context)
                    .load(baseUrl + combo.getComboPhoto() + ".jpg")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.image_not_upload)
                    .into(holder.binding.foodPhoto);
            holder.binding.setCombo(combo);
            holder.binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.addToComboCart(combo);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toasty.success(context, combo.getComboPrice() + "").show();
                }
            });
            holder.binding.executePendingBindings();
        }

    }

    @Override
    public int getItemCount() {
        if (type.equals("food"))
            return mHeaderView == null ? mdate.size() : mdate.size() + 1;
        else
            return mHeaderView == null ? combos.size() : combos.size() + 1;
    }

    interface OnItemClickListener {
        void onItemClick(int position, String data);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding binding;

        ViewHolder(View v) {
            super(v);
            if (itemView == mHeaderView) return;
        }

        public ItemBinding getBinding() {
            return binding;
        }

        void setBinding(ItemBinding binding) {
            this.binding = binding;
        }
    }
}
