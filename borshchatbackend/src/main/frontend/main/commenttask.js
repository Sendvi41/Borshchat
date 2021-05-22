import React, {Component} from 'react'


export default class Comment extends Component {

    constructor({comment}) {
        super(comment);
        this.state = {
            id: comment.id,
            comment: comment.comment,
            nameconsult: comment.consultant_id.name,
            date: comment.date
        };
    }

    componentDidMount() {
    }


    render() {
        return (
            <div>
                <div>
                    <div key={this.state.id}>
                        <span
                            style={{fontStyle: 'italic'}}>{this.state.comment} - {format(this.state.date, 'DD/MM/YYYY')}: </span>
                        <strong>{this.state.nameconsult}, </strong>
                        <span>{this.state.comment}</span>
                        <button
                            // onClick={this.removeComment.bind(null, comment.id)}
                        >Удалить комментарий
                        </button>
                    </div>
                </div>


            </div>


        )
    }
}