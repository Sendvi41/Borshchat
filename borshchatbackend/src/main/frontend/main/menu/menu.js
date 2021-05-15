import React, {Component} from 'react'
import './menu.css'




export default class Menu extends Component {

    constructor(props) {
        super(props);
    }


    render() {
        return (
            <div className="nav">
                <div className="toggle"></div>
                <ul>
                    <li><a href="/">Tasks</a></li>
                    <li><a href="#">Documentation</a></li>
                    <li><a href="#">Statistics</a></li>
                    <li><a href="#">Administration</a></li>
                    <li><a href="#">Download</a></li>
                </ul>
            </div>
        )
    }
}


