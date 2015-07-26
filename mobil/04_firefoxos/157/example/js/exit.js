
window.onload=function(){
    document.querySelector("#cancel").addEventListener("click",viewMainPage);
    document.querySelector("#exit").addEventListener("click",exit);
}

function viewMainPage(){window.location.href="index.html";}
function exit(){window.close();}