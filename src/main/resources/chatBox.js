/**
 * Created by sachin on 4/16/2016.
 */
//this function can remove a array element.
Array.remove = function(array, from, to) {
    var rest = array.slice((to || from) + 1 || array.length);
    array.length = from < 0 ? array.length + from : from;
    return array.push.apply(array, rest);
};

//this variable represents the total number of popups can be displayed according to the viewport width
var total_popups = 0;

//arrays of popups ids
var popups = [];

//this is used to close a popup
function close_popup(id)
{
    for(var iii = 0; iii < popups.length; iii++)
    {
        if(id == popups[iii])
        {
            Array.remove(popups, iii);

            //document.getElementById(id).style.display = "none";
            $("#"+id).remove();

            calculate_popups();

            return;
        }
    }
}

//displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
function display_popups()
{
    var right = 220;

    var iii = 0;
    for(iii; iii < total_popups; iii++)
    {
        if(popups[iii] != undefined)
        {
            var element = document.getElementById(popups[iii]);
            element.style.right = right + "px";
            right = right + 320;
            element.style.display = "block";

            var message = $("#"+popups[iii]).find(".popup-messages");

            var heightTop = message[0].scrollHeight;
            message.scrollTop(heightTop);
        }
    }

    for(var jjj = iii; jjj < popups.length; jjj++)
    {
        var element = document.getElementById(popups[jjj]);
        element.style.display = "none";
    }
}

//creates markup for a new popup. Adds the id to popups array.
function register_popup(id, name)
{

    for(var iii = 0; iii < popups.length; iii++)
    {
        //already registered. Bring it to front.
        if(id == popups[iii])
        {
            Array.remove(popups, iii);

            popups.unshift(id);

            calculate_popups();

            return;
        }
    }

    var element = '<div class="popup-box chat-popup" id="'+ id +'">';
    element = element + '<div class="popup-head">';
    element = element + '<div class="popup-head-left">'+ name +'</div>';
    element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
    element = element + '<div style="clear: both"></div></div><div class="popup-messages"></div></div>';
    document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;

    var message = $("#"+id).find(".popup-messages");
    fetchMessage("myId",id);
    var messageDiv = $("<div id='sendMessageDiv'></div>");
    var messageText = $('<textarea rows="3" cols="26" name="'+id+'" id="sendMessageBox" style="margin-top: 15px;margin-left: 3%"></textarea>');
    messageDiv.append(messageText);
    var myId="My Id";
    var sendButton = $("<button style='margin-left: 6%' class='sendMessageButton'>Send</button>");
    sendButton.attr("onClick","sendMessage('"+myId+"','"+id+"')");

    messageDiv.append(sendButton);
    message.append(messageDiv);


    popups.unshift(id);

    calculate_popups();


}

//calculate the total number of popups suitable and then populate the toatal_popups variable.
function calculate_popups()
{
    var width = window.innerWidth;
    if(width < 540)
    {
        total_popups = 0;
    }
    else
    {
        width = width - 200;
        //320 is width of a single popup box
        total_popups = parseInt(width/320);
    }

    console.log(total_popups);

    display_popups();

}

function sendMessage(sender,receiver){
    var chatBox = $("#"+receiver).find(".popup-messages");
    var message = chatBox.find("#sendMessageBox").val();
    chatBox.find("#sendMessageBox").val("");
    passMessageToServer(sender,receiver,message);
}

//recalculate when window is loaded and also when window is resized.
window.addEventListener("resize", calculate_popups);
window.addEventListener("load", calculate_popups);


function passMessageToServer(sender,receiver,message){
    var messageBox = $("#"+receiver).find(".popup-messages");
    var messageSpan = $('<span style="margin-left: 3%" class="mes">'+message+'</span><br/>');
    messageSpan.appendTo(messageBox.find("#messageListDiv").after(messageBox.find("span").lastChild));
    var heightTop = messageBox[0].scrollHeight;
    messageBox.scrollTop(heightTop);

}




function fetchMessage(myId,friendId){
    var message = $("#"+friendId).find(".popup-messages");
    var messageList = $("<div id='messageListDiv'></div>");
    for(var i=0;i<100;i++){
        var messageSpan = $('<span style="margin-left: 3%" class="'+i+'">'+i+'</span><br/>');
        messageSpan.appendTo(messageList);
    }
    message.append(messageList);
}

