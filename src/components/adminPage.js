import React from "react";

import {Nav, Navbar} from "react-bootstrap";
import {Link, useParams} from "react-router-dom";

class adminPage extends React.Component{
   constructor(props) {
       super(props);
       this.username=this.props.match.params.username;
   }

    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Nav className="mr-auto">
                    <Link to={this.username+"/restaurant"} className="nav-link">Modify Restaurant  </Link>
                    <Link to={this.username+"/food"} className="nav-link">Modify Food   </Link>
                    <Link to={this.username+"/orders"} className="nav-link">See Orders </Link>
                </Nav>
            </Navbar>
        );
    }
}

export default adminPage;