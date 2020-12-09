import React from 'react';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core'
    //--Styles --//
const styles = theme =>({
    container:{
        minHeight: '60vh',
        overflowX : 'hidden',
        paddingTop: '40px'
    }
})
    //--Fim Styles --//
class Cardapio extends React.Component{
    
    //Construtor
    constructor(props){
        super(props);
        this.state = {
            listaPratos: []
        }
    }

    //Le elementos do banco de dados
    componentDidMount = () =>{
        this.props.updateComponentWithDatabase('pratos')
    }

    render(){
        const{classes} = this.props
        
        return(
            <Grid container direction="column" className={classes.container} justify='center'>
                {this.props.listaPratos}
            </Grid>
        )
    }
}

export default withStyles(styles,{withTheme: true })(Cardapio)

