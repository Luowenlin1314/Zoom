package com.third.zoom.guanjia.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.third.zoom.R;
import com.third.zoom.common.listener.BmvSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alienware on 2018/7/15.
 */

public class SelectHotWaterView extends RelativeLayout implements View.OnClickListener{

    private String[] waterNotice = {"50度水温适宜冲泡奶粉、蜂蜜等",
            "65度水温适宜冲泡生粉及芝麻糊等",
            "80度水温适宜冲泡醇香咖啡等",
            "95度水温适宜冲泡各种茶类等"};

    private int openResId = R.drawable.gj_shape_cir_textview_red;
    private int closeResId = R.drawable.gj_shape_cir_textview;

    private Context mContext;
    private ImageView imgBack;
    private TextView txtNotice;
    private TextView txtTH50;
    private TextView txtTH65;
    private TextView txtTH85;
    private TextView txtTH95;

    List<TextView> tabs = new ArrayList<TextView>();
    private int currentIndex = -1;
    BmvSelectListener bmvSelectListener;

    public SelectHotWaterView(Context context) {
        this(context, null);
    }

    public SelectHotWaterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectHotWaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initData();
    }

    private void initView(){
        View view = View.inflate(mContext, R.layout.gj_widget_select_hot,this);
        txtNotice = (TextView) view.findViewById(R.id.txt_notice);
        imgBack = (ImageView) findViewById(R.id.img_back);

        txtTH50 = (TextView) view.findViewById(R.id.txt_th_50);
        txtTH50.setTag(0);
        txtTH50.setOnClickListener(this);

        txtTH65 = (TextView) view.findViewById(R.id.txt_th_65);
        txtTH65.setTag(1);
        txtTH65.setOnClickListener(this);

        txtTH85 = (TextView) view.findViewById(R.id.txt_th_85);
        txtTH85.setTag(2);
        txtTH85.setOnClickListener(this);

        txtTH95 = (TextView) view.findViewById(R.id.txt_th_95);
        txtTH95.setTag(3);
        txtTH95.setOnClickListener(this);
    }

    private void initData(){
        tabs.add(txtTH50);
        tabs.add(txtTH65);
        tabs.add(txtTH85);
        tabs.add(txtTH95);
    }

    public void resetView(){

    }

    public void setBmvSelectListener(BmvSelectListener bmvSelectListener){
        this.bmvSelectListener = bmvSelectListener;
    }

    public void setImageBackOnClickListener(OnClickListener onClickListener){
        imgBack.setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < tabs.size(); i++) {
            TextView item = tabs.get(i);
            if(currentIndex == i){
                currentIndex = -1;
                item.setBackgroundResource(closeResId);
                if(bmvSelectListener != null){
                    bmvSelectListener.itemSelectClose(i);
                }
                if((Integer)v.getTag() == i){
                    return;
                }
            }
        }
        for (int i = 0; i < tabs.size(); i++) {
            TextView item = tabs.get(i);
            if((Integer)v.getTag() == i){
                item.setBackgroundResource(openResId);
                if(bmvSelectListener != null){
                    bmvSelectListener.itemSelectOpen(i);
                }
            }else{
                item.setBackgroundResource(closeResId);
            }
        }

    }

}