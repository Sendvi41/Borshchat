import React, {Component} from 'react'
import TaskService from "./taskservice";
import "../css/onetaskstyle.css"
import Comment from "./commenttask"
import iconedit from './editdocument.png'
import Select from 'react-select';



const optionsTracker = [
    {value: 'Bug', label: 'Bug'},
    {value: 'Support', label: 'Support'}
]
const optionsPriority = [
    {value: 'High', label: 'High'},
    {value: 'Normal', label: 'Normal'},
    {value: 'Low', label: 'Low'},
    {value: 'Immediate', label: 'Immediate'}
]
const optionsStatus = [
    {value: 'New', label: 'New'},
    {value: 'In process', label: 'In process'},
    {value: 'Finished', label: 'Finished'}
]

const colourStyles = {
    control: styles => ({ ...styles, backgroundColor: 'white' }),
    option: (styles, { data, isDisabled, isFocused, isSelected }) => {
        return {

            backgroundColor: isDisabled ? 'red' : blue,
            color: '#FFF',
            cursor: isDisabled ? 'not-allowed' : 'default',

        };
    },
};

export default class Task extends Component {


    constructor({match}) {
        super(match);
        this.state = {
            id: match.params.userId,
            consultant: JSON.parse(localStorage.getItem('consultant')) || {},
            task: {},
            comments: [],
            comment: '',
            editForm: false
        };
    }


    componentDidMount() {
        TaskService.getOnetTask(this.state.id).then((response) => {
                this.setState({task: response.data})
                console.log(response.data)
            }
        ).then(() => {
                TaskService.getComments(this.state.task.id).then(
                    (response) => {
                        console.log(response.data)
                        this.setState({comments: response.data})
                    }
                )
            }
        )
    }

    functionEdit = () => {
        this.setState({editForm: true})
    }

    submitEdit = () => {
        this.setState({editForm: false})
    }

    handleChangeComment = (comment) => {
        this.setState({comment: comment})
    }

    addComment = () => {
        if (this.state.comment !== "") {
            console.log("add comemnt")
            TaskService.addOneComment(this.state.comment, this.state.consultant,
                new Date(), this.state.task.id)
                .then((response) => {
                        if (response.status === 201) {
                            TaskService.getComments(this.state.task.id).then(
                                (response) => {
                                    console.log(response.data)
                                    this.setState({
                                        comments: response.data,
                                        comment: ''
                                    })
                                }
                            )
                        }
                    }
                ).catch((exception) => {
                    console.log(exception.response.status)
                }
            )
        }
    }

    render() {
        return (
            <div>
                <br/>
                <br/>
                <h2 className="title">Task#id {this.state.id} </h2>
                <div className="form">
                    <label className="labelbold">{this.state.task.theme}
                        {this.state.editForm ? (<button className="labelboldbutton" onClick={this.submitEdit}>
                                <img src={iconedit}/>
                                Confirm changes
                            </button>) :
                            (<button className="labelboldbutton" onClick={this.functionEdit}>
                                <img src={iconedit}/>
                                Edit
                            </button>)
                        }
                    </label>
                    <div className="task_form">

                        <div className="size_block">

                            <label>Client's name: <span>{this.state.task.nameclient}</span> </label>
                            <label>Client's surname: <span>{this.state.task.surnameclient}</span> </label>
                            <label>Client's patronymic: <span>{this.state.task.patronymicclient}</span></label>
                            <label>E-mail:<span> {this.state.task.email}</span></label>
                            <label>Priority of task: <span>{this.state.task.priority}</span>


                            </label>
                            <label>Date: <span>{this.state.task.date && new Intl.DateTimeFormat('en-US', {
                                year: 'numeric',
                                month: '2-digit',
                                day: '2-digit',
                                hour: '2-digit',
                                minute: '2-digit',
                                second: '2-digit'
                            }).format(new Date(Date.parse(this.state.task.date + "0000"))).toString()}</span></label>

                            <label>Author:<span> {this.state.task.consultant_id && this.state.task.consultant_id.name}</span></label>

                            <label>Consult's
                                id: <span>{this.state.task.consultant_id && this.state.task.consultant_id.id}</span></label>

                            <label className="select" >Tracker:{!this.state.editForm ? (<span>{this.state.task.tracker}</span>) :
                                (   <select value={this.state.task.tracker}>
                                    <option value="Bug">Bug</option>
                                    <option value="Support">Support</option>
                                </select>
                                )
                            }

                            </label>
                            <label>Status: <span>{this.state.task.status}</span></label>


                        </div>

                        <label className="description">Description</label>
                        <br/>
                        <label className="underline">{this.state.task.comment}</label>
                    </div>
                </div>
                <div>
                    {this.state.comments && this.state.comments.map(comment =>
                        <Comment comment={comment}/>
                    )}
                </div>
                <div className="commentform">
                    <label className="headercomment">{this.state.consultant.name}
                        <button className="send" onClick={this.addComment}>Submit сomment</button>
                    </label>

                    <br/>
                    <label>
                        <textarea
                            className="text-area-form"
                            name="comment"
                            value={this.state.comment}
                            onChange={(event) => this.handleChangeComment(event.target.value)}></textarea>
                    </label>

                </div>


            </div>

        )
    }
}