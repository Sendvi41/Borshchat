import React, {Component, useContext} from "react";
import LogInService from "./logInservice"
import Menu from "../menu/menu";
import AuthorizationForm from "./autorizationform";

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            password: '',
            error: false
        };
    }

    handleChangeName = (name) => {
        this.setState({name: name})
    }


    handleChangePassword(password) {
        this.setState({password: password})
    }


    onLogin(event) {
        event.preventDefault();
        console.log("onLogin");
        LogInService.valiadte(this.state.name, this.state.password)
            .then((response) => {
                    if (response.status === 200) {
                        this.props.onChangeState();
                    }
                }
            ).catch((exception) => {
                if(exception.response.status===404){
                    this.setState({
                        error: true
                    })
                }
            }
        )


        // if (this.props.onChangeState) {
        //     this.props.onChangeState();
        // }

    }


    render() {
        return (
            <form>
                <h3>Sign In</h3>

                <div className="form-group">
                    <label className="navlabel">Name</label>
                    <input type="name" className="form-control" placeholder="Enter name"
                           onChange={(event) => this.handleChangeName(event.target.value)}/>
                </div>

                <div className="form-group">
                    <label className="navlabel">Password</label>
                    <input type="password" className="form-control" placeholder="Enter password"
                           onChange={(event) => this.handleChangePassword(event.target.value)}/>
                </div>

                <div className="form-group">
                    <div className="navlabel custom-control custom-checkbox">
                        <input type="checkbox" className="custom-control-input" id="customCheck1"/>
                        <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                    </div>
                </div>
                {
                    this.state.error ?
                        (<div className="form-group">
                            <label>Password or login entered incorrectly</label>
                        </div>)
                        : (<div/>)
                }


                <button type="submit" className="btn btn-primary btn-block"
                        onClick={(event) => this.onLogin(event)}>Sign in
                </button>
                <p className="forgot-password text-right">
                    Forgot <a href="#">password?</a>
                </p>
            </form>
        );
    }
}