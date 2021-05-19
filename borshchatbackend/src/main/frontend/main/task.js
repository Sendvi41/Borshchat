import React, {Component} from 'react'
import TaskService from "./taskservice";
import "./css/onetaskstyle.css"


export default class Task extends Component {

    constructor({match}) {
        super(match);
        this.state = {
            id: match.params.userId,
            task: {}
        };
    }


    componentDidMount() {
        TaskService.getOnetTask(this.state.id).then((response) => {
                this.setState({task: response.data})
                console.log(response.data.toString())
            }
        )
    }


    render() {
        return (
            <div>
                <br/>
                <br/>
                <div className="task_form">
                    <ul>
                        <li>
                            <h2>Task#id {this.state.id} </h2>
                            <span className="labelbold">{this.state.task.theme}</span>
                        </li>
                        <div className="size_block">
                        <li>
                            <label>Client's name: {this.state.task.nameclient} </label>
                            <label>Date: {new Date(Date.parse(this.state.task.date + "0000")).toDateString()}
                                {new Date(Date.parse(this.state.task.date + "0000")).toTimeString()}</label>
                        </li>
                        <li>
                            <label>Client's surname: {this.state.task.surnameclient}</label>
                            <label>Author: {this.state.task.consultant_id && this.state.task.consultant_id.name}</label>
                        </li>
                        <li>
                            <label>Client's patronymic: {this.state.task.patronymicclient}</label>
                            <label>Consult's
                                id: {this.state.task.consultant_id && this.state.task.consultant_id.id}</label>
                        </li>
                        <li>
                            <label>E-mail: {this.state.task.email}</label>
                            <label>Tracker: {this.state.task.tracker}</label>
                        </li>
                        <li>
                            <label>Priority of task: {this.state.task.priority}</label>
                        </li>
                        </div>
                    </ul>
                    <label className="description">Description</label>
                    <br/>
                    <label className="underline">{this.state.task.comment}</label>
                </div>
            </div>

        )
    }
}