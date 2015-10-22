import android.util.Log;

public class MainActivity extends Activity {
    
    private static final String TAG = "TestTag";
    
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    Log.v(TAG, "Creating MainActivity... VERBOSE"); //{speak:"Send a VERBOSE log message."}        
    Log.d(TAG, "Creating MainActivity... DEBUG"); //{speak:"Send a DEBUG log message."}   
    Log.i(TAG, "Creating MainActivity... INFO"); //{speak:"Send a INFO log message."}  
    Log.w(TAG, "Creating MainActivity... WARN"); //{speak:"Send a WARN log message."}  
    Log.e(TAG, "Creating MainActivity... ERROR"); //{speak:"Send a ERROR log message."}   
    Log.wtf(TAG, "Creating MainActivity... WTF"); //{speak:"What a Terrible Failure!"}   
/**
 * What a Terrible Failure: 
 * Report a condition that should never happen.
 */
    
 /* Log.d(TAG, "Exception in MainActivity.", throwable); */  //{speak:"You can log the exception as well."}