import React, { Component } from 'react'
import Button from 'react-bootstrap/Button'
class Table extends Component {
    
constructor(props) {
        super(props)
        this.state = {
            data: this.props.data?this.props.data:[],
        }
    }

    componentDidMount(){
        console.log('DidMount ->')
        console.log(this.state.data)
    }
    
    render() {
        return (
            <div>
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
                        {this.state.data.map(
                                    car =>
                                        <tr key={car.id}>
                                            <td>{car.id}</td>
                                            <td>{car.model}</td>
                                            <td>{car.engineType.name}</td>
                                            <td>{car.fuelType.name}</td>
                                            <td>{car.year}</td>
                                            <td><Button variant="danger" onClick={this.props.onDelete}>Delete</Button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                </table>
            </div>
        )
    }
}
export default Table