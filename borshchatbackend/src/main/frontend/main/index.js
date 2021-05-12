import React, {Component} from 'react'
import {render} from 'react-dom';
import {Listtasks} from './listtasks';
import 'bootstrap/dist/css/bootstrap.min.css'
import './css/liststyle.css'


class Main extends Component {

    constructor() {
        super();
    }


    render() {
        return (<div>
            <Listtasks/>
        </div>)
    }
}
render(<Main/>, document.getElementById('menu'));