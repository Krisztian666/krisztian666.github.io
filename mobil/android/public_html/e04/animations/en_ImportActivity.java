public void importFromXml(View view){
    try {
        Log.i(TAG, "Read todos.xml from assets.");
        InputStream is = getAssets().open("todos.xml");
        for (ToDo todo: ToDoXmlPullParser.ParseXml(is)){
            DataManager.getInstance().addTodo(todo);
        }
        is.close();
        Toast toast = Toast.makeText(view.getContext(), //{img:"scr-import-xml2.png", speak:"Import succesful."}
view.getContext().getText(R.string.import_true), Toast.LENGTH_SHORT);
        toast.show();
    } catch (Exception e) {
        Toast toast = Toast.makeText(view.getContext(), 
view.getContext().getText(R.string.import_false), Toast.LENGTH_SHORT);
        toast.show();
        e.printStackTrace();
    }
}//{img:"scr-main2.png", speak:"Import"}



