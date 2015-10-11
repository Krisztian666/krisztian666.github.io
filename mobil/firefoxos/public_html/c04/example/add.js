
window.onload = function(){ 
    document.querySelector(".recommend").addEventListener("click",eventNewToDo);
    document.querySelector("FORM").onsubmit = function(){return false;};
    init();
    if(sessionStorage.getItem("todoid")!=null)
        load();
};

function autoCall(){}

function load(){
    var item=todos[parseInt(sessionStorage.getItem("todoid"))];
    document.querySelector("#todoname").value=item.name;
    document.querySelector("#tododesc").value=item.desc;
    document.querySelector("input[type='checkbox']").value=item.important;
    document.querySelector("#tododate").value=item.date;
    document.querySelector("#todotime").value=item.time;
    document.querySelector("header menu button").addEventListener("click",save);    
}

function eventNewToDo(e){  
    var item=new todo("","","","",false);
    if(sessionStorage.getItem("todoid")!=null)
        item=todos[parseInt(sessionStorage.getItem("todoid"))];
    item.name = document.querySelector("#todoname").value;
    item.desc = document.querySelector("#tododesc").value;
    item.date = document.querySelector("#tododate").value; 
    item.time = document.querySelector("#todotime").value; 
    item.important = document.querySelector("#todoimportant").checked; 
    if(sessionStorage.getItem("todoid")!=null){
        todos[parseInt(sessionStorage.getItem("todoid"))]=item;
        localStorage.setItem("todo",JSON.stringify(todos));
    }
    else addTodo(item);
    document.querySelector('section[role="status"]').style.display="block";
    setTimeout(hideStatus,3000);
}

function hideStatus(){
    document.querySelector('section[role="status"]').style.display="none";
}