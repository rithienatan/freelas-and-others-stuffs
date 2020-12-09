///---Imports---
import React from 'react'
import { withStyles } from '@material-ui/core'
import Typography from '@material-ui/core/Typography'
import Grid from '@material-ui/core/Grid'
import Avatar from '@material-ui/core/Avatar'
import fire from '../../utils/fire'
import ListItem from '@material-ui/core/ListItem'
import InfoCliente from '../InfoCliente/InfoCliente'

//---Style---
const styles = theme => ({
    box: {
        margin: theme.spacing(1)
    },
    avatar: {
        margin: 10,
    },
    title: {
        fontWeight: 'bold',
        fontSize: '0.9em'
    },
    text: {
        fontSize: '0.8em'
    }
})

const storage = fire.storage()

//---Box Clientes---
class BoxClientes extends React.Component{
    constructor(props){
        super(props)
        this.state = {
        }
    }

    handleClick = () => {
        let info={
            id: this.props.info.id,
            nome: this.props.info.nome,
            idade: this.props.info.idade,
            telefone: this.props.info.telefone,
            email: this.props.info.email,
            imagem: this.state.imagem
        }
        let conteudoDialog = (<InfoCliente content={info} deleteItemDatabase={this.props.deleteItemDatabase} updateClientesWithDatabase={this.props.updateClientesWithDatabase}/>)
        this.props.showDialog(conteudoDialog)
    }
    
    render(){
        storage.ref().child(this.props.info.imagem).getDownloadURL().then(url => {
            this.setState({imagem: url})
        })
        const {classes} = this.props
        return(
            <div>
                <ListItem button onClick={this.handleClick}>
                    <Grid container direction="row" justify="space-between" alignItems="center">
                        <Grid item>
                            <Typography variant="h6" className={classes.title}>{this.props.info.nome}</Typography>
                            <Typography variant="h6" className={classes.text}>Idade: {this.props.info.idade}</Typography>
                            <Typography variant="h6" className={classes.text}>Telefone: {this.props.info.telefone}</Typography>
                            <Typography variant="h6" className={classes.text}>Email: {this.props.info.email}</Typography>
                        </Grid>
                        <Grid item>
                            <Avatar src={this.state.imagem} className={classes.avatar} />
                        </Grid>
                    </Grid>
                </ListItem>
            </div>
        )
    }
}

export default withStyles(styles, { withTheme: true })(BoxClientes)