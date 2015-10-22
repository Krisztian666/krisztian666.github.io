@Override
protected void onCreate(Bundle savedInstanceState) {

Log.i(TAG, "Creating MainActivity...");

...
/ / check phone state
TelephonyManager tm = 
    (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

tm.listen(new ToDoPhoneStateListener(getApplicationContext()),
        PhoneStateListener.LISTEN_CALL_STATE | 
        PhoneStateListener.LISTEN_DATA_ACTIVITY);

Toast.makeText(getApplicationContext(), "# " + tm.getLine1Number() 
+ " roaming:" + tm.isNetworkRoaming(), Toast.LENGTH_SHORT).show();

}


