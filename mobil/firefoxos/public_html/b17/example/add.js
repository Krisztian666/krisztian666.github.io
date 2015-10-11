
window.onload = function(){ 
    document.querySelector(".recommend").addEventListener("click",eventNewToDo);
    document.querySelector("FORM").onsubmit = function(){return false;};
};

function eventNewToDo(e){  
    var dataName = document.querySelector("#todoname").value;
    var dataDesc = document.querySelector("#tododesc").value;
    var dataDate = document.querySelector("#tododate").value; 
    var dataTime = document.querySelector("#todotime").value; 
    var dataImportant = document.querySelector("#todoimportant").checked; 
    var newtodo= new todo(todos.length,dataName,dataDesc,dataDate,dataTime,dataImportant);
    addTodo(newtodo);
    document.querySelector('section[role="status"]').style.display="block";
    setTimeout(hideStatus,3000);
}

function hideStatus(){
    document.querySelector('section[role="status"]').style.display="none";
}