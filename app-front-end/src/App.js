import React from 'react';
import './App.css';
import ListCarComponent from './component/ListCarComponent';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

function App() {
  return (
    <div className="App">
        <Row fluid>
          <Col xs={3}> 

          </Col>
          <Col xs={9}><ListCarComponent /></Col>
        </Row>
    </div>
  );
}

export default App;
