import {Component} from "react";
import {Card, Form, Button, Table} from 'react-bootstrap';
import axios from 'axios';

class adminFoodPage extends Component {

    constructor(props) {
        super(props);
        this.state = {foods:[], name: '', desc: '',price:'',categ:'',sCateg:''};
        this.createRestaurant = this.createRestaurant.bind(this);
        this.restaurantChange = this.restaurantChange.bind(this);
        this.username=this.props.match.params.username;
    }


    async componentDidMount(){
        axios.get("/food/"+this.username).then(response=>response.data).then(
            (data)=>{
                this.setState({foods:data});
            }
        );
    }

    createRestaurant = event => {
        const addVal = {
            name: this.state.name,
            description:  this.state.desc,
            price: this.state.price,
            category: this.state.categ,
            username: this.username
            }
        axios.post("/food/add",addVal).then(response => {
                if (response.data != null) {
                    alert("Added food");
                }
                else{
                    alert("Something is wrong.");
                }
            }
        )
        event.preventDefault();

    }

    findFood = event => {

    }

    restaurantChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }


    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> Add Food </Card.Header>
                <Card.Body>
                    <Form id="userForm" onSubmit={this.createRestaurant}>
                        <Form.Group controlId="UserGroup">
                            <Form.Label>Name</Form.Label>
                            <Form.Control required type="text" name="name" value={this.state.name}
                                          onChange={this.restaurantChange} placeholder="Name"/>
                        </Form.Group>

                        <Form.Group controlId="DescGroup">
                            <Form.Label>Description</Form.Label>
                            <Form.Control required type="text" name="desc" value={this.state.desc}
                                          onChange={this.restaurantChange} placeholder="Description"/>
                        </Form.Group>

                        <Form.Group controlId="PriceGroup">
                            <Form.Label>Price</Form.Label>
                            <Form.Control required type="text" name="price" value={this.state.price}
                                          onChange={this.restaurantChange} placeholder="Price"/>
                        </Form.Group>

                        <Form.Group controlId="CategGroup">
                            <Form.Label>Category</Form.Label>
                            <Form.Control required type="text" name="categ" value={this.state.categ}
                                          onChange={this.restaurantChange} placeholder="Category"/>
                        </Form.Group>


                        <Button variant="success" type="submit">
                            Add Food
                        </Button>
                    </Form>
                    <p></p>

                    <Form id="foodForm" onSubmit={this.findFood}>
                        <Form.Group controlId="UserGroup">
                            <Form.Label>Name</Form.Label>
                            <Form.Control required type="text" name="sCateg" value={this.state.sCateg}
                                          onChange={this.restaurantChange} placeholder="Category"/>
                        </Form.Group>
                    </Form>


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
                    {

                        this.state.foods.length === 0 ?
                            <tr></tr>:
                        this.state.foods.map((food)=>(
                            food.category===this.state.sCateg || this.state.sCateg===''?
                            <tr key={food.id}>
                                <td> {food.name}</td>
                                <td> {food.description}</td>
                                <td> {food.price}</td>
                                <td> {food.category}</td>
                            </tr>:
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

export default adminFoodPage;
