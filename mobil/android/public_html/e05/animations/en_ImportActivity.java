public void importFromJSON(View view){
    try {
        Log.i(TAG, "Read todos.json from assets.");
        InputStream is = getAssets().open("todos.json");
        for (ToDo todo: ToDoJSONParser.ParseJSON(is)){
            DataManager.getInstance().addTodo(todo);
        }
        is.close();
        Toast toast = Toast.makeText(view.getContext(), //{img:"scr-import-json2.png", speak:"Import succesful."}
view.getContext().getText(R.string.import_true), Toast.LENGTH_SHORT);
        toast.show();
    } catch (Exception e) {
        Toast toast = Toast.makeText(view.getContext(), 
view.getContext().getText(R.string.import_false), Toast.LENGTH_SHORT);
        toast.show();
        e.printStackTrace();
    }
}//{img:"scr-main2.png", speak:"Import"}


