package com.third.zoom.guanjia.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.third.zoom.R;
import com.third.zoom.common.listener.NormalListener;
import com.third.zoom.common.utils.PreferenceUtils;
import com.third.zoom.guanjia.activity.WifiActivity;
import com.third.zoom.guanjia.utils.Contans;
import com.third.zoom.guanjia.utils.FileUtil;
import com.third.zoom.guanjia.utils.IntentUtils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 作者：Sky on 2018/7/16.
 * 用途：设置权限view
 */

public class SetPermissionView extends RelativeLayout{

    private Context context;
    private LinearLayout permissionRoot;
    private Button btnTime;
    private Button btnPassword;
    private Button btnWifi;
    private Button btnMac;
    private ImageView imgBack;

    private int type;

    public SetPermissionView(Context context) {
        this(context, null);
    }

    public SetPermissionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SetPermissionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        initData();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.gj_widget_permission,this);
        permissionRoot = (LinearLayout) view.findViewById(R.id.permission_root);
        btnTime = (Button) view.findViewById(R.id.btn_time);
        btnPassword = (Button) view.findViewById(R.id.btn_password);
        btnWifi = (Button) view.findViewById(R.id.btn_wifi);
        btnMac = (Button) view.findViewById(R.id.btn_mac);
        imgBack = (ImageView) view.findViewById(R.id.img_change_back);
    }

    private void initData() {
        PreferenceUtils.init(context);
        btnTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
                type = 1;
                dialogShow();
            }
        });

        btnPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
                type = 2;
                dialogShow();
            }
        });

        btnWifi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent toSetting = new Intent(Settings.ACTION_WIFI_SETTINGS);
                Intent toSetting =  new Intent(context, WifiActivity.class);
                toSetting.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(toSetting);
            }
        });

        macInit();
//        passwordView.setCancelOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
//                permissionRoot.setVisibility(VISIBLE);
//                passwordView.setVisibility(GONE);
//            }
//        });

        normalDialog();

    }

    private void macInit(){
//        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), FileUtil.QR_PATH);
//        //本地文件
//        if(file != null && file.exists()){
//            btnMac.setVisibility(GONE);
//        }else{
            btnMac.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
                    type = 3;
                    dialogShow();
                }
            });
//        }

    }

    private void initMacDialog(){
        MacDialog macDialog = new MacDialog(getContext(),R.style.dialog_download);
        if(!macDialog.isShowing()){
            macDialog.show();
        }
    }

    private AlertDialog normalDialog;
    private void normalDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final PasswordView passwordView = new PasswordView(context);
        passwordView.setCancelOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordView.isHasEdit()){
                    dialogDismiss();
                }
            }
        });
        passwordView.setOkOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordView.isPwdTrue()){
                    dialogDismiss();
                    if(type == 1){
                        normalDialog2();
                        dialogShow2();
                    }else if(type == 2){
                        if(normalListener != null){
                            normalListener.onActive(2);
                        }
                    }else if(type == 3){
                        initMacDialog();
                    }
                }
            }
        });
        builder.setView(passwordView);

        normalDialog = builder.create();
    }

    private void dialogShow(){
        if(!normalDialog.isShowing()){

            normalDialog.show();
            Window dialogWindow = normalDialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
            lp.width = (int) (d.widthPixels * 0.5); // 宽度设置为屏幕的0.8
            dialogWindow.setAttributes(lp);
        }
    }

    private void dialogDismiss(){
        if(normalDialog.isShowing()){
            normalDialog.dismiss();
        }
    }


    private AlertDialog normalDialog2;
    private  TextView txtPercent;
    private void normalDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View capView = View.inflate(context,R.layout.gj_widget_lx_change,null);
        txtPercent = (TextView) capView.findViewById(R.id.txt_percent);
        Button btnCancel = (Button) capView.findViewById(R.id.btn_cancel);
        Button btnOk = (Button) capView.findViewById(R.id.btn_ok);
        ImageView imgCap = (ImageView) capView.findViewById(R.id.img_cap);
        imgCap.setImageResource(resId);
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
            }
        });
        builder.setView(capView);

        normalDialog2 = builder.create();
    }

    private void dialogShow2(){
        if(!normalDialog2.isShowing()){
            if(txtPercent != null){
                txtPercent.setText(lvTime + "%");
            }
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

    private int resId = R.drawable.gj_widget_permission_lx_change;
    private String lvTime = "";
    DecimalFormat df = new DecimalFormat("0");
    public void setLVTime(int type,float lvTime){
        if(lvTime < 0){
            lvTime = 0;
        }else if(lvTime > 1){
            lvTime = 1;
        }
        this.lvTime = df.format(lvTime * 100);
        if(type == 1){
            resId = R.drawable.gj_widget_permission_lx_change2;
        }else if(type == 2){
            resId = R.drawable.gj_widget_permission_lx_change3;
        }else{
            resId = R.drawable.gj_widget_permission_lx_change;
        }
    }

    public void setBackListener(OnClickListener onClickListener){
        imgBack.setOnClickListener(onClickListener);
    }


}
