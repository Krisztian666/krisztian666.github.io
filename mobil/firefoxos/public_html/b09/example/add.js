
window.onload = function(){ 
    document.querySelector(".recommend").addEventListener("click",eventNewToDo);//{speak:"Set click on the button event."}
    document.querySelector("FORM").onsubmit = function(){return false;};//{speak:"Disable form submit."}
};

function eventNewToDo(e){  //{speak:"create button on the button event"}
    var dataName = document.querySelector("#todoname").value;
    var dataDesc = document.querySelector("#tododesc").value;
    var dataDate = document.querySelector("#tododate").value; 
    var dataTime = document.querySelector("#todotime").value; 
    var newtodo= new todo(dataName,dataDesc,dataDate,dataTime); 
    addTodo(newtodo);
    document.querySelector('section[role="status"]').style.display="block";
    setTimeout(hideStatus,3000);
}

function hideStatus(){
    document.querySelector('section[role="status"]').style.display="none";
}