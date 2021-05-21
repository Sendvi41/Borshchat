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
            authorization: JSON.parse(localStorage.getItem('authorization'))||false,
            consultant: JSON.parse(localStorage.getItem('consultant'))|| {}
        };


    }

    onChangeState() {
        console.log("onChangeState");
        this.setState({
            authorization : true
        }, ()=>localStorage.setItem('authorization', JSON.stringify(this.state.authorization)))
    }
    onSetConsultant(consultant) {
        this.setState({
            consultant : consultant
        }, ()=>localStorage.setItem('consultant', JSON.stringify(this.state.consultant)))
    }



    render() {
        return (
                <div>
                    {
                        this.state.authorization ?
                            (<Menu consultant={this.state.consultant}/>)
                            : (<AuthorizationForm onChangeState={this.onChangeState.bind(this)}
                                                  onSetConsultant={this.onSetConsultant.bind(this)}/>
                            )}
                </div>
        )

    }
}

render(<Main/>, document.getElementById('menu'));