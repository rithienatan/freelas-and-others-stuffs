///---Imports---
import React from 'react'
import { withStyles } from '@material-ui/core'
import List from '@material-ui/core/List'

//---Style---
const styles = theme => ({
})

//---Clientes---
class Clientes extends React.Component{
    constructor(props){
        super(props)
        this.state = {
        }
    }
    
    componentDidMount(){
        this.props.updateComponentWithDatabase("clientes")
    }

    render(){
        const {classes} = this.props
        return(
            <List className={classes.root}>
                {this.props.listaClientes}
            </List>
        )
    }
}
   
export default withStyles(styles, { withTheme: true })(Clientes)