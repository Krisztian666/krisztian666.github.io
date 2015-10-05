
window.onload = function(){ 
    document.querySelector(".recommend").addEventListener("click",eventNewToDo);//{speak:"Set click on the button event."}
    document.querySelector("FORM").onsubmit = function(){return false;};//{speak:"Disable form submit."}
};

function eventNewToDo(e){  //{speak:"create button on the button event"}
    var dataName = document.querySelector("#todoname").value;
    var dataDesc = document.querySelector("#tododesc").value;
    var newtodo= new todo(dataName,dataDesc,"","");
    addTodo(newtodo);
}
