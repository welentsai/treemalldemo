import React, {Component} from 'react';
import {FaAngleRight} from "react-icons/fa";

class Order extends Component {

    state = {
        isLoading: true,
        inProgress: false,
        imgGrey: false,
        showDelivery: false,
        order: {}
    };

    componentDidMount() {
        this.setState({isLoading: false});
        this.setState({order: this.props.order});

        const code = this.props.order.status.code;

        if (code === 1 || code === 2) {  // 已成立, 處理中
            this.setState({inProgress: true});
            this.setState({showDelivery: true});
        }

        if (code === 3 || code === 4) { // 已送達, 已取消
            this.setState({imgGrey: true});
        }
    }

    render() {
        const {order, isLoading, inProgress, imgGrey, showDelivery} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <table id="order">
                <tbody>
                <tr>
                    <td rowSpan="2" className="Logo"><img src={order.logo} className={imgGrey ? 'grey' : 'original'}
                                                          alt="TreeMall" height="72" width="72"/></td>
                    <td className={inProgress ? 'orderInProgress' : 'orderFinished'}>{order.status.type}</td>
                    {showDelivery && <td className="deliveryDate">預計出貨 : {order.date}</td>}
                    {!showDelivery && <td></td>}
                    <td rowSpan="2" className="Right">
                        <FaAngleRight/>
                    </td>
                </tr>
                <tr>
                    <td colSpan="2" className="orderName">{order.name}</td>
                </tr>
                </tbody>
            </table>
        );
    }
}

export default Order;

