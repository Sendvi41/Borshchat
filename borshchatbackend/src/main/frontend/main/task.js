import React, {Component} from 'react'



export default class Task extends Component {

    constructor( {match}) {
        super(match);
        this.state = {
            id: match.params.userId
        };
    }


    render() {
        return (
            <div>
            <br/>
            <br/>
            <br/>
                <h1>{this.state.id}</h1>
                </div>

        )
    }
}