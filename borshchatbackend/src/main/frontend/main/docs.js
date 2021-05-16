import React, {Component} from 'react'
import FileUpload from "./listdocs/fileupload";




export default class Docs extends Component {

    constructor(props) {
        super(props);
    }



    render() {
        return (
            <div><FileUpload/></div>
        )
    }
}