function todo(pID,pName,pDesc,pDate,pTime,pImportant){
    this.id=pID;
    this.important=pImportant;
    this.name=pName;
    this.desc=pDesc;
    this.date=pDate;
    this.time=pTime;
}

var todos=[];

function init(){
    var old=localStorage.getItem("todo");
    if(old==null) old="[]";
    todos=JSON.parse(old);
}

function addTodo(pTodo){
    todos.push(pTodo);
    localStorage.setItem("todo",JSON.stringify(todos));
}


