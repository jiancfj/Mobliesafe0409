package cn.mmvtc.mobliesafe;

import android.app.Application;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Administrator on 2019/4/9.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        correctSIM();//检测SIM是否发生变化。
    }

    public void correctSIM() {
        SharedPreferences sp=getSharedPreferences("config",MODE_PRIVATE);
        boolean protecting=sp.getBoolean("protecting",true);
        if (protecting){
            //得到绑定的SIM卡号
            String bindSIM=sp.getString("sim","");
            //得到手机现在的SIM卡号
            TelephonyManager tm= (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            String realsim=tm.getSimSerialNumber();
            if (bindSIM.equals(realsim))
            {
                Log.i("","sim卡未发生变化，还是你的手机");
            }else {
                Log.i("","sim卡发生了变化");
                String safenumber=sp.getString("safephone","");
                if (!TextUtils.isEmpty(safenumber)){
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(safenumber,null,
                            "你的亲友手机的sim卡已经被更换！",null,null);
                }
            }
        }
    }

}
