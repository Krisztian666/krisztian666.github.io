
document.addEventListener('DOMContentLoaded', function () { //{speak:"This event run after created DOM."}
    init();
    sessionStorage.removeItem("todoid");
    document.querySelector("#tabpanel1").style.visibility="visible";
    var filteritems=document.querySelectorAll("a[role='tab']");
    for (var i = 0; i < filteritems.length; i++)
        filteritems[i].addEventListener("click", showTabpanel);
}, false);

function autoCall(){
    console.log("defaultFunction");
    objectStore=db.transaction(["toDoList"], "readwrite").objectStore("toDoList");    
    var res0=objectStore.index("name").openCursor(IDBKeyRange.lowerBound("", true));
    res0.onsuccess = function(event) {
    var cursor = event.target.result;
        if(cursor) {
            var todoItemUI = createToDoUI(cursor.value);
            document.querySelector("#alltodo UL").appendChild(todoItemUI);
            if(todoItemUI.important)
                document.querySelector("#importanttodo UL").appendChild(todoItemUI.cloneNode(true));
        cursor.continue();
      } 
      else {console.log('Entries all displayed.');}
    };
}


function showTabpanel(e) {
    var panels = document.querySelectorAll(".bb-tabpanel");
    for (var i = 0; i < panels.length; i++)
        panels[i].style.visibility = "hidden";
    document.querySelector("#" + e.currentTarget.getAttribute("aria-controls")).style.visibility = "visible";
    return false;
}

function editTodo(e){
    var todoid=e.currentTarget.getAttribute("todoid");
    sessionStorage.setItem("todoid",todoid);
    window.location.href="add.html";
}

function createToDoUI(pValue) {
    console.log("createToDoUI");
    var p0 = document.createElement("P");
    p0.innerHTML = pValue.name;

    var a = document.createElement("DIV");
    a.setAttribute("data-icon", "edit");
    a.setAttribute("todoid", pValue.id);
    a.addEventListener("click",editTodo);
    
    var li = document.createElement("LI");
    li.appendChild(a);
    li.appendChild(p0);
    console.log("createToDoUI");
    return li;
}