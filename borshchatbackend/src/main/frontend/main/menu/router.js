import React, {Component} from 'react'
import Listtasks from '../listtasks';
import { BrowserRouter as Router } from 'react-router-dom';
import {Route,Switch} from 'react-router-dom';


export default class Rout extends Component {

    constructor(props) {
        super(props);
    }


    render() {
        return (
                <Router>
                    <Switch>
                        <Route path="/" component={Listtasks}/>
                        <Route path="/docs" component={Listtasks}/>
                    </Switch>
                </Router>
        )
    }
}


