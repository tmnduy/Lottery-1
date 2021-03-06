/*
 * Some rights reserved!
 * Author 	: 	Layone
 * Date 	: 	2013-09
 * Mail 	: 	superlayone@gmail.com
 */
package activity;

import com.example.lottery.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends Activity{
	private GestureDetector gd;
	private Button btnSelect;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		gd=new GestureDetector(this,new OnDoubleClick());
		btnSelect = (Button)findViewById(R.id.eleven_choose_five);
		btnSelect.setOnClickListener(new Button.OnClickListener(){

			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SelectActivity.this, LoginActivity.class);
				SelectActivity.this.startActivity(intent);
				//SelectActivity.this.finish();
			}
			
		});
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	       return gd.onTouchEvent(event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select, menu);
		return true;
	}
	public class OnDoubleClick extends GestureDetector.SimpleOnGestureListener{
	    @Override
	    public boolean onDoubleTap(MotionEvent e) {
	        //TODO
	    	if(e.getX() <= 100 && e.getY() <= 100){
	    		/*
	    		 * Match  if condition
	    		 * go to change settings page
	    		 */
	    		//System.out.println("Double touched");
	    		Intent intent = new Intent();
				intent.setClass(SelectActivity.this, ChangeSettingActivity.class);
				SelectActivity.this.startActivity(intent);    	
	    	}
	        return false;
	    }
	}
}

