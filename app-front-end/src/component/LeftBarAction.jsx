import React, { Component } from 'react'
import CourseDataService from '../service/CarDataService'
import NotificationModalComponent from './NotificationModalComponent'
import Button from 'react-bootstrap/Button'
import Row from 'react-bootstrap/Row'
import Loading from './Loading'

class LeftBarAction extends Component {
    constructor(props) {
        super(props)
        this.state = {
        }

    }

    handleMessage(title, message){
        this.setState({title: title, message: message});
    }

    populateDataBase(e) {
        this.setState({isLoading: true});
        CourseDataService.populateDataBase().then(response => {
            console.log(response);
            this.setState({isLoading:false});
            if(response.status === "202"){
                this.handleMessage("Title Success!", "Status -> " + response.status + " Success!")
            }
        })
    }

    render() {
        return (
            <div>
                <Row>

                </Row>
                <Row>
                    
                </Row>
                <Button onClick={e => this.populateDataBase(e)}>LoadDataBaseBatch</Button>
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
export default LeftBarAction