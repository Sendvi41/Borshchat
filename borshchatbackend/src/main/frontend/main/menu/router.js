import React, {Component} from 'react'
import Listtasks from '../tasks/listtasks';
import Docs from '../listdocs/docs';
import { BrowserRouter as Router } from 'react-router-dom';
import {Route,Switch} from 'react-router-dom';
import Task from '../tasks/task'

export default class Rout extends Component {

    constructor(props) {
        super(props);


    }


    render() {
        return (
            <div>
                <Router>
                    <Switch>
                        <Route exact path="/tasks" component={Listtasks}/>
                        <Route exact path="/docs" component={Docs}/>
                        <Route exact path="/task/:userId" component={Task} />
                    </Switch>
                </Router>
            </div>
        )
    }
}


