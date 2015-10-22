
    /**
     * Add new toDo
     * @param view
     */
    public void addNewToDo(View view){ //{speak:"Called by add_new_button"}
        Log.i(TEST_TAG, "Add button pressed..");

        EditText name = (EditText)findViewById(R.id.toDoNameEditText);
        EditText description = (EditText)findViewById(R.id.descriptionEditText);
        EditText url = (EditText)findViewById(R.id.urlEditText);
        CheckBox allDay = (CheckBox)findViewById(R.id.allDayCheckBox);

        RadioGroup priority = (RadioGroup)findViewById(R.id.priorityRadioGroup);
        String sPriority = "LOW";
        switch (priority.getCheckedRadioButtonId()){
            case R.id.pLowRadio: sPriority = "LOW";
                break;
            case R.id.pMedRadio: sPriority = "MED";
                break;
            case R.id.pHighRadio: sPriority = "HI";
                break;
        }
        DatePicker datePicker = (DatePicker)findViewById(R.id.date);
        TimePicker timePicker = (TimePicker)findViewById(R.id.time);

        /log the date
        Date date = new Date(datePicker.getCalendarView().getDate());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(date);
        Log.i(TEST_TAG, formattedDate + " " + timePicker.getCurrentHour() + 
    ":" + timePicker.getCurrentMinute());

        /convert the date and time to ms
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, datePicker.getYear());
        calendar.set(Calendar.MONTH, datePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);


        / Add new broadcastIntent
        Intent intent = new Intent();//{speak:"Create new broadcastIntent"}
        intent.setAction("com.example.todo.ADD_NEW_TODO");

        intent.putExtra("name", name.getText().toString());
        intent.putExtra("priority", sPriority);
        intent.putExtra("datetime", calendar.getTimeInMillis());
        intent.putExtra("allday", allDay.isChecked());
        intent.putExtra("description", description.getText().toString());
        intent.putExtra("url", url.getText().toString());

        sendBroadcast(intent);//{speak:"Send broadcastIntent"}

        / Close this activity, return to MainActivity
        setResult(RESULT_OK);//{speak:"Close"}
        finish();
    }
}
