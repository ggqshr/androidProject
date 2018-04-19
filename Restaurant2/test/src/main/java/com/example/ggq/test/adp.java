package com.example.ggq.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adp extends RecyclerView.Adapter<adp.ViewHold>
{
    private Context context;
    private ArrayList<Object> list_value;
    private OnItemClickLitener onItemClickLitener ;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    public void addData(int position) {
        list_value.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        list_value.remove(position);
        notifyItemRemoved(position);
    }
    ArrayList<Integer> mHeights;
    public adp(Context context , ArrayList<Object> arrayList) {
        this.context = context;
        this.list_value = arrayList;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < list_value.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
    }

    @Override
    public adp.ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHold viewHold = new ViewHold(LayoutInflater.from(context)
                .inflate(R.layout.item,parent,false));
        return viewHold;
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.onItemClickLitener = mOnItemClickLitener;
    }
    @Override
    public void onBindViewHolder(final adp.ViewHold holder, int position) {
        ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.textView.setLayoutParams(lp);
        holder.textView.setText((String)list_value.get(position));
        if(onItemClickLitener!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickLitener.onItemClick(holder.itemView,pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickLitener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list_value.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHold(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.Text_view);
        }
    }
}