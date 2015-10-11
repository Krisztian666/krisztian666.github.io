
window.onload = function(){ 
    document.querySelector(".recommend").addEventListener("click",eventNewToDo);
    document.querySelector("FORM").onsubmit = function(){return false;};
    init();
};

var item;
function autoCall(){
    item=new todo("","","","",false);
    if(sessionStorage.getItem("todoid")){
        objectStore=db.transaction(["toDoList"], "readwrite").objectStore("toDoList");    
        var res0=objectStore.get(parseInt(sessionStorage.getItem("todoid")));
        res0.onsuccess = function(event) {
            item=res0.result;
            load(item);
        };
        
    }
}

function load(item){
    document.querySelector("#todoname").value=item.name;
    document.querySelector("#tododesc").value=item.desc;
    document.querySelector("input[type='checkbox']").value=item.important;
    document.querySelector("#tododate").value=item.date;
    document.querySelector("#todotime").value=item.time;
}

function eventNewToDo(e){  
    item.name = document.querySelector("#todoname").value;
    item.desc = document.querySelector("#tododesc").value;
    item.date = document.querySelector("#tododate").value; 
    item.time = document.querySelector("#todotime").value; 
    item.important = document.querySelector("#todoimportant").checked; 
    addTodo(item);
    document.querySelector('section[role="status"]').style.display="block";
    setTimeout(hideStatus,3000);
}

function hideStatus(){
    document.querySelector('section[role="status"]').style.display="none";
}