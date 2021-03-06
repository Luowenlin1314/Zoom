package com.third.zoom.guanjia.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.third.zoom.R;
import com.third.zoom.common.listener.NormalListener;
import com.third.zoom.common.utils.PreferenceUtils;
import com.third.zoom.guanjia.adapter.AboutPageAdapter;
import com.third.zoom.guanjia.utils.Contans;
import com.third.zoom.guanjia.utils.IntentUtils;

/**
 * 作者：Sky on 2018/7/16.
 * 用途：关于
 */

public class AboutGJView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private ViewPager viewPager;
    private LinearLayout circles;
    private SetPermissionView setPermissionView;
    private ImageView imgPre,imgNext;
    private RelativeLayout rlPre;
    private RelativeLayout rlNext;
    private RelativeLayout ll1;
    private LinearLayout ll2,ll3,ll4,ll5,ll6,ll7;
    private TextView txtZh1,txtZh2,txtZh3,txtZh4,txtZh5,txtZh6,txtZh7;
    private TextView txtEh1,txtEh2,txtEh3,txtEh4,txtEh5,txtEh6,txtEh7;
    private ImageView imgDown;

    private int[] resId1 = {R.drawable.gj_about_tab1_1,R.drawable.gj_about_tab1_2,R.drawable.gj_about_tab1_3};
    private int[] resId2 = {R.drawable.gj_about_tab2_1,R.drawable.gj_about_tab2_2,R.drawable.gj_about_tab2_3,R.drawable.gj_about_tab2_4};
    private int[] resId3 = {R.drawable.gj_about_tab3_1,R.drawable.gj_about_tab3_2,R.drawable.gj_about_tab3_3,R.drawable.gj_about_tab3_4};
    private int[] resId4 = {R.drawable.gj_about_tab4_1,R.drawable.gj_about_tab4_2,R.drawable.gj_about_tab4_3};
    private int[] resId5 = {R.drawable.gj_about_tab5_1,R.drawable.gj_about_tab5_2,R.drawable.gj_about_tab5_3,R.drawable.gj_about_tab5_4};
    private int[] resId7 = {R.drawable.gj_about_tab5_1};

    private int[] resId11 = {R.drawable.gj_about_tab1_1_1};
    private int[] resId12 = {R.drawable.gj_about_tab1_1_3};
    private int[] resId13 = {R.drawable.gj_about_tab1_1_3_1,R.drawable.gj_about_tab1_1_3_2,
            R.drawable.gj_about_tab1_1_3_3,R.drawable.gj_about_tab1_1_3_4,};

    private AboutPageAdapter aboutPageAdapter;
    private int[] imgResIds;
    private ImageView[] tips;
    private ImageView imageView;

    private PayView payView;
    private SelectWaterDeviceV2View selectWaterDeviceV2View;

//    private RelativeLayout rlChangeDevice;
//    private ImageView imgChangeBack;
//    private ImageView imgTypeSelect;
//    private RelativeLayout rlPay;
//    private RelativeLayout rlTc;
//    private ImageView imgTypeA;
//    private ImageView imgTypeB;
//    private ImageView imgTypeC;
    private int selectFlag = 1;

    private int currentType = 1;

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
        selectWaterDeviceV2View = (SelectWaterDeviceV2View) view.findViewById(R.id.rl_device);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        circles = (LinearLayout) view.findViewById(R.id.circles);
        setPermissionView = (SetPermissionView) view.findViewById(R.id.setPermissionView);
        payView =  (PayView) view.findViewById(R.id.pawView);
        imgBack = (ImageView) view.findViewById(R.id.img_back);
        rlBack = (RelativeLayout) view.findViewById(R.id.rl_back);
        imgPre = (ImageView) view.findViewById(R.id.gj_about_img_pre);
        imgNext = (ImageView) view.findViewById(R.id.gj_about_img_next);
        rlPre = (RelativeLayout) view.findViewById(R.id.rl_about_pre);
        rlNext = (RelativeLayout) view.findViewById(R.id.rl_about_next);
        ll1 = (RelativeLayout) view.findViewById(R.id.ll1);
        ll2 = (LinearLayout) view.findViewById(R.id.ll2);
        ll3 = (LinearLayout) view.findViewById(R.id.ll3);
        ll4 = (LinearLayout) view.findViewById(R.id.ll4);
        ll5 = (LinearLayout) view.findViewById(R.id.ll5);
        ll6 = (LinearLayout) view.findViewById(R.id.ll6);
        ll7 = (LinearLayout) view.findViewById(R.id.ll7);
        txtZh1 = (TextView) view.findViewById(R.id.txt_gj_zh_1);
        txtZh2 = (TextView) view.findViewById(R.id.txt_gj_zh_2);
        txtZh3 = (TextView) view.findViewById(R.id.txt_gj_zh_3);
        txtZh4 = (TextView) view.findViewById(R.id.txt_gj_zh_4);
        txtZh5 = (TextView) view.findViewById(R.id.txt_gj_zh_5);
        txtZh6 = (TextView) view.findViewById(R.id.txt_gj_zh_6);
        txtZh7 = (TextView) view.findViewById(R.id.txt_gj_zh_7);
        txtEh1 = (TextView) view.findViewById(R.id.txt_gj_eh_1);
        txtEh2 = (TextView) view.findViewById(R.id.txt_gj_eh_2);
        txtEh3 = (TextView) view.findViewById(R.id.txt_gj_eh_3);
        txtEh4 = (TextView) view.findViewById(R.id.txt_gj_eh_4);
        txtEh5 = (TextView) view.findViewById(R.id.txt_gj_eh_5);
        txtEh6 = (TextView) view.findViewById(R.id.txt_gj_eh_6);
        txtEh7 = (TextView) view.findViewById(R.id.txt_gj_eh_7);
        imgDown = (ImageView) view.findViewById(R.id.img_about_down);
//        rlChangeDevice = (RelativeLayout) view.findViewById(R.id.rl_change_device);
////        imgDevice = (ImageView) view.findViewById(R.id.img_device);
//        imgChangeBack = (ImageView) view.findViewById(R.id.img_change_back);
//        rlPay = (RelativeLayout) view.findViewById(R.id.rl_pay);
//        imgTypeSelect = (ImageView) view.findViewById(R.id.img_tc_select);
//        rlTc = (RelativeLayout) view.findViewById(R.id.rl_tc);
//        imgTypeA = (ImageView) view.findViewById(R.id.img_tc_a);
//        imgTypeB = (ImageView) view.findViewById(R.id.img_tc_b);
//        imgTypeC = (ImageView) view.findViewById(R.id.img_tc_c);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonnts/wryh.ttf");
        txtZh1.setTypeface(typeface);
        txtZh2.setTypeface(typeface);
        txtZh3.setTypeface(typeface);
        txtZh4.setTypeface(typeface);
        txtZh5.setTypeface(typeface);
        txtZh6.setTypeface(typeface);
        txtZh7.setTypeface(typeface);
    }

    private void initData() {
        PreferenceUtils.init(context);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);
        ll7.setOnClickListener(this);

        updateTab();

        viewPager.setOffscreenPageLimit(0);
        aboutPageAdapter = new AboutPageAdapter(context, imgResIds);
        viewPager.setAdapter(aboutPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
                for (int i = 0; i < imgResIds.length; i++) {
                    if (position == i) {
                        tips[position].setBackgroundResource(R.drawable.gj_icon_circle_press);
                    }else{
                        tips[i].setBackgroundResource(R.drawable.gj_icon_cirlce);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rlPre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPre.performClick();
            }
        });
        rlNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imgNext.performClick();
            }
        });

        imgPre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = viewPager.getCurrentItem();
                int count = aboutPageAdapter.getCount();
                if(--index < 0){
                    index = count - 1;
                }
                viewPager.setCurrentItem(index);
            }
        });
        imgNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = viewPager.getCurrentItem();
                int count = aboutPageAdapter.getCount();
                if(++index >= count){
                    index = 0;
                }
                viewPager.setCurrentItem(index);
            }
        });

        permissionViewSetListener();

//        imgDevice.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                normalDialog2();
//                dialogShow2();
//            }
//        });

//        imgChangeBack.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rlTc.setVisibility(GONE);
//                rlPay.setVisibility(GONE);
//                rlChangeDevice.setVisibility(GONE);
//            }
//        });
//
//        imgTypeA.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectFlag = 1;
//                imgTypeA.setSelected(true);
//                imgTypeB.setSelected(false);
//                imgTypeC.setSelected(false);
//            }
//        });
//
//        imgTypeB.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectFlag = 2;
//                imgTypeA.setSelected(false);
//                imgTypeB.setSelected(true);
//                imgTypeC.setSelected(false);
//            }
//        });
//
//        imgTypeC.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectFlag = 3;
//                imgTypeA.setSelected(false);
//                imgTypeB.setSelected(false);
//                imgTypeC.setSelected(true);
//            }
//        });
//
//        imgTypeSelect.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rlTc.setVisibility(GONE);
//                rlPay.setVisibility(VISIBLE);
//            }
//        });
//
//        rlPay.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rlTc.setVisibility(GONE);
//                rlPay.setVisibility(GONE);
//                rlChangeDevice.setVisibility(GONE);
//            }
//        });

        setPermissionView.setBackListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow != null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                imgBack.performClick();
            }
        });

        payView.setBackListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupWindow != null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                imgBack.performClick();
            }
        });

        initLVTime();

        changeView(1);
        imgResIds = resId1;
        changeTextColor(1);
        updateData(imgResIds);
    }

    public void updateTab(){
        int waterMode = PreferenceUtils.getInt("waterMode",1);
        if(waterMode == 2){
            ll7.setVisibility(GONE);
        }else{
            ll7.setVisibility(VISIBLE);
        }
    }

    private RelativeLayout rlBack;
    private ImageView imgBack;
    public void setImageBackOnClickListener(OnClickListener onClickListener){
        imgBack.setOnClickListener(onClickListener);
        rlBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow != null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                imgBack.performClick();
            }
        });
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
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.gj_icon_circle_press);
            } else {
                tips[i].setBackgroundResource(R.drawable.gj_icon_cirlce);
            }
            circles.addView(tips[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll1:
//                changeView(1);
//                imgResIds = resId1;
//                changeTextColor(1);
//                updateData(imgResIds);
                popupWindowView();
                activePopupWindow(v);
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
            case R.id.ll7:
                imgResIds = resId7;
                changeTextColor(7);
                changeView(7);
                updateData(imgResIds);
                break;
            case R.id.ll6:
                changeTextColor(6);
                changeView(6);
                break;
        }
}

    private void changeView(int position){
        IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
        if(position == 6){
            imgPre.setVisibility(GONE);
            imgNext.setVisibility(GONE);
            viewPager.setVisibility(GONE);
            circles.setVisibility(GONE);
            payView.setVisibility(GONE);
            setPermissionView.setVisibility(VISIBLE);
        }else if(position == 7){
            imgPre.setVisibility(GONE);
            imgNext.setVisibility(GONE);
            viewPager.setVisibility(GONE);
            circles.setVisibility(GONE);
            setPermissionView.setVisibility(GONE);
            payView.setVisibility(VISIBLE);
        }else{
            imgPre.setVisibility(VISIBLE);
            imgNext.setVisibility(VISIBLE);
            viewPager.setVisibility(VISIBLE);
            circles.setVisibility(VISIBLE);
            payView.setVisibility(GONE);
            setPermissionView.setVisibility(GONE);
        }
    }

    private void changeTextColor(int index){
        txtZh1.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtEh1.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtZh2.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtEh2.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtZh3.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtEh3.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtZh4.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtEh4.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtZh5.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtEh5.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtZh6.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtEh6.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtZh7.setTextColor(getResources().getColor(R.color.txt_normal2));
        txtEh7.setTextColor(getResources().getColor(R.color.txt_normal2));
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
            case 7:
                txtZh7.setTextColor(getResources().getColor(R.color.txt_focus));
                txtEh7.setTextColor(getResources().getColor(R.color.txt_focus));
                break;
        }
    }

    private String[] txtRes = {"水机说明","安全责任"};
    private int resIndex = 0;
    private PopupWindow popupWindow;
    private void popupWindowView(){
        View view = View.inflate(context,R.layout.gj_widget_popup,null);
        TextView txt1 = (TextView) view.findViewById(R.id.txt_popup_1);
        txt1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activePopupWindow(view);
                resIndex = 0;
                changePopText(resIndex);
            }
        });
        if(resIndex == 0){
            txt1.setTextColor(context.getResources().getColor(R.color.txt_tab_normal_2));
        }else{
            txt1.setTextColor(context.getResources().getColor(R.color.txt_black));
        }
        TextView txt2 = (TextView) view.findViewById(R.id.txt_popup_4);
        txt2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activePopupWindow(view);
                resIndex = 1;
                changePopText(resIndex);
            }
        });
        if(resIndex == 1){
            txt2.setTextColor(context.getResources().getColor(R.color.txt_tab_normal_2));
        }else{
            txt2.setTextColor(context.getResources().getColor(R.color.txt_black));
        }
//        TextView txt3 = (TextView) view.findViewById(R.id.txt_popup_3);
//        txt3.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activePopupWindow(view);
//                resIndex = 2;
//                changePopText(resIndex);
//            }
//        });
//        if(resIndex == 2){
//            txt3.setTextColor(context.getResources().getColor(R.color.txt_tab_normal_2));
//        }else{
//            txt3.setTextColor(context.getResources().getColor(R.color.txt_black));
//        }
//        TextView txt4 = (TextView) view.findViewById(R.id.txt_popup_4);
//        txt4.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activePopupWindow(view);
//                resIndex = 3;
//                changePopText(resIndex);
//            }
//        });
//        if(resIndex == 3){
//            txt4.setTextColor(context.getResources().getColor(R.color.txt_tab_normal_2));
//        }else{
//            txt4.setTextColor(context.getResources().getColor(R.color.txt_black));
//        }
        popupWindow = new PopupWindow(view, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        //设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00FFFFFF));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setTouchInterceptor(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

    }

    private void activePopupWindow(View view){
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }else{
            popupWindow.showAsDropDown(view);
        }
    }

    private void changePopText(int index){
        changeView(1);

        changeTextColor(1);
        txtZh1.setText(txtRes[index]);
        if(index == 0){
            imgResIds = resId1;
        }else if(index == 1){
            imgResIds = resId13;
        }else if(index == 2){
            imgResIds = resId13;
        }else if(index == 3){
            imgResIds = resId13;
        }
        updateData(imgResIds);
    }

    private void permissionViewSetListener(){
        setPermissionView.setNormalListener(new NormalListener() {
            @Override
            public void onActive(Object object) {
                if((int)object == 1){
//                    resId12[0] = R.drawable.gj_about_tab1_1_4;
//                    resIndex = 2;
//                    changePopText(2);
                    PreferenceUtils.init(context);
                    PreferenceUtils.commitLong("waterTime",System.currentTimeMillis());
                    Intent toLv = new Intent(Contans.INTENT_GJ_ACTION_LV_SET);
                    context.sendBroadcast(toLv);
                }else if((int)object == 2){
                    int waterMode = PreferenceUtils.getInt("waterMode",1);
                    selectWaterDeviceV2View.initViewData();
                    selectWaterDeviceV2View.setVisibility(VISIBLE);
                    if(waterMode == 1){
//                        rlChangeDevice.setVisibility(VISIBLE);
//                        rlTc.setVisibility(VISIBLE);
//                        imgTypeA.performClick();
//                        int waterType = PreferenceUtils.getInt("waterType",1);
//                        if(waterType == 2){
//                            currentType = 1;
//                            imgDevice.setImageResource(R.drawable.gj_device_a);
//                        }else if(waterType == 1){
//                            currentType = 2;
//                            imgDevice.setImageResource(R.drawable.gj_device_b);
//                        }
                    }else{
//                        selectFlag = 1;
                    }
                }
            }
        });
    }

    private AlertDialog normalDialog2;
    private void normalDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View capView = View.inflate(context,R.layout.gj_widget_notice,null);
        TextView txtMessage = (TextView) capView.findViewById(R.id.txt_message);
        if(currentType == 1){
            txtMessage.setText("您选择的是共享机");
        }else{
            txtMessage.setText("您选择的是家庭机");
        }
        Button btnCancel = (Button) capView.findViewById(R.id.btn_cancel);
        Button btnOk = (Button) capView.findViewById(R.id.btn_ok);
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDismiss2();
            }
        });
        btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDismiss2();
                if(normalListener != null){
                    normalListener.onActive(1);
                }
                PreferenceUtils.commitInt("waterType",currentType);
                PreferenceUtils.commitLong("waterTime",System.currentTimeMillis());
//                rlChangeDevice.setVisibility(GONE);
                imgBack.performClick();
            }
        });
        builder.setView(capView);

        normalDialog2 = builder.create();
    }

    private void dialogShow2(){
        if(!normalDialog2.isShowing()){
            normalDialog2.show();
            Window dialogWindow = normalDialog2.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
            lp.width = (int) (d.widthPixels * 0.5); // 宽度设置为屏幕的0.8
//            lp.height = (int) (d.heightPixels * 0.5); // 宽度设置为屏幕的0.8
            dialogWindow.setAttributes(lp);
        }
    }

    private void dialogDismiss2(){
        if(normalDialog2.isShowing()){
            normalDialog2.dismiss();
        }
    }

    private NormalListener normalListener;
    public void setNormalListener(NormalListener normalListener){
        this.normalListener = normalListener;
    }

    public void setLVTime(int type ,int lvTime){
        if(type == 2){
           if(lvTime > 120){
               resId12[0] = R.drawable.gj_about_tab1_1_4;
               setPermissionView.setLVTime(1,(float) lvTime / (float)180);
           }else if(lvTime > 60){
               resId12[0] = R.drawable.gj_about_tab1_1_2;
               setPermissionView.setLVTime(2,(float)lvTime/ (float)180);
           }else{
               resId12[0] = R.drawable.gj_about_tab1_1_3;
               setPermissionView.setLVTime(3,(float)lvTime / (float)180);
           }
        }else{
            if(lvTime > 80){
                resId12[0] = R.drawable.gj_about_tab1_1_4;
                setPermissionView.setLVTime(1,(float)lvTime/ (float)120);
            }else if(lvTime > 40){
                resId12[0] = R.drawable.gj_about_tab1_1_2;
                setPermissionView.setLVTime(2,(float)lvTime/ (float)120);
            }else{
                resId12[0] = R.drawable.gj_about_tab1_1_3;
                setPermissionView.setLVTime(3,(float)lvTime/ (float)120);
            }
        }
    }

    private void initLVTime(){
        long curnTime = System.currentTimeMillis();
        long indexTime = curnTime - PreferenceUtils.getLong("waterTime",0);
        int last = (int) (indexTime / (24 * 60 * 60 * 1000));
        if(last < 0 || last > 180){
            last = 0;
        }
        setLVTime(currentType,180 - last);

    }

}
