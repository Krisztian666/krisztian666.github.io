
window.onload = function () {
    document.querySelector("menu button").addEventListener("click", removeToDo);
    init();
};

function autoCall() {
    objectStore = db.transaction(["toDoList"], "readwrite").objectStore("toDoList");
    var res0 = objectStore.get(parseInt(sessionStorage.getItem("todoid")));
    res0.onsuccess = function (event) {
        var item = res0.result;
        console.log(item);
        load(item);
    };
}

function load(item) {
    document.querySelector("#todoname").innerHTML = item.name;
    document.querySelector("#tododesc").innerHTML = item.desc;
    document.querySelector("#tododate").innerHTML = item.date;
    document.querySelector("#todotime").innerHTML = item.time;
}


function removeToDo() {
    objectStore = db.transaction(["toDoList"], "readwrite").objectStore("toDoList");
    var res0 = objectStore.delete(parseInt(sessionStorage.getItem("todoid")));
    res0.onsuccess = function (event) {
        window.location.href = "index.html";
    };
}