protected void onCreate(Bundle savedInstanceState) {

    Log.i("TestTag", "Creating MainActivity...");
...

    DataManager.getInstance().setContext(getApplicationContext());//{speak:"setContext"}
    ToDoAdapter toDoAdapter = new ToDoAdapter(getApplicationContext(), DataManager.getInstance().getToDoList());
    setListAdapter(toDoAdapter);
}
