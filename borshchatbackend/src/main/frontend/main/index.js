import React, {Component} from 'react'
import {render} from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './css/liststyle.css'
import Menu from "./menu/menu";



class Main extends Component {

    constructor() {
        super();
    }


    render() {
        return (
            <div>
                <Menu/>
            </div>)
    }
}

render(<Main/>, document.getElementById('menu'));