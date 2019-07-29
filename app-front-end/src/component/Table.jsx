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

            </div>
        )
    }
}
export default Table