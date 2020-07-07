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
 * @date: 2018/8/17 9:33
 */
public abstract class HeadChildBindingAdapter<VH extends ViewDataBinding, VI extends ViewDataBinding, H, I> extends RecyclerView.Adapter {

    private final int TYPE_HEAD = 0;
    private final int TYPE_ITEM = 1;

    protected List<Object> list;

    public void setList(List<Object> list) {
        this.list = list;
    }

    protected abstract Class getItemClass();

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getClass() == getItemClass()) {
            return TYPE_ITEM;
        }
        return TYPE_HEAD;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            VH binding = onCreateHeadBind(LayoutInflater.from(parent.getContext()), parent);
            BindingHolder holder = new BindingHolder(binding.getRoot());
            return holder;
        }
        else {
            VI binding = onCreateChildBind(LayoutInflater.from(parent.getContext()), parent);
            BindingHolder holder = new BindingHolder(binding.getRoot());
            return holder;
        }
    }

    protected abstract VH onCreateHeadBind(LayoutInflater from, ViewGroup parent);

    protected abstract VI onCreateChildBind(LayoutInflater from, ViewGroup parent);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEAD) {
            VH binding = DataBindingUtil.getBinding(holder.itemView);
            onBindHead(binding, position, (H) list.get(position));
            binding.executePendingBindings();
        }
        else {
            VI binding = DataBindingUtil.getBinding(holder.itemView);
            onBindItem(binding, position, (I) list.get(position));
            binding.executePendingBindings();
        }
    }

    protected abstract void onBindHead(VH binding, int position, H head);

    protected abstract void onBindItem(VI binding, int position, I item);

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();// 首尾分别为header和footer
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        public BindingHolder(View itemView) {
            super(itemView);
        }
    }
}
