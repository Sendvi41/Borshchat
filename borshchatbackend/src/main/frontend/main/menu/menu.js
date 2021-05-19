import React, {Component} from 'react'
import './menu.css'
import Rout from './router'
import { Link, BrowserRouter as Router, Route } from 'react-router-dom';
import Listtasks from "../listtasks";


export default class Menu extends Component {

    constructor(props) {
        super(props);
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
                    </ul>
                </div>
                <Rout></Rout>
            </div>
        )
    }
}


