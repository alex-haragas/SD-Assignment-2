import {Component} from "react";
import {Card, Form, Button} from 'react-bootstrap';
import axios from 'axios';

class restaurantPage extends Component {

    constructor(props) {
        super(props);
        this.state = { name: '', location: '',rezName:' ',rezLoc:' '};
        this.createRestaurant = this.createRestaurant.bind(this);
        this.restaurantChange = this.restaurantChange.bind(this);
        this.deleteRestaurant = this.deleteRestaurant.bind(this);
        this.username=this.props.match.params.username;
        this.restaurant={name: ' ',location:' '}
        const value={
            name:this.props.match.params.username
        }
        this.user=JSON.parse(localStorage.getItem('user'));
    }


    async componentDidMount() {
        if (this.user.username === this.username) {
            axios.get("/restaurant/" + this.username).then(response => response.data).then(
                (data) => {
                    this.setState({rezName: data.name, rezLoc: data.location});
                }
            );
        }
    }


    createRestaurant = event => {
        if(this.user.username==this.username) {
            const addVal = {
                name: this.state.name,
                loc: this.state.location,
                usr: this.username
            }
            axios.post("/restaurant/add", addVal, {
                headers: {Authorization: "Bearer " + this.user.jwt}
            }).then(response => {
                    if (response.data != null) {
                        this.restaurant.name = response.data.name;
                        this.restaurant.location = response.data.location;
                    } else {
                        alert("Something is wrong.");
                    }
                }
            )
            event.preventDefault();
        }

    }

    deleteRestaurant = event=>{
        if(this.user.username===this.username) {
            const delValue = {
                name: this.state.rezName
            }
            console.log("random")
            axios.post("/restaurant/delete", delValue,{
                headers:{Authorization: "Bearer " + this.user.jwt }
            }).then(response => {
                    if (response.data != null) {
                        alert("Deleted Restaurant")
                    }
                }
            )
            event.preventDefault();
        }
    }

    restaurantChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }


    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> Add/Delete Restaurant </Card.Header>
                <Card.Body>
                    <p>{this.state.rezName}</p>
                    <p>{this.state.rezLoc}</p>
                    <Form id="userForm" onSubmit={this.createRestaurant}>
                        <Form.Group controlId="UserGroup">
                            <Form.Label>Name</Form.Label>
                            <Form.Control required type="text" name="name" value={this.state.name}
                                          onChange={this.restaurantChange} placeholder="Name"/>
                        </Form.Group>

                        <Form.Group controlId="PassGroup">
                            <Form.Label>Location</Form.Label>
                            <Form.Control required type="text" name="location" value={this.state.location}
                                          onChange={this.restaurantChange} placeholder="Location"/>
                        </Form.Group>


                        <Button variant="success" type="submit">
                            Add Restaurant
                        </Button>
                    </Form>
                    <p></p>
                </Card.Body>
            </Card>
        );
    }
}

export default restaurantPage;
