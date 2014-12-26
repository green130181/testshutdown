package com.example.testshutdown;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

// http://code.metager.de/source/xref/android/4.4/development/samples/Support7Demos/Android.mk
// http://blog.csdn.net/zzp16/article/details/7188185
public class MainActivity extends ActionBarActivity {
	public final static String TAG = "testshutdown/MainActivity";
	private Button mShutDown;
	private Button mRestart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		mShutDown = (Button) findViewById(R.id.shutdown);
		mRestart = (Button) findViewById(R.id.restart);
	}
	
	public void btnShutDown(View view) {
		Log.d(TAG, "btnShutDown");
		Log.v(TAG, "shutdown");
        Intent intent = new Intent(Intent.ACTION_REQUEST_SHUTDOWN);
		intent.putExtra(Intent.EXTRA_KEY_CONFIRM, false);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	public void btnRestart(View view) {
		Log.d(TAG, "btnRestart");
		Log.v(TAG, "broadcast_reboot");
        Intent i = new Intent(Intent.ACTION_REBOOT);
        i.putExtra("nowait", 1);
        i.putExtra("interval", 1);
        i.putExtra("window", 0);
        sendBroadcast(i);   
	}
	
	public void btnScreenOff(View view) {
		// copy from here http://bbs.51cto.com/thread-1018050-1.html
		Log.d(TAG, "btnScreenOff");
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		pm.goToSleep(SystemClock.uptimeMillis());
	}
	
	public void btnUpdateTime(View view) {
		// copy from here http://bbs.51cto.com/thread-1018050-1.html
		Log.d(TAG, "btnUpdateTime");
		TimeManager tm = new TimeManager();
		tm.SyncTime();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
