document.addEventListener('DOMContentLoaded', function(){ //{speak:"This event run after created DOM."}
    for(var x in todos){
        var p0=document.createElement("P");
        p0.innerHTML = todos[x].name; //{speak:"Name of ToDo "}
        var p1=document.createElement("P");
        p1.innerHTML = todos[x].desc; //{speak:"Description of ToDo "}
        var li=document.createElement("LI");
        li.appendChild(p0);
        li.appendChild(p1);
        document.querySelector("DIV[role='main'] UL").appendChild(li);//{speak:"Append User Interface."}
    }
},false);