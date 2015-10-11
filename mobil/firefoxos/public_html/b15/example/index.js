document.addEventListener('DOMContentLoaded', function(){ //{speak:"This event run after created DOM."}
    for(var x in todos){
        var p0=document.createElement("P");
        p0.innerHTML = todos[x].name; //{speak:"Name of ToDo "}
        
        var a=document.createElement("A");//{speak:"Create navigation"}
        a.setAttribute("data-icon","edit");
        a.href="edit.html";
//        a.style.background="yellow";
//        a.style.textDecoration="none";
               
        var li=document.createElement("LI");
        li.appendChild(a);
        li.appendChild(p0);
        document.querySelector("DIV[role='main'] UL").appendChild(li);//{speak:"View in User Interface."}
    }
    document.querySelector("div[role='toolbar'] button[data-icon='add']")
        .addEventListener("click",
        function(){window.location.href="add.html";});
    document.querySelector("div[role='toolbar'] button[data-icon='reload']")
        .addEventListener("click",
        function(){window.location.href="index.html";});
    
},false);

