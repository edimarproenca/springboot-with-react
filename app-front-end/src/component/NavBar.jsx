import React, { Component } from 'react'
import {Nav, NavDropdown, Navbar} from 'react-bootstrap'


export default class NavBar extends Component {
    render() {
        return (
            <div>
                <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                  <Navbar.Brand href="#home">React-SpringBatch</Navbar.Brand>
                  <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                  <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="mr-auto">
                      <Nav.Link href="#features">About</Nav.Link>
                      <NavDropdown title="Dropdown" id="collasible-nav-dropdown">
                      </NavDropdown>
                    </Nav>
                    <Nav>
                      <Nav.Link href="#deets">More deets</Nav.Link>
                    </Nav>
                  </Navbar.Collapse>
                </Navbar>
            </div>
        )
    }
}
