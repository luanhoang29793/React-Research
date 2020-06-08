import React, { Component } from 'react';
import employeeServices from"../Service/Employee"
import { Link } from "react-router-dom";
export default  class List extends Component {
    constructor() {
        super();
        this.state ={
            listEmployee:[]
        }
    }
    async componentDidMount() {
        console.log("Mount List")
        const  res = await employeeServices.list();
        console.log(res)
        if (res.success){
            this.setState({listEmployee:res.list})
        } else {
            alert("Error Server===>" +res.message)
        }
    }

    render() {
        return (
            <section>
                <h4>Employee List v3</h4>
                <hr/>
                    <table className="table">
                        <thead className="thead-dark">
                        <tr >
                            <th key="thead" scope="col">#</th>
                            <th scope="col" key="thead">Name</th>
                            <th scope="col" key="thead">Email</th>
                            <th scope="col" key="thead">Address</th>
                            <th scope="col" key="thead">Phone</th>
                            <th scope="col" key="thead">Action</th>
                        </tr>
                        </thead>
                        <tbody key="tbody">
                        {this.state.listEmployee.map((data) => {
                            return(
                                <tr>
                                    <td scope="row">{data.id}</td>
                                    <td>{data.name}</td>
                                    <td>{data.email}</td>
                                    <td>{data.address}</td>
                                    <td>{data.phone}</td>
                                    <td>
                                        <Link to={"/employee/edit/"+ data.id} className="btn btn-light"> Edit </Link>
                                        <a href="#" className="btn btn-danger"> Delete </a>
                                    </td>
                                </tr>
                            )
                        })
                        }
                        </tbody>
                    </table>
            </section>
    )
    }
}


