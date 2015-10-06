function gotoAdd(e){
    window.location.href="add.html";
}

function gotoList(e){
    window.location.href="index.html";
}

window.onload = function(){  //{speak:"load page event"}
   document.querySelector("FORM").onsubmit = function(){return false;};
   document.querySelector("#list").onclick=function(){gotoList();};
   document.getElementById("cancel").onclick=function(){gotoList();};
   document.querySelector("#add").onclick=function(){gotoAdd();};
};
