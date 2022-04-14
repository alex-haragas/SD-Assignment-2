import {Component} from "react";
import {Card, Form, Button, Table, ButtonGroup} from 'react-bootstrap';
import axios from 'axios';

class adminOrderPage extends Component {

    constructor(props) {
        super(props);
        this.state = {orders:[], sCateg:''};
        this.username=this.props.match.params.username;
        this.orderChange=this.orderChange.bind(this);
        this.orderAccept=this.orderAccept.bind(this);
        this.orderDecline=this.orderDecline.bind(this);
    }


    async componentDidMount(){
        axios.get("/orders/admin/"+this.username).then(response=>response.data).then(
            (data)=>{
                this.setState({orders:data});
            }
        );
    }



    orderChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    orderDecline(order){
        axios.post("/orders/decline", order).then(response=> {
            if (response.data != null) {
                alert("Changed")
                order=response.data
                window.location.reload()
            }
        })


    }

    orderAccept(order){
        axios.post("/orders/accept",order).then(response=> {
            if (response.data != null) {
                alert("Changed")
                window.location.reload()
            }
        })
    }


    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> See orders </Card.Header>
                <Card.Body>
                    <Form id="foodForm" onSubmit={this.findFood}>
                        <Form.Group controlId="UserGroup">
                            <Form.Label>Status</Form.Label>
                            <Form.Control required type="text" name="sCateg" value={this.state.sCateg}
                                          onChange={this.orderChange} placeholder="Category"/>
                        </Form.Group>
                    </Form>


                    <Table bordered hover striped variant="dark">
                        <thead>
                        <tr>
                            <td>Id</td>
                            <th>Client</th>
                            <th>Location</th>
                            <th>Status</th>
                            <th>Order</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.orders.length === 0 ?
                                <tr></tr>:
                                this.state.orders.map((order)=>(
                                    order.status===this.state.sCateg || this.state.sCateg===''?
                                        <tr key={order.id}>
                                            <td>{order.id}</td>
                                            <td> {order.client.username}</td>
                                            <td> {order.client.address}</td>
                                            <td> {order.status}</td>
                                            <Table bordered hover striped variant="dark">
                                                <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Price</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                {order.foods.map((food) => (
                                                    <tr key={food.id}>
                                                        <td>{food.name}</td>
                                                        <td>{food.price}</td>
                                                    </tr>
                                                ))}
                                                </tbody>
                                            </Table>
                                            <td>
                                                <ButtonGroup>
                                                    <Button onClick={()=>this.orderAccept(order)}>
                                                        Accept
                                                    </Button>
                                                    <Button onClick={()=>this.orderDecline(order)}>
                                                        Decline
                                                    </Button>
                                                </ButtonGroup>
                                            </td>
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

export default adminOrderPage;
