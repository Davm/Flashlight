package com.davmag.flashlight;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class MainActivity extends Activity {
	public static Camera cam = null;
	//apparently not setting this to null leads to disaster
	public static int count = 0;
	
	//future fix: if phone does not have flash, display a warning message and force close the app.
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//future fix: change this to work with checking parameters and such
				if (count == 0){
					flashLightOn();
					count = 1;
				} else if (count == 1){
					flashLightOff();
					count = 0;
				}
			}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void flashLightOn(){
    	if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
    		cam = Camera.open();
    		Parameters p = cam.getParameters();
    		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
    		cam.setParameters(p);
    		cam.startPreview();
    	}
    }
    
    public void flashLightOff(){
    	if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
    	cam.stopPreview();
    	cam.release();
    	cam = null;
    	}
    }
    
    /*public void stringToMorse(String string){
     *Method takes in string
     *uses for loop to change string into dots and dashes and spaces
     *uses another for loop to use dots and dashes and spaces to flashes
     *can be condensed into a single for loop, but focus on functionality first
     *efficiency can always be added in later
    }
    */
}
