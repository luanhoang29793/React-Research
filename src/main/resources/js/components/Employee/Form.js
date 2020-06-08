import React, { Component } from 'react';

import employeeServices from "../Service/Employee"

export default class Form extends Component {

    constructor(){
        super();
        this.state = {
            fieldName:"",
            fieldEmail:"",
            fieldAddress:"",
            fieldPhone:"",
        }
    }
    async onClickSave()
    {
        const res = await employeeServices.create(this.state)
        if (res.success) {
            alert(res.message)
            window.location.replace("/employee/index")
        }
        else if (res.status==400) {
            console.log(res.status);
            const dataError = []
            const error = res.data.errors

            if (error) {
                error.map((itemerror)=>{
                    console.log(itemerror.defaultMessage);
                    dataError.push(itemerror.defaultMessage)
                })
                this.setState({errorField:dataError})
            }
            else {
                dataError.push(res.data.message)
                this.setState({errorField:dataError})
            }
        }
        else {
            // alert("Error ==>"+JSON.stringify(res))
            console.log(res);
            const dataError = []
            dataError.push(res.message);
            this.setState({errorField:dataError});
        }
    }


    render() {
        return (
            <div>
                <h4>Create employee v2 </h4>
                <div className="row">
                    <div className="col-md-6 mb-3">
                        <label for="firstName">Name employee</label>
                        <input type="text" className="form-control" placeholder="Name"
                               value={this.state.fieldName}
                               onChange={(event)=>this.setState({fieldName:event.target.value})}
                        />
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6 mb-3">
                        <label for="email">Email</label>
                        <input type="email" className="form-control" placeholder="you@example.com"
                               value={this.state.fieldEmail}
                               onChange={(event)=>this.setState({fieldEmail:event.target.value})}
                        />
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6 mb-3">
                        <label htmlfor="address">Address</label>
                        <input type="text" className="form-control" placeholder="1234 Main St"
                               value={this.state.fieldAddress}
                               onChange={(event)=>this.setState({fieldAddress:event.target.value})}
                        />
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6 mb-3">
                        <label htmlfor="phone">Phone </label>
                        <input type="text" className="form-control" placeholder="123467890"
                               value={this.state.fieldPhone}
                               onChange={(event)=>this.setState({fieldPhone:event.target.value})}
                        />
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6 mb-3">
                        <button onClick={()=>this.onClickSave()} className="btn btn-primary btn-block" type="submit">Save</button>
                    </div>
                </div>
            </div>
        )
    }



}