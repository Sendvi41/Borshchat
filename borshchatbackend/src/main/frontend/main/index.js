import React, {Component} from 'react'
import {render} from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './css/liststyle.css'

import Menu from "./menu/menu";
import AuthorizationForm from "./login/autorizationform"


class Main extends Component {

    constructor() {
        super();
        this.state = {
            authorization: JSON.parse(localStorage.getItem('authorization'))||false
        };


    }

    onChangeState() {
        console.log("onChangeState");
        this.setState({
            authorization : true
        }, ()=>localStorage.setItem('authorization', JSON.stringify(this.state.authorization)))
    }


    render() {
        return (
                <div>
                    {
                        this.state.authorization ?
                            (<Menu/>)
                            : (<AuthorizationForm onChangeState={this.onChangeState.bind(this)}/>
                            )}
                </div>
        )

    }
}

render(<Main/>, document.getElementById('menu'));