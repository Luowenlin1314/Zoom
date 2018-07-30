package com.third.zoom.guanjia.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.third.zoom.R;
import com.third.zoom.common.base.ActivityFragmentInject;
import com.third.zoom.common.base.BaseActivity;
import com.third.zoom.common.listener.BmvSelectListener;
import com.third.zoom.common.serial.SerialInterface;
import com.third.zoom.common.utils.SystemUtil;
import com.third.zoom.guanjia.utils.Contans;
import com.third.zoom.guanjia.utils.GJProUtil;
import com.third.zoom.guanjia.utils.IntentUtils;
import com.third.zoom.guanjia.widget.AboutGJView;
import com.third.zoom.guanjia.widget.MainView;
import com.third.zoom.guanjia.widget.SelectHotWaterView;
import com.third.zoom.guanjia.widget.WaitingView;

import java.util.Timer;
import java.util.TimerTask;

import static com.third.zoom.guanjia.utils.Contans.INTENT_GJ_ACTION_ACTIVE;

/**
 * 作者：Sky on 2018/7/13.
 * 用途：//TODO
 */
@ActivityFragmentInject(
        contentViewId = R.layout.gh_activity_main,
        hasNavigationView = false,
        hasToolbar = false
)
/**
 * 1、获取数据、获取成功2，获取不成功，重复获取
 * 2、同步本地状态
 * 3、点击发送协议、如果返回CC，表示发送成功、如果没返回，100ms后继续发送、重复3次，3次不行，提示错误
 *
 */
public class MainActivity extends BaseActivity {

    private static final int WHAT_OPEN_SERIAL = 9;
    private static final int WHAT_NOT_OPERATION = 10;
    private static final int WHAT_NOT_OPERATION_1 = 11;
    private static final int WHAT_NOT_OPERATION_2 = 12;
    private static final int WHAT_PRO_HANDLER = 13;
    private static final int WHAT_DIALOG_DISMISS = 14;
    private static final long DEFAULT_TIME = 3 * 60 * 1000;
    private static final long DEFAULT_TIME_1 = 1 * 60 * 1000;
    private static final long DEFAULT_TIME_2 = 2 * 60 * 1000;

    private MainView mainView;
    private SelectHotWaterView selectHotWaterView;
    private AboutGJView aboutGJView;
    private WaitingView waitingView;

    @Override
    protected void toHandleMessage(Message msg) {
        switch (msg.what) {
            case WHAT_OPEN_SERIAL:
                openSerial();
                break;
            case WHAT_NOT_OPERATION:
                operation(false);
                break;
            case WHAT_NOT_OPERATION_1:
                operation1();
                break;
            case WHAT_NOT_OPERATION_2:
                operation2();
                break;
            case WHAT_PRO_HANDLER:
                proTimerHandler();
                break;
            case WHAT_DIALOG_DISMISS:
                dialogDismiss();
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterGJProReceiver();
//        SerialInterface.closeAllSerialPort();
    }

    @Override
    protected void findViewAfterViewCreate() {
        mainView = (MainView) findViewById(R.id.mainView);
        selectHotWaterView = (SelectHotWaterView) findViewById(R.id.selectHotWaterView);
        aboutGJView = (AboutGJView) findViewById(R.id.aboutView);
        waitingView = (WaitingView) findViewById(R.id.waitingView);
    }

    @Override
    protected void initDataAfterFindView() {
        registerGJProReceiver();

//        SerialInterface.serialInit(this);
//        mHandler.sendEmptyMessageDelayed(WHAT_OPEN_SERIAL,1500);

        mainView.setBmvListener(new BmvSelectListener() {
            @Override
            public void itemSelectOpen(int position) {
                Log.e("ZM", "itemSelectOpen = " + position);
                sendActiveAction();
                mainView.setPositionShow(position);
                if (position == 0 || position == 2) {
                    sendPro(true, GJProUtil.DEFAULT_NORMAL_WATER_TH,
                            GJProUtil.DEFAULT_WATER_ML);
                } else if (position == 3) {
                    mainView.setVisibility(View.GONE);
                    aboutGJView.setVisibility(View.VISIBLE);
                    mainView.getBmvItem(3).performClick();
                }
            }

            @Override
            public void itemSelectClose(int position) {
                sendActiveAction();
                Log.e("ZM", "itemSelectClose = " + position);
                mainView.updateShow(0);
                if (position == 0 || position == 2) {
                    sendPro(false, GJProUtil.DEFAULT_NORMAL_WATER_TH,
                            GJProUtil.DEFAULT_WATER_ML);
                }
            }
        });

        mainView.setHotWaterClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActiveAction();
                mainView.setVisibility(View.GONE);
                selectHotWaterView.setVisibility(View.VISIBLE);
                mainView.getBmvItem(1).performClick();
            }
        });

        selectHotWaterView.setBmvSelectListener(new BmvSelectListener() {
            @Override
            public void itemSelectOpen(int position) {
                sendActiveAction();
                Log.e("ZM", "itemSelectOpen = " + position);
                sendPro(false, GJProUtil.getWaterThByPosition(position),
                        GJProUtil.DEFAULT_WATER_ML);
            }

            @Override
            public void itemSelectClose(int position) {
                sendActiveAction();
                Log.e("ZM", "itemSelectClose = " + position);
                sendPro(false, GJProUtil.getWaterThByPosition(position),
                        GJProUtil.DEFAULT_WATER_ML);
            }
        });

        selectHotWaterView.setImageBackOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActiveAction();
                selectHotWaterView.resetView();
                mainView.setVisibility(View.VISIBLE);
                selectHotWaterView.setVisibility(View.GONE);
                mainView.updateShow(0);
            }
        });

        aboutGJView.setImageBackOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActiveAction();
                mainView.setVisibility(View.VISIBLE);
                aboutGJView.setVisibility(View.GONE);
                mainView.updateShow(0);
            }
        });

        runOperationTimer();

        normalDialog();
    }


    /**
     * 发送协议
     * @param isOpen 出水、停水
     * @param waterTh 水温
     * @param waterMl 水容量
     */
    private long sendProTime = 0;   //用来记录当前发送的时间
    private boolean isSending = false;  //是否正在发送协议
    private String proTempString = "";
    private synchronized boolean sendPro(boolean isOpen, int waterTh, int waterMl) {
        if(isSending){
            return true;
        }
        dialogShow(dialogMessage);
        proTimer(false);
        sendProTime = System.currentTimeMillis();
        isSending = true;
        String pro = GJProUtil.getWaterPro(isOpen,waterTh,waterMl);
        proTempString = pro;
        Log.e("ZM","PRO = " + pro);
//        SerialInterface.sendHexMsg2SerialPort(SerialInterface.USEING_PORT,pro);
        return isSending;
    }

    private OperationReceiver operationReceiver;
    private void registerGJProReceiver() {
        operationReceiver = new OperationReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contans.INTENT_GJ_ACTION_ACTIVE);
        intentFilter.addAction(Contans.INTENT_GJ_ACTION_PRO_COME);
        registerReceiver(operationReceiver, intentFilter);
    }

    private void unRegisterGJProReceiver() {
        if (operationReceiver != null) {
            unregisterReceiver(operationReceiver);
        }
    }

    private class OperationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(Contans.INTENT_GJ_ACTION_ACTIVE)){
                operation(true);
                runOperationTimer();
            }else if(action.equals(Contans.INTENT_GJ_ACTION_PRO_COME)){
                int comValue = intent.getIntExtra("comValue",-1);
                if(comValue == 1){
                    //发送协议成功
                    isSending = false;
                }
            }
        }
    }

    /**
     * 3分钟没有操作，亮度设置为30%
     */
    private void operation(boolean isActive) {
        if (isActive) {
            SystemUtil.setScreenLight(this, 200);
            waitingView.setVisibility(View.GONE);
        } else {
            Log.e("ZM", "3分钟没有操作");
            SystemUtil.setScreenLight(this, 80);
            waitingView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 1分钟没有操作，亮度设置为70%
     */
    private void operation1() {
        Log.e("ZM", "1分钟没有操作");
        SystemUtil.setScreenLight(this, 160);
    }

    /**
     * 2分钟没有操作，亮度设置为50%
     */
    private void operation2() {
        Log.e("ZM", "2分钟没有操作");
        SystemUtil.setScreenLight(this, 120);
    }

    /**
     * 操作倒计时
     *
     * @param flag
     */
    private Timer operationTimer;
    private Timer operationTimer1;
    private Timer operationTimer2;

    private void runOperationTimer() {
        if (operationTimer != null) {
            operationTimer.cancel();
            operationTimer = null;
        }
        if (operationTimer1 != null) {
            operationTimer1.cancel();
            operationTimer1 = null;
        }
        if (operationTimer2 != null) {
            operationTimer2.cancel();
            operationTimer2 = null;
        }
        Log.e("ZM", "开始操作定时");
        operationTimer = new Timer();
        operationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(WHAT_NOT_OPERATION);
            }
        }, DEFAULT_TIME);

        operationTimer1 = new Timer();
        operationTimer1.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(WHAT_NOT_OPERATION_1);
            }
        }, DEFAULT_TIME_1);

        operationTimer2 = new Timer();
        operationTimer2.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(WHAT_NOT_OPERATION_2);
            }
        }, DEFAULT_TIME_2);
    }

    private void sendActiveAction() {
        IntentUtils.sendBroadcast(MainActivity.this, INTENT_GJ_ACTION_ACTIVE);
    }


    /**
     * 打开串口
     */
    private void openSerial(){
        try {
            SerialInterface.openSerialPort(SerialInterface.USEING_PORT,SerialInterface.USEING_RATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private long timeLimit = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("ZM", "onKeyDown = " + keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - timeLimit < 1500) {
                finish();
            } else {
                timeLimit = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    private Timer proTimer;
    private void proTimer(boolean cancel){
        if(proTimer != null){
            proTimer.cancel();
            proTimer = null;
        }
        if(cancel){
            return;
        }
        proTimer = new Timer();
        proTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(WHAT_PRO_HANDLER);
            }
        },200);
    }

    /**
     * 100ms没收到返回、继续发送，三次后提示错误
     */
    private int sendTimes = 0;
    private void proTimerHandler(){
        if(isSending){
            //重复发送pro、重新定时
            if(sendTimes > 2){
                //dialog消失,次数清0。提示错误
                sendTimes = 0;
                dialogShow(dialogMessageFail);
                mHandler.sendEmptyMessageDelayed(WHAT_DIALOG_DISMISS,500);
            }else{
                Log.e("ZM","重复发送 = " + proTempString);
                sendTimes++;
                proTimer(false);
            }
        }else{
            //dialog消失
            dialogShow(dialogMessageSuccess);
            mHandler.sendEmptyMessageDelayed(WHAT_DIALOG_DISMISS,500);
        }
    }

    private AlertDialog normalDialog;
    private String dialogMessage = "正在处理中,请稍后...";
    private String dialogMessageSuccess = "处理成功";
    private String dialogMessageFail = "处理失败";
    private void normalDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        normalDialog = builder.create();
    }

    private void dialogShow(String message){
        normalDialog.setMessage(message);
        normalDialog.show();
    }

    private void dialogDismiss(){
        normalDialog.dismiss();
    }
}
