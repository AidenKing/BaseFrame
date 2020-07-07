package com.king.lib.frame.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2018/6/7 16:50
 */
public abstract class BindingAdapter<V extends ViewDataBinding, T> extends RecyclerView.Adapter {

    protected List<T> list;

    protected BaseRecyclerAdapter.OnItemClickListener<T> onItemClickListener;

    protected BaseRecyclerAdapter.OnItemLongClickListener<T> onItemLongClickListener;

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(BaseRecyclerAdapter.OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        V binding = onCreateBind(LayoutInflater.from(parent.getContext()), parent);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.itemView.setOnClickListener(v -> {
            int position = holder.getLayoutPosition();
            onClickItem(v, position);
        });
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(v -> {
                int position = holder.getLayoutPosition();
                onItemLongClickListener.onLongClickItem(v, position, list.get(position));
                return true;
            });
        }
        return holder;
    }

    protected abstract V onCreateBind(LayoutInflater from, ViewGroup parent);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        V binding = getBindingFromHolder(holder);
        onBindItem(binding, position, list.get(position));
        binding.executePendingBindings();
    }

    protected V getBindingFromHolder(RecyclerView.ViewHolder holder) {
        return DataBindingUtil.getBinding(holder.itemView);
    }

    protected abstract void onBindItem(V binding, int position, T bean);

    protected void onClickItem(View v, int position) {
        if (onItemClickListener != null) {
            onItemClickListener.onClickItem(v, position, list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        public BindingHolder(View itemView) {
            super(itemView);
        }
    }
}
