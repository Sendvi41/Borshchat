import React, {Component} from 'react'
import {render} from 'react-dom';
import {Listtasks} from './listtasks';



class Main extends Component {

    constructor() {
        super();
    }


    render() {
        return (<div>
        <h1>Hello world</h1>
            <Listtasks/>
        </div>)
    }
}
render(<Main/>, document.getElementById('menu'));