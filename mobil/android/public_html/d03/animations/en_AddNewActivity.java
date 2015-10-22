/**
 * Add new toDo
 * @param view
 */
public void addNewToDo(View view){
    Log.i(TEST_TAG, "Add button pressed..");
...
    TextView contactName = (TextView)findViewById(R.id.contact_name);
    TextView contactPhone = (TextView)findViewById(R.id.contact_phone);
...
    / / call ToDoServiceBound
    / /boundService.addToDo(newToDo);//{speak:"From now don't use the services."}

    Contact contact = new Contact(contactName.getText().toString(),//{speak:"Save the Contact as well."}
            contactPhone.getText().toString(),
            "");
    ToDo newToDo = new ToDo(
            name.getText().toString(),
            pPriority,
            calendar.getTimeInMillis(),
            allDay.isChecked(),
            description.getText().toString(),
            url.getText().toString(),
            contact);

    / /Add new ToDo//{speak:"Use the DataManager."}
    DataManager.getInstance().addTodo(newToDo);




    / / Close this activity, return to MainActivity
    setResult(RESULT_OK);
    finish();
}

/**
 * Show add contact dialog
 * @param view
 */
public void addNewContactToToDo(View view){

    final ArrayList<Contact> contacts = ContactsProvider.getContacts(view.getContext());

    final Dialog dialog = new Dialog(view.getContext());//{img:"scr-addnew-contact2.png", speak:"Create contact list dialog."}
    dialog.setTitle(R.string.add_contact);
    dialog.setCancelable(true);

    ListView listView = new ListView(this);
    ToDoContactsAdapter toDoContactsAdapter = new ToDoContactsAdapter(view.getContext(), contacts);
    listView.setAdapter(toDoContactsAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//{img:"scr-addnew-contact3.png", speak:"Select contact item."}
            Log.i(TEST_TAG, "item selected:" + position);
            TextView cName = (TextView)findViewById(R.id.contact_name);
            cName.setText(contacts.get(position).getContactName());
            TextView cPhone = (TextView)findViewById(R.id.contact_phone);
            cPhone.setText(contacts.get(position).getPhoneNumber());

            dialog.cancel();
        }
    });
    dialog.setContentView(listView);
    dialog.show();
}

