/**
 * @version 0.0.0
 */

//imports de react, material ui e Firebase--------------------------------------

import React from 'react';
import { withStyles } from '@material-ui/styles';
import fire from '../../utils/fire'
import Grid from '@material-ui/core/Grid'
import Avatar from '@material-ui/core/Avatar'
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button'
import CircularProgress from '@material-ui/core/CircularProgress'

//------------------------------------------------------------------------------

/**
 * Set Style
 */
 const styles = theme => ({
   root: {
       width: 300,
       height: 350
   },
   avatar: {
       margin: 40,
       width: 100,
       height: 100
   },
   title: {
       fontWeight: 'bold',
       fontSize: '1.2em',
       marginLeft: '0px'
   },
   text: {
       fontSize: '1.0em',
       marginLeft: '0px'
   },
   button: {
       margin: theme.spacing(3,0)
   }
 });//end styles

 //----Firebase global var--------//
 var db = fire.firestore()

 /**
  * Classe AdicionandoAFila
  */
class AddFilaBox extends React.Component
{
  /**
   * Construtor da classe
   */
   constructor(props)
   {
     super(props)
     this.state = { button: "Adicionar a lista" }//end state
   }//end constructor()

   /**
    * Função que faz a inserção do cliente em uma fila no banco de dados
    */
   handleClick = () => {
     //inserindo os dados
     var dados = {
         nome: this.props.content.nome,
         idade: this.props.content.idade,
         email: this.props.content.email,
         telefone: this.props.content.telefone
     }
     db.collection("fila").add(dados).then(docRef =>{
              this.setState({button: <CircularProgress color="white"/>})
              db.collection("fila").doc(docRef.id).set(dados).then(() => {
                 this.props.showAlert("Cliente adicionado a fila com sucesso!")
                 this.props.closeDialogFullScreen()
                 this.props.updateComponentWithDatabase("fila")
                 this.setState({button: "Adicionar a lista"})
             })
     }).catch(error => {
         this.props.showAlert("O cliente não adicionado a fila!", "error")
     })
   }//end handleClick

  /**
   * Metodo render
   * @return Construção do front
   */
  render()
  {
    const { classes } = this.props

    return(
      <div className={classes.root}>
          <Grid container direction="row" justify="center" alignItems="center">
              <Grid item container justify="center">
                  <Avatar src={this.props.content.imagem} className={classes.avatar} />
              </Grid>
              <Grid item>
                  <Typography variant="h6" className={classes.title}>{this.props.content.nome}</Typography>
                  <Typography variant="h6" className={classes.text}>Email: {this.props.content.email}</Typography>
                  <Typography variant="h6" className={classes.text}>Idade: {this.props.content.idade}</Typography>
                  <Typography variant="h6" className={classes.text}>Telefone: {this.props.content.telefone}</Typography>
              </Grid>
              <Grid item container justify="center">
                  <Button onClick={this.handleClick} variant="contained" color="primary" className={classes.button}>
                      {this.state.button}
                  </Button>
              </Grid>
          </Grid>
      </div>
    );
  }//end render()
}//end class

export default withStyles(styles, { withTheme: true })(AddFilaBox)
