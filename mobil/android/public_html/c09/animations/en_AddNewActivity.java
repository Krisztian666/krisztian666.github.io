public class AddNewActivity extends Activity {
    private DatePicker date;
    private static String TEST_TAG = "TestTag";

    ToDoServiceBound boundService;
    boolean mBound = false;

    @Override
    protected void onStart(){
        super.onStart();
        / /Bind to ToDoServiceBound//{speak:"Bind to ToDoServiceBound"}
        Intent boundIntent = new Intent(this, ToDoServiceBound.class);
        bindService(boundIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        / /Unbind from ToDoServiceBound//{speak:"Unbind from ToDoServiceBound"}
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

...

        
    public void addNewToDo(View view){
        Log.i(TEST_TAG, "Add button pressed..");
...
        /* DELETE
        / / call the IntentService to add new ToDo
        Intent isintent = new Intent(this, ToDoServiceIntent.class);
        isintent.setAction(ToDoServiceIntent.ACTION_ADD_TODO);
        isintent.putExtra("name", name.getText().toString());
        isintent.putExtra("priority", sPriority);
        isintent.putExtra("datetime", calendar.getTimeInMillis());
        isintent.putExtra("allday", allDay.isChecked());
        isintent.putExtra("description", description.getText().toString());
        isintent.putExtra("url", url.getText().toString());

        startService(isintent);
        */

        / / call ToDoServiceBound//{speak:"call ToDoServiceBound"}
        ToDo newToDo = new ToDo(
                name.getText().toString(),
                pPriority,
                calendar.getTimeInMillis(),
                allDay.isChecked(),
                description.getText().toString(),
                url.getText().toString());
        boundService.addToDo(newToDo);


        / / Close this activity, return to MainActivity
        setResult(RESULT_OK);
        finish();
    }



    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            / /bound to ToDoServiceBound//{speak:"bound to ToDoServiceBound"}
            ToDoServiceBound.ToDoServiceBinder binder = (ToDoServiceBound.ToDoServiceBinder) service;
            boundService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

}


