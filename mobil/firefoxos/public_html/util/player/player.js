var dataSource;
var lineIndex=0;
var playingIndex=0;
var edContent={};
/*commands
 * speak
 * img
 * 
 */           
function getSourceContent(pUrl) {
    $.ajax({
        url: edContent.dataPath+pUrl, //GetURLParameter("path"), 
        success: function (result) {
                writeingCodeFromString(result);
        }
    });
}


function writeingCodeFromString(result){
            dataSource.values=[];
            var ks = result.split("\n");
            $.each(ks, function (k) {
                kc= ks[k].split("//");
                console.log(ks[k]);
                var data={};
                if(kc.length>1) eval("data="+kc[1]);
                data["content"]=kc[0];
                dataSource.values.push(data);
            });
            dataSource["sourceurl"]=null;
            lineIndex=0;
            writeCode();   
}


function getURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}


function showText(pCont, pozition, index, interval, pIDX) {
    message = "" + pCont["values"][pIDX].content + "";
    if (index < message.length) {
        $("#" + pCont["container"] + " code:eq(" + pozition + ")").scroll();
        $("#" + pCont["container"] + " code:eq(" + pozition + ")").append(message[index]);
        index++;
        setTimeout(function () {
            showText(pCont, pozition, index, interval, pIDX);
        }, interval);
    }
    else if (pIDX < (pCont.values.length - 1)) {
        if(pCont["values"][pIDX].delete)
            removeCode(pCont["container"],pCont["values"][pIDX].delete);
        lineIndex++;
        newspeed=(pCont.values[pIDX].newspeed)?pCont.values[pIDX].newspeed:2000;
        setTimeout(writeCode, newspeed);
    }
    else if (playingIndex < edContent.playing.length) {
        if(pCont["values"][pIDX].delete)
            removeCode(pCont["container"],pCont["values"][pIDX].delete);
        playingIndex++;
        console.log("playingindex:"+playingIndex);
        setTimeout(playContent, 3000);
    }
}

function insertText(pCont, pIdx) {
//    console.log("AddText:" + pIdx);
//    console.log("AddText:" + $("#" + pCont["container"] + "code:eq(" + (pIdx - 1) + ")"));
    if(pCont.values[pIdx].delete)
        for(x in pCont.values[pIdx].delete){
        $("#" + pCont["container"] + " pre " + "code:eq(" + (pCont.values[pIdx].delete[x]-1) + ")").addClass("modifing");
    }
    $("#" + pCont["container"] + " pre " + "code:eq(" + (pCont.values[pIdx].position - 1) + ")").after("<code></code>");
    writingspeed=(pCont.values[pIdx].writingspeed)?pCont.values[pIdx].writingspeed:pCont.writingspeed;
    showText(pCont, pCont.values[pIdx].position, 0,writingspeed, pIdx);
}

function newText(pCont, pIdx) {
//    console.log("NextText:" + pIdx);
    $("#" + pCont["container"] + " pre").append("<code></code>");
    showText(pCont, $("#" + pCont["container"] + " pre code").length - 1, 0, 1, pIdx);
}


function speak(pTxt){
    $("h3").html(pTxt);
//    $("#screen audio").attr("src","http://5.249.155.46:8080/SoundServer-1.0/sound?text="+pTxt+".");
//    document.querySelector("#screen audio").play();
}

function changeImg(pUrl){
    $("#screen img").attr("src",edContent.dataPath+pUrl);
}

function removeCode(pContainer,pLineNumbers){
        $(".modifing").remove();
}


function startingPlayer(){
            $.ajax({
                url: getURLParameter("path"),
                success: function(result){
                    eval("edContent="+result);
                    $("H1").html(edContent.title);
                    $("span").html(edContent.desc);
                    for(x in edContent.files){
                        $("#sources ul").append("<li><a href=\"#"+edContent.files[x].id+"\">"+edContent.files[x].title+"</a></li>");
                        $("#sources").append("<div id="+edContent.files[x].id+"  number=\""+x+"\"><pre></pre></div>");
                    }
                    $("#sources").tabs();
                    $("#screen").tabs();
                    playContent();
                }
            });
            
}
        
            function writeCode(){
                $("#sources").tabs("option","active",$("#"+dataSource["container"]).attr("number"));
                
                if(dataSource["sourceurl"]!=null){
//remote source                    
                    getSourceContent(dataSource["sourceurl"]);
                }
                else{
//                console.log(JSON.stringify(dataSource.values[lineIndex]));
//source in conf            
                    if(dataSource.values[lineIndex]["speak"]!=null){
                        speak(dataSource.values[lineIndex]["speak"]);
                    }        
                    if(dataSource.values[lineIndex]["img"]!=null){
                        changeImg(dataSource.values[lineIndex]["img"]);
                    }        
                    if(dataSource.values[lineIndex]["position"]==null)
                        newText(dataSource,lineIndex);
                    else 
                        insertText(dataSource,lineIndex);
                }
            }
            
            function playContent(){
                console.log("play:"+playingIndex+"/"+edContent.playing.length);
                dataSource=edContent.playing[playingIndex]["source"];
                if(edContent.playing[playingIndex].image!=null)
                    changeImg(edContent.playing[playingIndex].image);
                
                if(edContent.playing[playingIndex].hint!=null){
                    speak(edContent.playing[playingIndex].hint);
                }
                lineIndex=0;
                writeCode(dataSource);
            }