function todo(pName, pDesc, pDate, pTime, pImportant) {
    this.important = pImportant;
    this.name = pName;
    this.desc = pDesc;
    this.date = pDate;
    this.time = pTime;
}

var todos = [];
var db;
var objectStore;

function init() {
    request = window.indexedDB.open("ToDo", 1);
    request.onerror = function (event) {
        console.log("Hiba van");
    };

    request.onsuccess = function (event) {
        db = request.result;
        autoCall();
    };

    request.onupgradeneeded = function (event) {
        console.log("DB onupgradeneeded");
        db = event.target.result;
        objectStore = db.createObjectStore("toDoList", {keyPath: "id", autoIncrement: true});
        objectStore.createIndex("name", "name", {unique: false});
        objectStore.createIndex("date", "date", {unique: false});
        objectStore.createIndex("time", "time", {unique: false});
    };
}

function addTodo(pTodo) {
    objectStore=db.transaction(["toDoList"], "readwrite").objectStore("toDoList");    
    var req=objectStore.put(pTodo);
    req.onsuccess=function(event){
        console.log("tarolva");
    }
}

