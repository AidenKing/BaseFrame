package com.king.lib.frame.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2018/8/6 16:16
 */
public abstract class HeaderFooterBindingAdapter<VH extends ViewDataBinding, VF extends ViewDataBinding, VI extends ViewDataBinding, T>
        extends HeaderFooterRecyclerAdapter<T> {

    @Override
    protected RecyclerView.ViewHolder onCreateHeader(ViewGroup parent) {
        VH binding = onCreateHeaderBind(LayoutInflater.from(parent.getContext()), parent);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        return holder;
    }

    protected abstract VH onCreateHeaderBind(LayoutInflater from, ViewGroup parent);

    @Override
    protected RecyclerView.ViewHolder onCreateFooter(ViewGroup parent) {
        VF binding = onCreateFooterBind(LayoutInflater.from(parent.getContext()), parent);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        return holder;
    }

    protected abstract VF onCreateFooterBind(LayoutInflater from, ViewGroup parent);

    @Override
    protected RecyclerView.ViewHolder onCreateItem(ViewGroup parent) {
        VI binding = onCreateItemBind(LayoutInflater.from(parent.getContext()), parent);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        return holder;
    }

    protected abstract VI onCreateItemBind(LayoutInflater from, ViewGroup parent);

    @Override
    protected void onBindHeaderView(RecyclerView.ViewHolder holder) {
        VH binding = DataBindingUtil.getBinding(holder.itemView);
        onBindHead(binding);
        binding.executePendingBindings();
    }

    protected abstract void onBindHead(VH binding);

    @Override
    protected void onBindFooterView(RecyclerView.ViewHolder holder) {
        VF binding = DataBindingUtil.getBinding(holder.itemView);
        onBindFooter(binding);
        binding.executePendingBindings();
    }

    protected abstract void onBindFooter(VF binding);

    @Override
    protected void onBindItemView(RecyclerView.ViewHolder holder, int position) {
        VI binding = DataBindingUtil.getBinding(holder.itemView);
        onBindItem(binding, position, list.get(position));
        binding.executePendingBindings();
    }

    protected abstract void onBindItem(VI binding, int position, T object);

    public static class BindingHolder extends RecyclerView.ViewHolder {

        public BindingHolder(View itemView) {
            super(itemView);
        }
    }
}
