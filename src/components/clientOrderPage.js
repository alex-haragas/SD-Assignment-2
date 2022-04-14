import {Component} from "react";
import { Card, Table} from 'react-bootstrap';
import axios from 'axios';

class clientOrderPage extends Component {

    constructor(props) {
        super(props);
        this.state = {orders:[]};
        this.username = this.props.match.params.username;

    }


    async componentDidMount() {
        axios.get("/orders/client/"+this.username).then(response=>response.data).then(
            (data)=>{
                this.setState({orders:data});
            }
        );
    }



    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> Orders </Card.Header>
                <Card.Body>
                    <Table bordered hover striped variant="dark">
                        <thead>
                        <tr>
                            <th>Restaurant</th>
                            <th>Status</th>
                            <th>Order</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.orders.length === 0 ?
                                <tr></tr> :
                                this.state.orders.map((order) => (
                                        <tr key={order.id}>
                                            <td> {order.restaurant.name}</td>
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
                                        </tr>
                                ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default clientOrderPage;
