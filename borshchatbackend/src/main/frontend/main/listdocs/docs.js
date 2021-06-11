import React, {Component} from 'react'
import FileUpload from "./fileupload";
import ListDocs from "./listdocs";




export default class Docs extends Component {

    constructor(props) {
        super(props);
    }



    render() {
        return (
            <div>
                <FileUpload/>
                <ListDocs/>
            </div>
        )
    }
}