...
    /**
     * Add new toDo
     * @param view
     */
    public void addNewToDo(View view){
        Log.i(TEST_TAG, "Add button pressed..");
...

        /* Remove started service //{speak:"Remove started service"}
        / / call the started service to add new ToDo
        Intent sintent = new Intent(this, ToDoServiceStarted.class);
        sintent.putExtra("name", name.getText().toString());
        sintent.putExtra("priority", sPriority);
        sintent.putExtra("datetime", calendar.getTimeInMillis());
        sintent.putExtra("allday", allDay.isChecked());
        sintent.putExtra("description", description.getText().toString());
        sintent.putExtra("url", url.getText().toString());
        
        startService(sintent);
        */

        / / call the IntentService to add new ToDo//{speak:"Call the IntentService"}
        Intent isintent = new Intent(this, ToDoServiceIntent.class);
        isintent.setAction(ToDoServiceIntent.ACTION_ADD_TODO);
        isintent.putExtra("name", name.getText().toString());
        isintent.putExtra("priority", sPriority);
        isintent.putExtra("datetime", calendar.getTimeInMillis());
        isintent.putExtra("allday", allDay.isChecked());
        isintent.putExtra("description", description.getText().toString());
        isintent.putExtra("url", url.getText().toString());

        startService(isintent);


        / Close this activity, return to MainActivity
        setResult(RESULT_OK);
        finish();
    }
}
