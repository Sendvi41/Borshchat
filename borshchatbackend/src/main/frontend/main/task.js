import React, {Component} from 'react'
import TaskService from "./taskservice";
import "./css/onetaskstyle.css"
import Comment from "./commenttask"
import LogInService from "./login/logInservice";


export default class Task extends Component {

    constructor({match}) {
        super(match);
        this.state = {
            id: match.params.userId,
            consultant: JSON.parse(localStorage.getItem('consultant')) || {},
            task: {},
            comment: ''
        };
    }


    componentDidMount() {
        TaskService.getOnetTask(this.state.id).then((response) => {
                this.setState({task: response.data})
                console.log(response.data)
            }
        )
    }

    handleChangeComment = (comment) => {
        this.setState({comment: comment})
    }

    addComment = () => {
        console.log("add comemnt")
        TaskService.addOneComment(this.state.comment, this.state.consultant,
            new Date(), this.state.task.id).then((response) => {
                if (response.status === 200) {
                    this.componentDidMount();
                }
            }
        ).catch((exception) => {
                console.log(exception.response.status)
            }
        )
    }

    render() {
        return (
            <div>
                <br/>
                <br/>
                <h2 className="title">Task#id {this.state.id} </h2>
                <div className="form">
                    <label className="labelbold">{this.state.task.theme}</label>
                    <div className="task_form">

                        <div className="size_block">

                            <label>Client's name: <span>{this.state.task.nameclient}</span> </label>
                            <label>Client's surname: <span>{this.state.task.surnameclient}</span> </label>
                            <label>Client's patronymic: <span>{this.state.task.patronymicclient}</span></label>
                            <label>E-mail:<span> {this.state.task.email}</span></label>
                            <label>Priority of task: <span>{this.state.task.priority}</span></label>
                            <label>Date: <span>{new Date(Date.parse(this.state.task.date + "0000")).toDateString()}
                                {new Date(Date.parse(this.state.task.date + "0000")).toTimeString()}</span></label>

                            <label>Author:<span> {this.state.task.consultant_id && this.state.task.consultant_id.name}</span></label>

                            <label>Consult's
                                id: <span>{this.state.task.consultant_id && this.state.task.consultant_id.id}</span></label>

                            <label>Tracker: <span>{this.state.task.tracker}</span></label>
                            <label>Status: <span>{this.state.task.status}</span></label>


                        </div>

                        <label className="description">Description</label>
                        <br/>
                        <label className="underline">{this.state.task.comment}</label>
                    </div>
                </div>
                <div>
                    {this.state.task.comments && this.state.task.comments.map(comment =>
                        <Comment comment={comment}/>
                    )}
                </div>
                <div>
                    <label>Имя: {this.state.consultant.name} </label>
                    <label>Комментарий:
                        <textarea
                        name="comment"
                        onChange={(event) => this.handleChangeComment(event.target.value)}></textarea>
                    </label>
                    <button onClick={this.addComment}>Добавить комментарий</button>

                </div>


            </div>

        )
    }
}