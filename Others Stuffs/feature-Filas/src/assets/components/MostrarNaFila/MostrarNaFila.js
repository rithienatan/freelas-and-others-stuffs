/**
 * @version 0.0.0
 */

//imports de react, material ui e Firebase--------------------------------------

import React from 'react'
import { withStyles } from '@material-ui/core'
import Typography from '@material-ui/core/Typography'
import Grid from '@material-ui/core/Grid'
//import Avatar from '@material-ui/core/Avatar'
import ListItem from '@material-ui/core/ListItem'
import VerClienteNaFila from '../VerClienteNaFila/VerClienteNaFila'

//------------------------------------------------------------------------------

/**
 * Set Style
 */
const styles = theme => ({
  box: {
      margin: theme.spacing(1)
  },
  //avatar: {
    //  margin: 10,
  //},
  title: {
      fontWeight: 'bold',
      fontSize: '0.9em'
  },
  text: {
      fontSize: '0.8em'
  }
});//end styles

/**
 * Classe Fila
 */
class MostrarNaFila extends React.Component
{
  /**
   * Construtor da classe
   */
   constructor(props)
   {
     super(props)
     this.state = { }
   }//end constructor()

   handleClick = () => {
       let info={
           id: this.props.info.id,
           nome: this.props.info.nome,
           idade: this.props.info.idade,
           telefone: this.props.info.telefone,
           email: this.props.info.email,
           //imagem: this.state.imagem
       }
       let conteudoDialog = (<VerClienteNaFila content={info} deleteItemDatabase={this.props.deleteItemDatabase} updateFilaWithDatabase={this.props.updateFilaWithDatabase}/>)
       this.props.showDialog(conteudoDialog)
   }
   //storage.ref().child(this.props.info.imagem).getDownloadURL().then(url => {
       //this.setState({imagem: url})
   //})

   //<Grid item>
    //   <Avatar src={this.state.imagem} className={classes.avatar} />
   //</Grid>

   /**
    * @return Construção do front
    */
   render(){

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

                   </Grid>
               </ListItem>
           </div>
       )
   }//end render()

}//end class

export default withStyles(styles, { withTheme: true })(MostrarNaFila)
