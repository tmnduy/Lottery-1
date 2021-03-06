/*
 * Some rights reserved!
 * Author 	: 	Layone
 * Date 	: 	2013-09
 * Mail 	: 	superlayone@gmail.com
 */
package activity;

import java.lang.ref.WeakReference;

import com.example.lottery.R;
import common.Global;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.KeyEvent;

public class WelcomeActivity extends Activity {

	//Define go
	private static final int goSelect = 1000;  
	private static final int goGuide = 1001; 
	//Splash delay time
	private static final long SPLASH_DELAY_MILLIS = 2000; 
	//Preference 
	boolean isFirstIn = false;  
	private static final String SHAREDPREFERENCES_NAME = "first_pref";  
	//Handler
	private Handler mHandler = new MyHandler(this) {  		  
	        @Override  
	        public void handleMessage(Message msg) {  
	            switch (msg.what) {  
	            case goSelect:  
	            	goSelectActivity();  
	                break;  
	            case goGuide:  
	                goGuideActivity();  
	                break;  
	            }  
	            super.handleMessage(msg);  
	        }  
	    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		init();
    }
	//Using weak reference to avoid leaking occur
	private static class MyHandler extends Handler{
	    private final WeakReference<Activity> mActivity;
	    public MyHandler(Activity activity) {
	        mActivity = new WeakReference<Activity>(activity);
	    }
	    @Override
	    public void handleMessage(Message msg) {
	        System.out.println(msg);
	        if(mActivity.get() == null) {
	            return;
	        }
	    }
	}
	/*
	 * Checkout if first in
	 */
	private void init() {  
		/*
		 * Fetch SharedPreferences to make a decision
		 */
        SharedPreferences preferences = getSharedPreferences(  
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);    
        isFirstIn = preferences.getBoolean("isFirstIn", true);  
        if (isFirstIn) { 
        	mHandler.sendEmptyMessageDelayed(goGuide, SPLASH_DELAY_MILLIS);             
        } else {  
        	mHandler.sendEmptyMessageDelayed(goSelect, SPLASH_DELAY_MILLIS); 
        }  
        /*
         * Check if global settings stored into SharedPreferences
         * If NOT_EXIST,put into SharedPreferences
         * and commit these changes
         */
        preferences = getSharedPreferences(Global.GLOBALSETTINGS,MODE_PRIVATE);
        if(preferences.getString(Global.PREFERENCE_GATEWAY, "none") == "none"){
        	Editor editor = preferences.edit(); 
        	editor.putString(Global.PREFERENCE_GATEWAY,  Global.defaultGateway); 
        	editor.commit(); 
        }
        if(preferences.getString(Global.PREFERENCE_PLATFORM_IP, "none") == "none"){
        	Editor editor = preferences.edit(); 
        	editor.putString(Global.PREFERENCE_PLATFORM_IP, Global.defaultPlatformIP); 
        	editor.commit(); 
        }
        if(preferences.getString(Global.PREFERENCE_PLATFORM_PORT, "none") == "none"){
        	Editor editor = preferences.edit(); 
        	editor.putString(Global.PREFERENCE_PLATFORM_PORT, Global.defaultPlatformPort); 
        	editor.commit(); 
        }
        if(preferences.getString(Global.PREFERENCE_SITE_NO, "none") == "none"){
        	Editor editor = preferences.edit(); 
        	editor.putString(Global.PREFERENCE_SITE_NO, Global.defaultSiteNo); 
        	editor.commit(); 
        }
        if(preferences.getString(Global.PREFERENCE_TERMINAL_NO, "none") == "none"){
        	Editor editor = preferences.edit(); 
        	editor.putString(Global.PREFERENCE_TERMINAL_NO, Global.defaultTerminalNo); 
        	editor.commit(); 
        }
    }  
    private void goSelectActivity() {  
    	Intent intent = new Intent();
		intent.setClass(WelcomeActivity.this, SelectActivity.class);
		startActivity(intent);
		WelcomeActivity.this.overridePendingTransition(R.layout.welcomepage_alpha_in, R.layout.welcomepage_alpha_out);
		finish();
    }  
  
    private void goGuideActivity() {  
        Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);  
        WelcomeActivity.this.startActivity(intent);  
        WelcomeActivity.this.finish();  
    }  
	@Override  
	//hold up BACK operations
    public boolean onKeyDown(int keyCode, KeyEvent event) {   
        if(keyCode==KeyEvent.KEYCODE_BACK) {   
            return false;   
        }   
        return false;   
    } 

}
