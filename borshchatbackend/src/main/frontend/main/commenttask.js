import React, {Component} from 'react'
import "./css/commentstyle.css"


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
                <div className="formcomment">
                    <div key={this.state.id}>
                        <label className="header">{this.state.nameconsult} <span
                            style={{fontStyle: 'italic'}}>
                            {new Intl.DateTimeFormat('en-US', {year: 'numeric', month: '2-digit',day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit'}).format(new Date(Date.parse(this.state.date + "0000"))).toString()}
                        </span>
                        </label>
                        <br/>
                        <span className="textcomment">{this.state.comment}</span>
                    </div>
                </div>


            </div>


        )
    }
}