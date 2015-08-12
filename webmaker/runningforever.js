function runningforever(){ 
    postMessage(Math.random()); 
    setTimeout("timedCount()",Math.floor((Math.random()*1000)+1)); 
} 
runningforever();
