import React, {Component} from 'react'
import {render} from 'react-dom';
import {Launcher} from 'react-chat-window'
import './css/chatstyle.css'

class Demo extends Component {

    constructor() {
        super();
        this.state = {
            messageList: []
        };
    }

    _onMessageWasSent(message) {
        this.setState({
            messageList: [...this.state.messageList, message]
        })
    }

    _sendMessage(text) {
        if (text.length > 0) {
            this.setState({
                messageList: [...this.state.messageList, {
                    author: 'them',
                    type: 'text',
                    data: { text }
                }]
            })
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
        </div>)
    }
}
render(<Demo/>, document.getElementById('app'));