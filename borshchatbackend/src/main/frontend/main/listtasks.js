import React, {Component} from 'react'

import TaskService from './taskservice'
import { Link, BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Task from "./task";





export default class Listtasks extends Component {

    constructor(props) {
        super(props);
        this.state = {
            tasks: []};
    }

    componentDidMount() {
        TaskService.getTasks().then((response)=>{
            this.setState({tasks: response.data})
            }
        )
    }


    render() {
        return (

            <section className="content-area">
                <div className="table-area">
               <table className="responsive-table table">
                   <thead>
                       <tr>
                           <th>id</th>
                           <th>Tracker</th>
                           <th>Status</th>
                           <th>Priority</th>
                           <th>Theme</th>
                           <th>Author</th>
                           <th>Date</th>
                       </tr>
                   </thead>
                   <tbody>
                   {
                       this.state.tasks.map(
                            task=>
                                <tr key = {task.id}>

                                    <td>  <Link to={`/task/${task.id}`}>{task.id}</Link></td>
                                    <td>{task.tracker}</td>
                                    <td>{task.status}</td>
                                    <td>{task.priority}</td>
                                    <td>{task.theme}</td>
                                    <td>{task.consultant_id.name}</td>
                                    <td>{new Date(Date.parse(task.date+"0000")).toDateString()}<br/>
                                    {new Date(Date.parse(task.date+"0000")).toTimeString()}</td>
                                </tr>
                       )
                   }
                   </tbody>

               </table>
                </div>
            </section>


        )
    }
}