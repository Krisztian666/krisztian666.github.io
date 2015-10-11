function gotoMenu(e){  //{speak:"Navigate to the back page."}
    window.location.href="index.html";
}

function gotoOS(e){   //{speak:"End of application."}
    window.close();
}

window.onload = function(){  //{speak:"load page event"}
   document.querySelector("FORM").onsubmit = function(){console.log("form");return false;};
   document.querySelector("#yes").onclick=function(){gotoOS();};
   document.querySelector("#no").onclick=function(){gotoMenu();};
};
