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

addTodo(new todo("demo1","demo1","",""));
addTodo(new todo("demo2","demo2","",""));
addTodo(new todo("demo3","demo3","",""));
addTodo(new todo("demo4","demo4","",""));
console.log("loaded");