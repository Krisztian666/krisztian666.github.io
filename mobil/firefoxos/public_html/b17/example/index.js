document.addEventListener('DOMContentLoaded', function () { //{speak:"This event run after created DOM."}
    for (var x in todos) {
        var todoItemUI=crateToDoUI(todos[x]);
        document.querySelector("#alltodo UL").appendChild(todoItemUI);
        if(todos[x].important)
            document.querySelector("#importanttodo UL").appendChild(todoItemUI.cloneNode(true));
    }
    document.querySelector("#tabpanel1").style.visibility="visible";
    var filteritems=document.querySelectorAll("a[role='tab']");
    for (var i = 0; i < filteritems.length; i++)
        filteritems[i].addEventListener("click", showTabpanel);
}, false);

function crateToDoUI(pValue) {
    var p0 = document.createElement("P");
    p0.innerHTML = pValue.name;

    var a = document.createElement("A");
    a.setAttribute("data-icon", "edit");
    a.href = "edit.html";
    
    var li = document.createElement("LI");
    li.appendChild(a);
    li.appendChild(p0);
    return li;
}

function showTabpanel(e) {
    var panels = document.querySelectorAll(".bb-tabpanel");
    for (var i = 0; i < panels.length; i++)
        panels[i].style.visibility = "hidden";
    document.querySelector("#" + e.currentTarget.getAttribute("aria-controls")).style.visibility = "visible";
    return false;
}