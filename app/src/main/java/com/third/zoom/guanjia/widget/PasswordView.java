package com.third.zoom.guanjia.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.third.zoom.R;

/**
 * 作者：Sky on 2018/7/16.
 * 用途：密码view
 */

public class PasswordView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private Button btnCancel;
    private TextView txtInputPwd;
    private TextView txtPwd1,txtPwd2,txtPwd3,txtPwd4,txtPwd5,txtPwd6,txtPwd7,txtPwd8,txtPwd9,txtPwd0;

    private String DEFAULT_PASSWORD = "123456";

    private String passwordText = "";

    public PasswordView(Context context) {
        this(context, null);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        initData();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.gj_widget_password,this);
        txtInputPwd = (TextView) view.findViewById(R.id.txt_pwd_input);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        txtPwd0 = (TextView) view.findViewById(R.id.txt_pwd_0);
        txtPwd1 = (TextView) view.findViewById(R.id.txt_pwd_1);
        txtPwd2 = (TextView) view.findViewById(R.id.txt_pwd_2);
        txtPwd3 = (TextView) view.findViewById(R.id.txt_pwd_3);
        txtPwd4 = (TextView) view.findViewById(R.id.txt_pwd_4);
        txtPwd5 = (TextView) view.findViewById(R.id.txt_pwd_5);
        txtPwd6 = (TextView) view.findViewById(R.id.txt_pwd_6);
        txtPwd7 = (TextView) view.findViewById(R.id.txt_pwd_7);
        txtPwd8 = (TextView) view.findViewById(R.id.txt_pwd_8);
        txtPwd9 = (TextView) view.findViewById(R.id.txt_pwd_9);
    }

    private void initData() {
        txtPwd0.setOnClickListener(this);
        txtPwd1.setOnClickListener(this);
        txtPwd2.setOnClickListener(this);
        txtPwd3.setOnClickListener(this);
        txtPwd4.setOnClickListener(this);
        txtPwd5.setOnClickListener(this);
        txtPwd6.setOnClickListener(this);
        txtPwd7.setOnClickListener(this);
        txtPwd8.setOnClickListener(this);
        txtPwd9.setOnClickListener(this);
    }

    public void setCancelOnClickListener(OnClickListener onClickListener){
        btnCancel.setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_pwd_0:
                passwordText += "0";
                break;
            case R.id.txt_pwd_1:
                passwordText += "1";
                break;
            case R.id.txt_pwd_2:
                passwordText += "2";
                break;
            case R.id.txt_pwd_3:
                passwordText += "3";
                break;
            case R.id.txt_pwd_4:
                passwordText += "4";
                break;
            case R.id.txt_pwd_5:
                passwordText += "5";
                break;
            case R.id.txt_pwd_6:
                passwordText += "6";
                break;
            case R.id.txt_pwd_7:
                passwordText += "7";
                break;
            case R.id.txt_pwd_8:
                passwordText += "8";
                break;
            case R.id.txt_pwd_9:
                passwordText += "9";
                break;
        }

        if(!TextUtils.isEmpty(passwordText)){
            txtInputPwd.setText(passwordText);
            if(passwordText.length() == 6){
                if(passwordText.equals(DEFAULT_PASSWORD)){
                    passwordText = "";
                    txtInputPwd.setText(passwordText);
                    btnCancel.performClick();
                    toastError("密码正确");
                }else{
                    passwordText = "";
                    txtInputPwd.setText(passwordText);
                    toastError("密码错误，请重新输入");
                }
            }
        }
    }

    /**
     * toast
     */
    private void toastError(String error){
        Toast.makeText(context,error, Toast.LENGTH_LONG).show();
    }


}
