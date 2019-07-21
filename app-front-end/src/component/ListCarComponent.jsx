import React, { Component } from 'react';
import Button from 'react-bootstrap/Button'
import CourseDataService from '../service/CarDataService'

class ListCarComponent extends Component {

    constructor(props) {
        super(props)
        this.refreshCars = this.refreshCars.bind(this)
        this.state = {
            cars: []
        }
    }

    componentDidMount() {
        this.refreshCars();
    }

    refreshCars() {
        CourseDataService.retrieveAllCourses()
            .then(
                response => {
                    console.log(response)
                    this.setState({cars: response.data})
                }
            )
    }

    populateDataBase(e) {
        CourseDataService.populateDataBase().then(response => {
            console.log(response);
        })
    }

    render() {
        return (
            <div className="container">
                <h3>All Car</h3>
                <Button variant="outline-primary" onClick={this.populateDataBase}>Primary</Button>
                <Button variant="primary" onClick={this.refreshCars}>Primary</Button>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Model</th>
                                <th>Engine Type</th>
                                <th>Fuel Type</th>
                                <th>Year</th>
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
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListCarComponent