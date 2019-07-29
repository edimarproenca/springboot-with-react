import React, { Component } from 'react'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class NotificationModalComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            title: this.props.title,
            messageToDisplay: this.props.message,
            showModal: this.props.showModal,
            displayCloseButton: this.props.displayCloseButton,
        }

    }

    handleClose(){
        this.setState({showModal:false});
    }

    render() {
        return (
            <div>
                <Modal show={this.state.showModal}>
                    <Modal.Header closeButton>
                        <Modal.Title>{this.state.title}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>{this.state.messageToDisplay}</Modal.Body>
                    <Modal.Footer>
                     {this.state.displayCloseButton?<Button variant="secondary" onClick={e => this.handleClose()}>
                            Close
                    </Button>:<div/>}
                    </Modal.Footer>
                </Modal>
            </div>
        )
    }
}
