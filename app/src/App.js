import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';

import OrderList from './orderList';
import TinyUrl from './tinyUrl';

class App extends Component {
    state = {
        isLoading: true,
        ordersJson: ""
    };

    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={OrderList}/>
                    <Route path='/orders' exact={true} component={OrderList}/>
                    <Route path='/tinyUrl' exact={true} component={TinyUrl}/>
                </Switch>
            </Router>
        );
    }
}

export default App;
