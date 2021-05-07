import React, {Component} from 'react'
import {render} from 'react-dom';
import {Launcher} from 'react-chat-window'
import './css/chatstyle.css'
import SockJsClient from "react-stomp";




function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i
        }
    }

    return -1
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}
var id = getRandomInt(1, 10000);
class Demo extends Component {

    constructor() {
        super();
        this.state = {
            messageList: [],
            senderid: id,
            recipientid: null
        };
    }



    _onMessageWasSent(message) {
        console.log(message);
        this.setState({
            messageList: [...this.state.messageList, message]
        })
        // if (message.length > 0) {
        //     console.log("enter to _sendMessage");
        //     this.setState({
        //         messageList: [...this.state.messageList, {
        //             author: 'them',
        //             type: 'text',
        //             data: { message }
        //         }]
        //
        //     })
        // }

        message['senderid'] =this.state.senderid;
        message['recipientid'] =this.state.recipientid;
        if(this.state.recipientid==null){

            this.clientRef.sendMessage('/app/user-all', JSON.stringify( message));
            console.log("Send to user-all ")
        }else{
            this.clientRef.sendMessage('/app/chatdesk', JSON.stringify( message));
            console.log('Send to chatdesk')
        }

    }



    render() {
        return (<div>
            <Launcher
                agentProfile={{
                    teamName: 'Borshchat',
                    imageUrl: 'https://static.wixstatic.com/media/81d525_68448533e55943ee950091135534c418~mv2.png/v1/fill/w_34,h_34,al_c,usm_0.66_1.00_0.01/81d525_68448533e55943ee950091135534c418~mv2.png'
                }}

                onMessageWasSent={this._onMessageWasSent.bind(this)}
                messageList={this.state.messageList}
                showEmoji


            />
            <SockJsClient url='http://localhost:8080/websocket-chat/'
                          topics={["/user/" + this.state.senderid + "/queue/messages"]}
                          // topics={["/user/queue/messages"]}
                          // topics={['/topic/user']}
                          onConnect={() => {
                              console.log("connected");
                          }}
                          onDisconnect={() => {
                              console.log("Disconnected");
                          }}
                          onMessage={(msg) => {

                              msg['author']='them';
                              if(msg.recipientid!=null){
                                  this.setState({
                                      recipientid: msg.recipientid
                                  })
                              }
                              console.log(msg);
                              this.setState({
                                  messageList: [...this.state.messageList, msg]
                              })
                              console.log(this.state);
                          }}
                          ref={(client) => {
                              this.clientRef = client
                              console.log("Client got correct")
                          }}/>
        </div>)
    }
}
render(<Demo/>, document.getElementById('app'));