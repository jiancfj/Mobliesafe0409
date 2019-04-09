package cn.mmvtc.mobliesafe.chapter02.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.mmvtc.mobliesafe.App;

/**
 * Created by Administrator on 2019/4/9.
 * 功能：监听开机启动的广播接收者，用于检测sim卡是否
 * 发生变化。如果发生变化，给安全号码发生短信。
 */

public class BootCompleteReciever extends BroadcastReceiver {
    private static final String TAG=BootCompleteReciever.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        ((App)context.getApplicationContext()).correctSIM();
    }
}
