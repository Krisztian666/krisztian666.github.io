function todo(pName,pDesc,pDate,pTime){
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