/*    public class MainActivity extends ListActivity {*/ //{speak:"extends ListActivity", position:1,delete:[2]}        
    
/*Create datas for demonstration*/ //{speak:"Create demo datas."}
        ArrayList<ToDo> todoList = new ArrayList<ToDo>();
        todoList.add(new ToDo("Todo 1", ToDo.Priority.HIGH, 
                "2015.09.01", true, "Description 1"));
        todoList.add(new ToDo("Todo 2", ToDo.Priority.LOW, 
                "2015.09.02", true, "Description 2"));
        todoList.add(new ToDo("Todo 3", ToDo.Priority.MED, 
                "2015.09.03", true, "Description 3"));

/* Fill the ListView with the demo datas*/ 
        ToDoAdapter toDoAdapter = //{img:"scr-new-main.png"}
                new ToDoAdapter(getApplicationContext(),todoList);
        setListAdapter(toDoAdapter);   
    }
        
/* Notify the Adapter, if the list changed. 
        It it will be usefull later.*/         
    @Override
    protected void onResume() {
        super.onResume();
        ((ToDoAdapter)getListAdapter()).notifyDataSetChanged();
    }