package com.third.zoom.guanjia.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.third.zoom.R;
import com.third.zoom.guanjia.utils.Contans;
import com.third.zoom.guanjia.utils.IntentUtils;

/**
 * 作者：Sky on 2018/7/16.
 * 用途：设置权限view
 */

public class SetPermissionView extends RelativeLayout{

    private Context context;
    private LinearLayout permissionRoot;
    private Button btnTime;
    private Button btnPassword;


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
    }

    private void initData() {
        btnTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
                dialogShow();
            }
        });

        btnPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
                dialogShow();
            }
        });

//        passwordView.setCancelOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
//                permissionRoot.setVisibility(VISIBLE);
//                passwordView.setVisibility(GONE);
//            }
//        });

//        setTimeView.setCancelOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentUtils.sendBroadcast(context, Contans.INTENT_GJ_ACTION_ACTIVE);
//                permissionRoot.setVisibility(VISIBLE);
//                passwordView.setVisibility(GONE);
//                setTimeView.setVisibility(GONE);
//            }
//        });

        normalDialog();
        normalDialog2();
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
                    dialogShow2();
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
    private void normalDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View capView = View.inflate(context,R.layout.gj_widget_lx_change,null);
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
            dialogWindow.setAttributes(lp);
        }
    }

    private void dialogDismiss2(){
        if(normalDialog2.isShowing()){
            normalDialog2.dismiss();
        }
    }

}
