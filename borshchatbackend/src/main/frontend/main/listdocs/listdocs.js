import React, {Component} from 'react'

import ListDocService from './listservice'
import {Link} from 'react-router-dom'
const TASK_REST_API_URL_DOWNLOAD = "document/download";




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


    // download(arrayBuffer) {
    //     var array = new Uint16Array([arrayBuffer]);
    //     var blob = new Blob([array], { type: 'application/octet-stream' });
    //     var url = URL.createObjectURL(blob);
    //     window.open(url, "createdocument.docx");
    //
    //         window.URL.createObjectURL(new Blob(), { type:'application/octet-stream' })}
    // }

    getLink(id){
        return TASK_REST_API_URL_DOWNLOAD + "?id="+ id.toString();
    }

    openLink(id){
        window.open(TASK_REST_API_URL_DOWNLOAD + "?id="+ id.toString())
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
                                        <td>
                                            <a href={this.getLink(doc.id)}>Скачать</a>
                                            {/*<Link to={} activeClassName="active">Скачать</Link>*/}
                                        </td>
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