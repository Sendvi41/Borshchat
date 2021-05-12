import React, {Component} from 'react'
import {Task} from './task';



export class Listtasks extends Component {

    constructor(props) {
        super(props);
        this.state = {tasks: []};
    }
    //
    // componentDidMount() { (2)
    //     client({method: 'GET', path: uri}).done(response => {
    //         this.setState({tasks: response.entity._embedded.tasks});
    //     });
    // }


    render() {
        return (
            <ul>
                <Task/>
                <Task/>
                <Task/>
            </ul>
        )
    }
}