import React, { Component } from 'react';
import ReactDOM from 'react-dom';

import Nav from "./Nav"
import Form from "./Employee/Form"
import List from "./Employee/List"
import Edit from "./Employee/Edit"

import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";

export default class Main extends Component {
    render() {
        return (
            <Router>
                <main>
                    <Nav/>
                    <Switch>
                        <Route path="/employee/index" exact component={List} />
                        <Route path="/employee/form"  component={Form} />
                        <Route path="/employee/edit/:id" component={Edit} />
                    </Switch>
                    <Nav/>

                </main>
            </Router>
        )
    }
}
// for <div id="main-customer"></div>
ReactDOM.render(<Main />, document.getElementById('main-employee'));