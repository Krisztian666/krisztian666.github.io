function runningforever(){ 
    postMessage(Math.random()); 
    setTimeout(runningforever,Math.floor((Math.random()*1000)+1)); 
} 
runningforever();
