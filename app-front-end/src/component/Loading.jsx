import React, { Component } from 'react'
import Modal from 'react-bootstrap/Modal'
import { MDBIcon } from 'mdbreact'

export default class Loading extends Component {
    
    constructor(props) {
        super(props)
        this.state = {
            showLoading: this.props.isLoading
        }
    }

    render() {
        let divStyle = {
            'marginTop': '20%',
        };

        return (
            <div style={divStyle}>
                <Modal show={this.state.showLoading}>
                    <Modal.Body>isLoading...</Modal.Body>
                </Modal>
            </div>
        )
    }
}

