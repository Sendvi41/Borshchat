import React, {Component} from 'react'
import Listtasks from '../listtasks';
import Docs from '../docs';
import { BrowserRouter as Router } from 'react-router-dom';
import {Route,Switch} from 'react-router-dom';


export default class Rout extends Component {

    constructor(props) {
        super(props);
    }


    render() {
        return (
            <div>
                <Router>
                    <Switch>
                        <Route exact path="/" component={Listtasks}/>
                        <Route exact path="/docs" component={Docs}/>
                    </Switch>
                </Router>
            </div>
        )
    }
}


