document.addEventListener('DOMContentLoaded', function(){ //{speak:"This event run after created DOM."}
    for(var x in todos){
        var p0=document.createElement("P");
        p0.innerHTML = todos[x].name; //{speak:"Name of ToDo "}
        
        var a=document.createElement("A");//{speak:"Create navigation"}
        a.setAttribute("data-icon","edit");
        a.href="edit.html";
               
        var li=document.createElement("LI");
        li.appendChild(a);
        li.appendChild(p0);
        if(todos[x].important)
        document.querySelector("DIV[role='main'] UL").appendChild(li);//{speak:"View in User Interface."}
    }
    
},false);

