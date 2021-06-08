import React, {Component} from 'react'
import icon from './downloaddoc.png'
import icondelete from './deleteicondoc.png'

import ListDocService from './listservice'
import {Link} from 'react-router-dom'
const TASK_REST_API_URL_DOWNLOAD = "document/download";
const TASK_REST_API_URL_DELETE = "document/deletedoc";
import '../css/buttomstyle.css'




export default class ListDocs extends Component {

    constructor(props) {
        super(props);
        this.state = {
            docs: []};
    }

    humanFileSize(size) {
        var i = Math.floor( Math.log(size) / Math.log(1024) );
        return ( size / Math.pow(1024, i) ).toFixed(2) * 1 + ' ' + ['B', 'kB', 'MB', 'GB', 'TB'][i];
    };


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

    getDeleteLink(id){
        fetch(TASK_REST_API_URL_DELETE + "/"+ id.toString(), { method: 'DELETE' });

    }

    render() {


        return (
            <section className="content-area">
                <div className="table-area-button">
                    <table className="responsive-table-2 table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Name</th>
                            <th>Size</th>
                            <th>Date</th>
                            <th>Download</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.docs.map(
                                doc=>
                                    <tr key = {doc.id}>
                                        <td>{doc.id}</td>

                                        <td>{doc.name}</td>
                                        <td>{this. humanFileSize(doc.size)}</td>
                                        <td>{new Date(Date.parse(doc.date+"0000")).toDateString()}<br/>
                                            {new Date(Date.parse(doc.date+"0000")).toTimeString()}</td>
                                        <td>
                                            <a href={this.getLink(doc.id)}><img src={icon} /></a>
                                        </td>
                                        <td>
                                                <button className="buttonstyle" onClick={()=>this.getDeleteLink(doc.id)} ><img src={icondelete} />
                                                </button>
                                        </td>
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