import {Component} from "react";
import {Card, Form, ButtonGroup, Button, Table} from 'react-bootstrap';
import axios from 'axios';

class clientRestaurantPage extends Component {

    constructor(props) {
        super(props);
        this.state = {restaurants: [], sName: '', cart: [],price:0};
        this.restaurantChange = this.restaurantChange.bind(this);
        this.username = this.props.match.params.username;
        this.addFood = this.addFood.bind(this);
        this.deleteFood = this.deleteFood.bind(this);
        this.addOrder=this.addOrder.bind(this);
    }


    async componentDidMount() {
        axios.post("/restaurant/all").then(response => response.data).then(
            (data) => {
                this.setState({restaurants: data});
            }
        );
    }


    restaurantChange = event => {
        if(this.state.cart.length===0) {
            this.setState({
                [event.target.name]: event.target.value
            });
        }
    }

    addFood(value,value2) {
        this.state.cart.length === 0 ?
            this.setState({cart: [value],sName:value2.name,price:this.state.price+value.price}) :
            this.setState({cart: [...this.state.cart, value],price:this.state.price+value.price})
    }

    deleteFood(value) {
        this.setState({
            cart: this.state.cart.filter(function (food) {
                return food !== value
            })
        ,price:this.state.price-value.price})
        if(this.state.cart.length===0)
            this.setState({sName:''})

    }

    addOrder (){
        if(this.state.cart.length!==0) {
            const addVal = {
                foods: [],
                username: this.username,
                resName: this.state.sName
            }
            this.state.cart.map((food)=>
                addVal.foods=[...addVal.foods,food.name])
            axios.post("/orders/add",addVal).then(response=>{
                if(response.data!=null){
                    alert("Created order");
                    this.setState({cart:[],sName:''})
                }
                }
            )
        }
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> Restaurants </Card.Header>
                <Card.Body>
                    <p>Cart</p>
                    <Table bordered hover striped variant="dark">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.cart.length === 0 ?
                            <tr></tr> :
                            this.state.cart.map((food) => (
                                <tr key={food.id}>
                                    <td>{food.name}</td>
                                    <td>{food.price}</td>
                                    <ButtonGroup>
                                        <Button onClick={() => this.deleteFood(food)}>remove </Button>
                                    </ButtonGroup>
                                </tr>
                            ))

                        }
                        </tbody>
                    </Table>
                    <p></p>
                    Price {this.state.price}
                    <p></p>
                    <Button onClick={()=>this.addOrder()}>Create Order</Button>
                    <p></p>
                    <Form>
                        <Form.Group controlId="UserGroup">
                            <Form.Label>Search Restaurant</Form.Label>
                            <Form.Control required type="text" name="sName" value={this.state.sName}
                                          onChange={this.restaurantChange} placeholder="Name"/>
                        </Form.Group>
                    </Form>


                    <Table bordered hover striped variant="dark">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Location</th>
                            <th>Menu</th>
                        </tr>
                        </thead>
                        <tbody>
                        {

                            this.state.restaurants.length === 0 ?
                                <tr></tr> :
                                this.state.restaurants.map((restaurant) => (
                                    restaurant.name === this.state.sName || this.state.sName === '' ?
                                        <tr key={restaurant.id}>
                                            <td> {restaurant.name}</td>
                                            <td> {restaurant.location}</td>
                                            <Table bordered hover striped variant="dark">
                                                <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Description</th>
                                                    <th>Price</th>
                                                    <th>Category</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                {restaurant.foods.map((food) => (
                                                    <tr key={food.id}>
                                                        <td>{food.name}</td>
                                                        <td>{food.description}</td>
                                                        <td>{food.price}</td>
                                                        <td>{food.category}</td>
                                                        <ButtonGroup>
                                                            <Button onClick={() => this.addFood(food,restaurant)}>add </Button>
                                                        </ButtonGroup>
                                                    </tr>
                                                ))}
                                                </tbody>
                                            </Table>
                                        </tr> :


                                        <tr></tr>
                                ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default clientRestaurantPage;
