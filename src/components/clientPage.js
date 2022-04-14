import React from "react";
import {Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router-dom";



class clientPage extends React.Component{
    constructor(props) {
        super(props);
        this.username=this.props.match.params.username;
    }


    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Nav className="mr-auto">
                    <Link to={this.username+"/restaurants"} className="nav-link">Restaurant  </Link>
                    <Link to={this.username+"/orders"} className="nav-link">See Orders </Link>
                </Nav>
            </Navbar>
        );
    }
}

export default clientPage;