document.addEventListener('DOMContentLoaded', function(){ //{speak:"This event run after created DOM."}
    for(var x in todos){
        var p0=document.createElement("P");
        p0.innerHTML = todos[x].name; //{speak:"Name of ToDo "}
        
        var span= document.createElement("SPAN"); //{speak:"Create navigation"}
        span.class="icon icon-edit";
        span.innerHTML="edit";
        span.style.float="rigth";
        var a=document.createElement("A");
        a.href="edit.html";
        a.appendChild(span);
        
        var li=document.createElement("LI");
        li.appendChild(p0);
        li.appendChild(a);
        document.querySelector("DIV[role='main'] UL").appendChild(li);//{speak:"View in User Interface."}
    }
},false);