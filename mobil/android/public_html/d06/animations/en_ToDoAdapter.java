public void setList(List<ToDo> pToDoList) {
    toDoList.clear();
    for (ToDo td:pToDoList) {
        toDoList.add(td);
    }
}

public void deleteRow(int position){
    toDoList.remove(position);
}

