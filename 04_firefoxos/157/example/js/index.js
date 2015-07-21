window.onload=function(){
    document.querySelector("BUTTON [data-icon='add']").addEventListener("click",viewAddPage);
    document.querySelector("BUTTON [data-icon='info']").addEventListener("click",viewMainPage);
    document.querySelector("BUTTON [data-icon='close']").addEventListener("click",viewExitPage);
};

function viewMainPage(){window.location.href="index.html";}
function viewAddPage(){window.location.href="add.html";}
function viewExitPage(){window.location.href="exit.html";}