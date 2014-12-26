package com.example.testshutdown;

import android.net.SntpClient;
import android.os.SystemClock;
import android.util.Log;

// http://www.apkbus.com/android-167020-1-1.html
// SystemClock.setCurrentTimeMillis(now); 不需要时系统app

// http://blog.csdn.net/i2cbus/article/details/21550901
// android system framework work flow
public class TimeManager {
	private static final String TAG = "testshutdown/TimeManager";

	public void SyncTime() {
		Runnable syncTimeRunner = new Runnable() {

			@Override
			public void run() {
				SyncTimeLock();
			}
		};
		/* 启动线程负责网络通信 */
		new Thread(syncTimeRunner).start();
	}

	protected void SyncTimeLock() {
		SntpClient client = new SntpClient();
		String[] setNtpServers = {
				"133.100.11.8",
				"210.72.145.44",
		};
		int NTP_TIMEOUT = 10000;
		for (int i = 0; i < setNtpServers.length; i++) {
			
			boolean flag = client.requestTime(setNtpServers[i], NTP_TIMEOUT);
			Log.i(TAG, flag + " = client.requestTime(server:"
					+ setNtpServers[i] + ", timeout:" + NTP_TIMEOUT + ")");
			if (flag) {
				long now = client.getNtpTime() + SystemClock.elapsedRealtime()
						- client.getNtpTimeReference();
				Log.d(TAG, "setTimeto rtc");
				SystemClock.setCurrentTimeMillis(now);
//				Message msg = new Message();
//				msg.what = 1;
//				handeler.sendMessage(msg);
				break;
			} else if (i == setNtpServers.length - 1) {
//				Message msg = new Message();
//				msg.what = 0;
//				handeler.sendMessage(msg);
			}
		}
	}
}
