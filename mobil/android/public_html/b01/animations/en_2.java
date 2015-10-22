package com.example.todo;

import android.app.Activity;
import android.os.Bundle;

/**
* The Activity class takes care of creating a window for you
* in which you can place your UI.
*/
public class MainActivity extends Activity { //{speak:"Create MainActivity"}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
/**
* Called when the activity is first created. 
* This is where you should do all of your normal static set up: 
* create views, bind data to lists, etc. 
* This method also provides you with a Bundle 
* containing the activity's previously frozen state, if there was one.
* Always followed by onStart().
*/        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
/**
* Called when the activity is becoming visible to the user.
* Followed by onResume() if the activity comes to the foreground, 
* or onStop() if it becomes hidden.      
*/
        super.onStart();
    }

    @Override
    protected void onRestart() {
/**
* Called after your activity has been stopped, 
* prior to it being started again.
* Always followed by onStart()
*/        
        super.onRestart();
    }

    @Override
    protected void onResume() {
/**
* Called when the activity will start interacting with the user. 
* At this point your activity is at the top of the activity stack, 
* with user input going to it.
* Always followed by onPause().
*/        
        super.onResume();
    }

    @Override
    protected void onPause() {
/**
 * Called when the system is about to start resuming a previous activity. 
 * This is typically used to commit unsaved changes, stop animations, etc. 
 * Implementations of this method must be quick because 
 * the next activity will not be resumed until this method returns.
 * Followed by either onResume() if the activity returns back to the front, 
 * or onStop() if it becomes invisible to the user.
 */        
        super.onPause();
    }

    @Override
    protected void onStop() {
/**
 * Called when the activity is no longer visible to the user, 
 * because another activity has been resumed and is covering this one. 
 * This may happen either because a new activity is being started, 
 * an existing one is being brought in front of this one, 
 * or this one is being destroyed. 
 * Followed by either onRestart(), or onDestroy().
 */        
        super.onStop();
    }

    @Override
    protected void onDestroy() {
/**
 * The final call you receive before your activity is destroyed. 
 * This can happen either because the activity is finishing 
 * (someone called finish() on it, or because the system is 
 * temporarily destroying this instance of the activity 
 * to save space. You can distinguish between these two 
 * scenarios with the isFinishing() method.
 */        
        super.onDestroy();
    }

}
