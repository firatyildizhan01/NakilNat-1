package com.nakilnat.nakilnat.ui.onboarding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;
import androidx.annotation.RequiresApi;

public class OTPReceiver extends BroadcastReceiver {

    private static EditText smsOtp;

    public void setEditText_otp(EditText editText){
        OTPReceiver.smsOtp = editText;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage smsMessage : smsMessages){
            String message_body = smsMessage.getMessageBody();
            String getOTP = message_body.split(":")[1];
            smsOtp.setText(getOTP);
        }
    }
}
