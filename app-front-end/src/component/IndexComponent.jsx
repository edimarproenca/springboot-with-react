import React, { Component } from 'react';
import NotificationModalComponent from './NotificationModalComponent'
import Loading from './Loading'
import Button from 'react-bootstrap/Button'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Container from 'react-bootstrap/Container'
import Table from 'react-bootstrap/Table'
import CourseDataService from '../service/CarDataService'
import './IndexComponent.css'

class IndexComponent extends Component {

    constructor(props) {
        super(props)
        this.refreshCars = this.refreshCars.bind(this)
        this.state = {
            cars: [],
            pageNumber: 0
        }
    }

    componentWillMount(){
        this.refreshCars();
    }

    refreshCars() {
        console.log('RefreshingPage')
        this.setState({isLoading: true});
        CourseDataService.findAllByPage(this.state.pageNumber)
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

    deleteButtonClick(e){
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

    handleNextPage(){
        let page = this.state.pageNumber+1;
        this.setState({pageNumber: page})
        this.refreshCars()
        console.log('next page -> ' + this.state.pageNumber)
    }

    handleBackPage(){
        if(this.state.pageNumber-1>=0){
            let page = this.state.pageNumber-1;
            this.setState({pageNumber: page})
        }else{
            this.setState({pageNumber: 0})
        }
        this.refreshCars()
        console.log('back page -> ' + this.state.pageNumber)
    }

    render() {
        return (
            <div styleName="containerRight">
                <Row>
                <Col xs={3}>
                    <Container className="containerLeft">
                        <Row styleName="titleSideBar">
                            <h3 >Actions</h3>
                        </Row>
                        <Row>
                            <Button className="rowLeft" onClick={e => this.populateDataBase()}>StartSpringBatch</Button>
                        </Row>
                        <Row>
                            <Button className="rowLeft" onClick={e => this.refreshCars()}>Reload Table</Button>
                        </Row>
                    </Container>
                </Col>
                <Col xs={9}>
                <Table striped bordered hover variant="dark" responsive="xl" className="table">
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
                        {this.state.cars.map(
                                    car =>
                                        <tr key={car.id}>
                                            <td>{car.id}</td>
                                            <td>{car.model}</td>
                                            <td>{car.engineType.name}</td>
                                            <td>{car.fuelType.name}</td>
                                            <td>{car.year}</td>
                                            <td><Button variant="danger" onClick={e => this.deleteButtonClick(car)}>Delete</Button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                        <tfoot>
                        {this.state.cars.length>0?
                        <Row>
                            <Col><Button  variant="danger" className="rowLeft" onClick={e => this.handleBackPage()}>Back</Button></Col>
                            <Col><Button  variant="danger"  className="rowLeft" onClick={e => this.handleNextPage()}>Next</Button></Col>
                        </Row>:<div><h3>Nothing to display</h3></div>}                            
                        </tfoot>
                </Table>

                </Col>
                </Row>
                
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

export default IndexComponent