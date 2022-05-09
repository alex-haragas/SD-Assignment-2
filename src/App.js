import './App.css';
import React from "react";

import signUp from "./components/singUp";
import {BrowserRouter as Router,Switch,Route} from 'react-router-dom';
import {Container,Row,Col} from "react-bootstrap";
import NavigationBar from "./components/navigationBar";
import logIn from "./components/logIn";
import clientPage from "./components/clientPage";
import adminPage from "./components/adminPage";
import restaurantPage from "./components/restaurantsPage";
import adminFoodPage from "./components/adminFoodPage";
import adminOrderPage from "./components/adminOrderPage";
import clientRestaurantPage from "./components/clientRestaurantPage";
import clientOrderPage from "./components/clientOrderPage";

function App() {
  const marginTop={marginTop:"20px"};

    return (
      <Router>
        <NavigationBar/>
        <Container>
          <Row>
            <Col lg={12} style={marginTop}>
              <Switch>
                  <Route path="/clientSignUp" exact component={signUp}/>
                  <Route path="/clientLogIn" exact component={logIn}/>
                  <Route path="/clients/:username/" exact component={clientPage}/>
                  <Route path="/clients/:username/restaurants" exact component={clientRestaurantPage}/>
                  <Route path="/clients/:username/orders" exact component={clientOrderPage}/>
                  <Route path="/admins/:username/" exact component={adminPage}/>
                  <Route path="/admins/:username/restaurant" exact component={restaurantPage}/>
                  <Route path="/admins/:username/food" exact component={adminFoodPage}/>
                  <Route path="/admins/:username/orders" exact component={adminOrderPage}/>
              </Switch>
            </Col>
            </Row>
        </Container>
      </Router>
    );
}

export default App;
