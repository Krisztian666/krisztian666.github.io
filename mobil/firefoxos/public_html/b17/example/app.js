function todo(pID,pName,pDesc,pDate,pTime,pImportant){
    this.id=pID;
    this.important=pImportant;
    this.name=pName;
    this.desc=pDesc;
    this.date=pDate;
    this.time=pTime;
}

var todos=[];

function addTodo(pTodo){
    todos.push(pTodo);
    for(x in todos){
        console.log(""+x+":"+JSON.stringify(todos[x]));
    }
}

addTodo(new todo(0,"demo1","demo1","","",true));
addTodo(new todo(1,"demo2","demo2","","",true));
addTodo(new todo(2,"demo3","demo3","","",false));
addTodo(new todo(3,"demo4","demo4","","",false));
