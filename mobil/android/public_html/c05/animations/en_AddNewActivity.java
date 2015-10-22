...
    /**
     * Add new toDo
     * @param view
     */
    public void addNewToDo(View view){
        Log.i(TEST_TAG, "Add button pressed..");
...

        /* Remove //{speak:"Remove sendBroadcast"}
        / Add new broadcastIntent
        Intent intent = new Intent();
        intent.setAction("com.example.todo.ADD_NEW_TODO");

        intent.putExtra("name", name.getText().toString());
        intent.putExtra("priority", sPriority);
        intent.putExtra("datetime", calendar.getTimeInMillis());
        intent.putExtra("allday", allDay.isChecked());
        intent.putExtra("description", description.getText().toString());
        intent.putExtra("url", url.getText().toString());

        sendBroadcast(intent);
        */

        / call the started service to add new ToDo
        Intent sintent = new Intent(this, ToDoServiceStarted.class);//{speak:"Call the started service"}
        sintent.putExtra("name", name.getText().toString());
        sintent.putExtra("priority", sPriority);
        sintent.putExtra("datetime", calendar.getTimeInMillis());
        sintent.putExtra("allday", allDay.isChecked());
        sintent.putExtra("description", description.getText().toString());
        sintent.putExtra("url", url.getText().toString());

        startService(sintent);


        / Close this activity, return to MainActivity
        setResult(RESULT_OK);
        finish();
    }
}
