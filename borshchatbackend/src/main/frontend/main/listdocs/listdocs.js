import React, {Component} from 'react'

import ListDocService from './listservice'




export default class ListDocs extends Component {

    constructor(props) {
        super(props);
        this.state = {
            docs: []};
    }

    componentDidMount() {
        ListDocService.getDocs().then((response)=>{
                this.setState({docs: response.data})
            }
        )
    }


    render() {
        return (
            <section className="content-area">
                <div className="table-area-button">
                    <table className="responsive-table-2 table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Document</th>
                            <th>Name</th>
                            <th>Size</th>
                            <th>Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.docs.map(
                                doc=>
                                    <tr key = {doc.id}>
                                        <td>{doc.id}</td>
                                        <td>{doc.content}</td>
                                        <td>{doc.name}</td>
                                        <td>{doc.size}</td>
                                        <td>{new Date(Date.parse(doc.date+"0000")).toDateString()}<br/>
                                            {new Date(Date.parse(doc.date+"0000")).toTimeString()}</td>
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