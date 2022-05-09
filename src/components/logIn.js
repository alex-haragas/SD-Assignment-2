import {Component} from "react";
import {Card,Form,Button} from 'react-bootstrap';
import axios from "axios";
import {useHistory} from "react-router-dom";


class logIn extends Component {

    constructor(props) {
        super(props);
        this.state={username:'', password:''};
        this.logUser=this.logUser.bind(this);
        this.userChange=this.userChange.bind(this);
    }

    logUser(event){
        const user={
            username: this.state.username,
            password: this.state.password
        }
        axios.post('/authcontroller/logIn', user).then(response => {
            if (response.data != null) {
                localStorage.setItem('user',JSON.stringify(response.data))
                if(response.data.role==='ADMIN')
                    this.props.history.push('admins/'+response.data.username);
                if(response.data.role==='CLIENT')
                    this.props.history.push('clients/'+response.data.username);
            }
            else{
                alert("Username or password wrong");
            }
        })
        event.preventDefault();
    }

    userChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }


    render(){
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> Log In</Card.Header>
                <Card.Body>
                    <Form id="userForm" onSubmit={this.logUser}>
                        <Form.Group controlId="UserGroup">
                            <Form.Label>Username</Form.Label>
                            <Form.Control required type="text" name="username" value={this.state.username}
                                          onChange={this.userChange}  placeholder="Username" />
                        </Form.Group>

                        <Form.Group controlId="PassGroup">
                            <Form.Label>Password</Form.Label>
                            <Form.Control required type="password" name="password"  value={this.state.password}
                                          onChange={this.userChange} placeholder="Password" />
                        </Form.Group>


                        <Button variant="success" type="submit">
                            Log In
                        </Button>

                    </Form>
                </Card.Body>
            </Card>
        );
    }
}

export default logIn;
