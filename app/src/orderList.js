import React, {Component} from 'react';
import { ListGroup, ListGroupItem } from 'reactstrap';
import Order from "./order";

class OrderList extends Component {

    state = {
        isLoading: true,
    };

    async componentDidMount() {

        this.setState({isLoading: true});
        try {
            const response = await fetch('/json');
            // const body = await response.json();
            const text = await response.text();
            const orders = JSON.parse(text)["orders"];

            const sortedOrders = orders.sort(function (a, b) {
                // order by descend
                const d1 = a.date.split("/").join("");
                const d2 = b.date.split("/").join("");

                if (d1 > d2) {
                    return -1;  // in order [d1, d2]
                } else if (d1 < d2) {
                    return 1; // in order [d2, d1]
                }

                return 0; // keep same order
            });

            const inprogressList = sortedOrders.filter(function (order) {
                return order.status.code === 1 || order.status.code === 2;
            });

            const finishedList = orders.filter(function (order) {
                return order.status.code === 3 || order.status.code === 4;
            });

            this.setState({inprogressOrders: inprogressList});
            this.setState({finishedOrders: finishedList});
            this.setState({isLoading: false});
        } catch(e) {
            console.log("Oops ...")
        }
    }

    render() {
        const {inprogressOrders, finishedOrders, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <ListGroup className="OrderList">
                <ListGroupItem className="InProgress"><svg width="22" height="32">
                    <rect width="8" height="32"  className="greenBar" />
                </svg>進行中</ListGroupItem>
                {inprogressOrders.map((order, idx) =>
                    <ListGroupItem key={idx}>
                        <Order order={order}/>
                    </ListGroupItem>
                )}
                <ListGroupItem className="Finish"><svg width="22" height="32">
                    <rect width="8" height="32"  className="greenBar" />
                </svg>已完成</ListGroupItem>
                {finishedOrders.map((order, idx) =>
                    <ListGroupItem key={idx}>
                        <Order order={order}/>
                    </ListGroupItem>
                )}
            </ListGroup>
        );
    }
}

export default OrderList;