import React from "react";

import {Navbar, Nav} from "react-bootstrap";
import {Link} from 'react-router-dom';


class NavigationBar extends React.Component{
    render() {
        return(
          <Navbar bg="dark" variant="dark">
              <Link to={""} className="brand">
                  <img src="data:image/png;base64,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"
                       width="25" height="25"/>
              </Link>
              <Nav className="mr-auto">
                  <Link to={"/clientSignUp"} className="nav-link">Sign Up </Link>
                  <Link to={"/clientLogIn"} className="nav-link">Log In   </Link>
              </Nav>
          </Navbar>
        );
    }
}
export default NavigationBar;

