package com.third.zoom.guanjia.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.third.zoom.R;
import com.third.zoom.guanjia.adapter.AboutPageAdapter;

/**
 * 作者：Sky on 2018/7/16.
 * 用途：关于
 */

public class AboutGJView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private ViewPager viewPager;
    private LinearLayout circles;
    private SetPermissionView setPermissionView;
    private LinearLayout ll1,ll2,ll3,ll4,ll5,ll6;
    private TextView txtZh1,txtZh2,txtZh3,txtZh4,txtZh5,txtZh6;
    private TextView txtEh1,txtEh2,txtEh3,txtEh4,txtEh5,txtEh6;

    private int[] resId1 = {R.drawable.audio,R.drawable.audio,R.drawable.audio};
    private int[] resId2 = {R.drawable.copy,R.drawable.copy,R.drawable.copy};
    private int[] resId3 = {R.drawable.dir,R.drawable.dir,R.drawable.dir};
    private int[] resId4 = {R.drawable.excel,R.drawable.excel,R.drawable.excel};
    private int[] resId5 = {R.drawable.flv,R.drawable.flv,R.drawable.flv};
    private int[] resId6 = {R.drawable.gif,R.drawable.gif,R.drawable.gif};

    private AboutPageAdapter aboutPageAdapter;
    private int[] imgResIds;
    private ImageView[] tips;
    private ImageView imageView;

    public AboutGJView(Context context) {
        this(context, null);
    }

    public AboutGJView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AboutGJView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        initData();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.gj_widget_about, this);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        circles = (LinearLayout) view.findViewById(R.id.circles);
        setPermissionView = (SetPermissionView) view.findViewById(R.id.setPermissionView);
        imgBack = (ImageView) view.findViewById(R.id.img_back);
        ll1 = (LinearLayout) view.findViewById(R.id.ll1);
        ll2 = (LinearLayout) view.findViewById(R.id.ll2);
        ll3 = (LinearLayout) view.findViewById(R.id.ll3);
        ll4 = (LinearLayout) view.findViewById(R.id.ll4);
        ll5 = (LinearLayout) view.findViewById(R.id.ll5);
        ll6 = (LinearLayout) view.findViewById(R.id.ll6);
        txtZh1 = (TextView) view.findViewById(R.id.txt_gj_zh_1);
        txtZh2 = (TextView) view.findViewById(R.id.txt_gj_zh_2);
        txtZh3 = (TextView) view.findViewById(R.id.txt_gj_zh_3);
        txtZh4 = (TextView) view.findViewById(R.id.txt_gj_zh_4);
        txtZh5 = (TextView) view.findViewById(R.id.txt_gj_zh_5);
        txtZh6 = (TextView) view.findViewById(R.id.txt_gj_zh_6);
        txtEh1 = (TextView) view.findViewById(R.id.txt_gj_eh_1);
        txtEh2 = (TextView) view.findViewById(R.id.txt_gj_eh_2);
        txtEh3 = (TextView) view.findViewById(R.id.txt_gj_eh_3);
        txtEh4 = (TextView) view.findViewById(R.id.txt_gj_eh_4);
        txtEh5 = (TextView) view.findViewById(R.id.txt_gj_eh_5);
        txtEh6 = (TextView) view.findViewById(R.id.txt_gj_eh_6);
    }

    private void initData() {
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);

        viewPager.setOffscreenPageLimit(0);
        aboutPageAdapter = new AboutPageAdapter(context, imgResIds);
        viewPager.setAdapter(aboutPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imgResIds.length; i++) {
                    if (position == i) {
                        tips[position].setBackgroundResource(R.drawable.audio);
                    }else{
                        tips[i].setBackgroundResource(R.drawable.vedio);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ll1.performClick();
    }

    private ImageView imgBack;
    public void setImageBackOnClickListener(OnClickListener onClickListener){
        imgBack.setOnClickListener(onClickListener);
    }

    public void updateData(int[] imgRes){
        if(imgRes == null || imgRes.length== 0){
            return;
        }

        imgResIds = imgRes;
        initTips();

        aboutPageAdapter = new AboutPageAdapter(context, imgResIds);
        viewPager.setAdapter(aboutPageAdapter);
//        aboutPageAdapter.updateData(imgResIds);
    }

    private void initTips() {
        int allSize = imgResIds.length;
        tips = new ImageView[allSize];
        circles.removeAllViews();
        for (int i = 0; i < allSize; i++) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(50, 0, 50, 0);
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.audio);
            } else {
                tips[i].setBackgroundResource(R.drawable.vedio);
            }
            circles.addView(tips[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll1:
                changeView(1);
                imgResIds = resId1;
                changeTextColor(1);
                updateData(imgResIds);
                break;
            case R.id.ll2:
                changeView(2);
                imgResIds = resId2;
                changeTextColor(2);
                updateData(imgResIds);
                break;
            case R.id.ll3:
                changeView(3);
                imgResIds = resId3;
                changeTextColor(3);
                updateData(imgResIds);
                break;
            case R.id.ll4:
                changeView(4);
                imgResIds = resId4;
                changeTextColor(4);
                updateData(imgResIds);
                break;
            case R.id.ll5:
                changeView(5);
                imgResIds = resId5;
                changeTextColor(5);
                updateData(imgResIds);
                break;
            case R.id.ll6:
                changeTextColor(6);
                changeView(6);
                break;
        }
    }

    private void changeView(int position){
        if(position == 6){
            viewPager.setVisibility(GONE);
            circles.setVisibility(GONE);
            setPermissionView.setVisibility(VISIBLE);
        }else{
            viewPager.setVisibility(VISIBLE);
            circles.setVisibility(VISIBLE);
            setPermissionView.setVisibility(GONE);
        }
    }

    private void changeTextColor(int index){
        txtZh1.setTextColor(getResources().getColor(R.color.txt_normal));
        txtEh1.setTextColor(getResources().getColor(R.color.txt_normal));
        txtZh2.setTextColor(getResources().getColor(R.color.txt_normal));
        txtEh2.setTextColor(getResources().getColor(R.color.txt_normal));
        txtZh3.setTextColor(getResources().getColor(R.color.txt_normal));
        txtEh3.setTextColor(getResources().getColor(R.color.txt_normal));
        txtZh4.setTextColor(getResources().getColor(R.color.txt_normal));
        txtEh4.setTextColor(getResources().getColor(R.color.txt_normal));
        txtZh5.setTextColor(getResources().getColor(R.color.txt_normal));
        txtEh5.setTextColor(getResources().getColor(R.color.txt_normal));
        txtZh6.setTextColor(getResources().getColor(R.color.txt_normal));
        txtEh6.setTextColor(getResources().getColor(R.color.txt_normal));
        switch (index){
            case 1:
                txtZh1.setTextColor(getResources().getColor(R.color.txt_focus));
                txtEh1.setTextColor(getResources().getColor(R.color.txt_focus));
                break;
            case 2:
                txtZh2.setTextColor(getResources().getColor(R.color.txt_focus));
                txtEh2.setTextColor(getResources().getColor(R.color.txt_focus));
                break;
            case 3:
                txtZh3.setTextColor(getResources().getColor(R.color.txt_focus));
                txtEh3.setTextColor(getResources().getColor(R.color.txt_focus));
                break;
            case 4:
                txtZh4.setTextColor(getResources().getColor(R.color.txt_focus));
                txtEh4.setTextColor(getResources().getColor(R.color.txt_focus));
                break;
            case 5:
                txtZh5.setTextColor(getResources().getColor(R.color.txt_focus));
                txtEh5.setTextColor(getResources().getColor(R.color.txt_focus));
                break;
            case 6:
                txtZh6.setTextColor(getResources().getColor(R.color.txt_focus));
                txtEh6.setTextColor(getResources().getColor(R.color.txt_focus));
                break;
        }
    }

}
