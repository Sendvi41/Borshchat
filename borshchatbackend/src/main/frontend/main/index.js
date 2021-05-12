import React, {Component} from 'react'
import {render} from 'react-dom';



class Main extends Component {

    constructor() {
        super();
    }



    render() {
        return (<div>
        <h1>Hello world</h1>
        </div>)
    }
}
render(<Main/>, document.getElementById('menu'));