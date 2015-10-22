...
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("TestTag", "Creating MainActivity...");
...
        /* Use DataManager.getInstance().getToDoList()
            instead of ArrayList<ToDo> todoLis */
        ToDoAdapter toDoAdapter = new ToDoAdapter(getApplicationContext(), DataManager.getInstance().getToDoList());
        setListAdapter(toDoAdapter);
    }
