import React from 'react'
import axios from 'axios';
import '../css/uploadstyle.css'
import icon from './upload.png'

const TASK_REST_API_URL_UPLOAD = "http://localhost:8080/document/uploaddoc";

class FileUpload extends React.Component {

    constructor() {
        super();
        this.state = {
            selectedFile: '',
        }

        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        this.setState({
            selectedFile: event.target.files[0],
        })
    }

    submit() {
        const data = new FormData()
        data.append('document', this.state.selectedFile)
        console.warn(this.state.selectedFile);


        axios.post(TASK_REST_API_URL_UPLOAD, data, { // receive two parameter endpoint url ,form data
        })
            .then(res => { // then print response status
                console.warn(res);
            })

    }

    render() {
        return (

            <div className="position">
                <div className="input__wrapper">
                    <input type="file"  className="input input__file" id="input__file" name="upload_file" onChange={this.handleInputChange}/>
                        <label htmlFor="input__file" className="input__file-button">
                            <span className="input__file-icon-wrapper"><button type="submit" className="btn"
                                                                               onClick={() => this.submit()}>
                                <img className="input__file-icon"
                                                                            src={icon} alt="Выбрать файл"
                                     height="30" width="25"/>    </button></span>
                            <span className="input__file-button-text" >Select file</span>
                        </label>
                </div>
            </div>

        )
    }
}

export default FileUpload;