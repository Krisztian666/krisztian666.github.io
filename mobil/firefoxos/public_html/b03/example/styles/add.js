window.onload=function(){
    var newToDoButton=document.querySelector(".recommend");
    newToDoButton.addEventListener("click",newToDoEvent);
}

function newToDoEvent(e){
    var nameToDo=document.getElementById("todoname").value;
    var descToDo=document.getElementById("tododesc").value;
    var todo=newToDo(nameToDo,descToDo,"","");
    addTodo(todo);
}