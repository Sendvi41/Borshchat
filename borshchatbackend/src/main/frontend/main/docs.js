import React, {Component} from 'react'
import FileUpload from "./listdocs/fileupload";
import ListDocs from "./listdocs/listdocs";




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