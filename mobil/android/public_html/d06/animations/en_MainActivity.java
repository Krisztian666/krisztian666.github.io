@Override
protected void onCreate(Bundle savedInstanceState) {

    Log.i(TAG, "Creating MainActivity...");
...
    / /register menu for the listed items
    registerForContextMenu(getListView()); //{speak:"Register menu"}

}
...

@Override
protected void onResume() {
    super.onResume();
    ((ToDoAdapter)getListAdapter()).setList(DataManager.getInstance().getToDoList());//{speak:"Set the modified ToDoList"}
    ((ToDoAdapter)getListAdapter()).notifyDataSetChanged();
}
...

@Override//{speak:"Create menu for the list items"}
public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
    if (view.equals(getListView())){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(((ToDo) getListAdapter().getItem(info.position)).getName());
        menu.add(Menu.NONE, 0, 0, getResources().getString(R.string.detailed_todo));
        menu.add(Menu.NONE, 1, 1, getResources().getString(R.string.delete));
        menu.add(Menu.NONE, 2, 2, getResources().getString(R.string.back));
    }
}

@Override//{speak:"An item in a menu is selected."}
public boolean onContextItemSelected(MenuItem item) {
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
    int menuItemIndex = item.getItemId();
    if (menuItemIndex ==0) {
        Intent myIntent = new Intent();
        myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
        myIntent.putExtra("id", ((ToDo) getListAdapter().getItem(info.position)).getId());
        myIntent.putExtra("mode", AddNewActivity.MODE_UPDATE_TODO);//{speak:"Call AddNewActivity in update mode."}
        startActivity(myIntent);
    }
    if (menuItemIndex == 1){
        Log.i(TAG, "Delete item");
        DataManager.getInstance().deleteTodo(((ToDo) getListAdapter().getItem(info.position)).getId());//{speak:"Delete ToDo."}
        ((ToDoAdapter)getListAdapter()).deleteRow(info.position);
        ((ToDoAdapter)getListAdapter()).notifyDataSetChanged();
    }
    return true;
}
