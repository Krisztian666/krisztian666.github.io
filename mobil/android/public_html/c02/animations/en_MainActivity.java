    protected void onCreate(Bundle savedInstanceState) {

...

        ArrayList<ToDo> todoList = new ArrayList<ToDo>();
        todoList.add(new ToDo("Todo 1", ToDo.Priority.HIGH, "2015.09.01", 
            true, "Description 1", 
            "http:/ /developer.android.com/guide/components/intents-common.html"));//{speak:"Add URL."}
        todoList.add(new ToDo("Todo 2", ToDo.Priority.LOW, "2015.09.02", 
            true, "Description 2"));
...
