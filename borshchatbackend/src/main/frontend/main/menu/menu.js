import React, {Component} from 'react'
import './menu.css'
import Rout from './router'



export default class Menu extends Component {

    constructor(props) {
        super(props);

    }
    componentDidMount() {
        document.body.style.backgroundImage = "none";
    }

    render() {
        return (
            <div className="pos">
                <div className="nav">
                    <ul>
                        <li><a href="/tasks">Tasks</a></li>
                        <li><a href="/docs">Documentation</a></li>
                        <li><a href="/stat">Statistics</a></li>
                        <li><a href="/admin">Administration</a></li>
                        <li><a href="/load">Download</a></li>
                        <li><div>{this.props.consultant.name}</div></li>
                    </ul>

                </div>
                <Rout></Rout>
            </div>
        )
    }
}


