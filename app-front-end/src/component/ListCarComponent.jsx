import React, { Component } from 'react';
import Button from 'react-bootstrap/Button'
import NotificationModalComponent from './NotificationModalComponent'
import Loading from './Loading'
import Jumbotron from 'react-bootstrap/Jumbotron'

import CourseDataService from '../service/CarDataService'

class ListCarComponent extends Component {

    constructor(props) {
        super(props)
        this.refreshCars = this.refreshCars.bind(this)
        this.state = {
            cars: [],
            title: "",
            message: "",
        }

    }

    componentDidMount() {
        console.log('DidMount');
        this.refreshCars();
    }

    refreshCars() {
        console.log('RefreshingPage')
        this.setState({isLoading: true});
        CourseDataService.retrieveAllCourses()
            .then(
                response => {
                    console.log(response)
                    this.setState({cars: response.data, isLoading: false})
                }
            )
    }

    populateDataBase(e) {
        this.setState({isLoading: true});
        CourseDataService.populateDataBase().then(response => {
            console.log(response);
            this.setState({isLoading:false});
            if(response.status === "202"){
                this.handleMessage("Title Success!", "Status -> " + response.status + " Success!")
            }
            this.refreshCars();
        })
    }

    deleteCar(e){
        this.setState({isLoading: true});
        CourseDataService.delete(e).then(response => {
            console.log("returning delete !")
            console.log(response)
            console.log(response.data)
            if(response.data){
                 this.handleMessage("Title Success!", "Status -> " + response.status + " Success!")
            }else{
                this.handleMessage("Title Error!", "Status -> " + response.status + " Error!")
            }
            this.refreshCars();
        })
    }

    handleClose(){
        this.setState({showModal: false});
    };

    handleShow(){
        this.setState({showModal: true});
    }

    handleLoading(loading){
        this.setState({isLoading: loading});
    }

    handleMessage(title, message){
        console.log("Title -> " + title);
        console.log("MEssage -> " + message);
        this.setState({title: title, message: message});
    }

    render() {
        return (
            <div className="container">
                <Jumbotron fluid>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Model</th>
                                <th>Engine Type</th>
                                <th>Fuel Type</th>
                                <th>Year</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                        {
                                this.state.cars.map(
                                    car =>
                                        <tr key={car.id}>
                                            <td>{car.id}</td>
                                            <td>{car.model}</td>
                                            <td>{car.engineType.name}</td>
                                            <td>{car.fuelType.name}</td>
                                            <td>{car.year}</td>
                                            <td><Button variant="danger" onClick={e => this.deleteCar(car)}>Delete</Button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                </Jumbotron>

                {this.state.showModal?<NotificationModalComponent
                                title={this.state.title}
                                message={this.state.message}
                                showModal={true} 
                                displayCloseButton={true}           
               />:<div/>}
               
               {this.state.isLoading?<Loading
                    isLoading={true}
               />:<div/>}

            </div>
        )
    }
}

export default ListCarComponent