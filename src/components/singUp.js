import {Component} from "react";
import {Card, Form, Button} from 'react-bootstrap';
import axios from 'axios';

class signUp extends Component {

    constructor(props) {
        super(props);
        this.state = { username: '', password: '', rePassword: '', address: '', checkAdmin: 'false'};
        this.createUser = this.createUser.bind(this);
        this.userChange = this.userChange.bind(this);
        this.checkBoxChange = this.checkBoxChange.bind(this);
    }

    createUser = event => {
        if(this.state.password === this.state.rePassword) {
            if(this.state.checkAdmin==='false') {
                const client = {
                    username: this.state.username,
                    password: this.state.password,
                    address: this.state.address
                }

                axios.post("/authcontroller/signupClient", client).then(response => {
                    if (response.data != null) {
                        alert("Added Client");
                    }
                    else{
                        alert("Username taken or missing info");
                    }
                })
            }
            else{
                const admin = {
                    username: this.state.username,
                    password: this.state.password,
                }

                axios.post("/authcontroller/signupAdmin", admin).then(response => {
                    if (response.data != null) {
                        alert("Added Admin");
                    }
                    else {
                        alert("Username taken");
                    }
                })
            }
        }
        else{
            alert("Passwords do not match");
        }
    }

    userChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    checkBoxChange(event) {
        let isChecked = event.target.checked;
        if (isChecked) {
            this.setState({[event.target.name]: 'true'});
        } else {
            this.setState({[event.target.name]: 'false'});
        }
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> Sign Up</Card.Header>
                <Card.Body>
                    <Form id="userForm" onSubmit={this.createUser}>
                        <Form.Group controlId="UserGroup">
                            <Form.Label>Username</Form.Label>
                            <Form.Control required type="text" name="username" value={this.state.username}
                                          onChange={this.userChange} placeholder="Username"/>
                        </Form.Group>

                        <Form.Group controlId="PassGroup">
                            <Form.Label>Password</Form.Label>
                            <Form.Control required type="password" name="password" value={this.state.password}
                                          onChange={this.userChange} placeholder="Password"/>
                        </Form.Group>

                        <Form.Group controlId="RePassGroup">
                            <Form.Label>Repeat Password</Form.Label>
                            <Form.Control required type="password" name="rePassword" value={this.state.rePassword}
                                          onChange={this.userChange} placeholder="Password"/>
                        </Form.Group>

                        <Form.Group controlId="AddressGroup">
                            <Form.Label>Address</Form.Label>
                            <Form.Control type="text" name="address" value={this.state.address}
                                          onChange={this.userChange} placeholder="Address"/>
                        </Form.Group>

                        <Form.Group controlId="CheckGroup">
                            <Form.Check type="checkbox" name="checkAdmin" value={this.state.checkAdmin}
                                        onChange={this.checkBoxChange} label="Admin"/>
                        </Form.Group>

                        <Button variant="success" type="submit">
                            Sign Up
                        </Button>

                    </Form>
                </Card.Body>
            </Card>
        );
    }
}

export default signUp;
