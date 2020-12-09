/**
 * @version 0.0.0
 */

//imports de react, material ui e Firebase--------------------------------------

import React from 'react'
import { withStyles } from '@material-ui/core'
import Grid from '@material-ui/core/Grid'
//import Avatar from '@material-ui/core/Avatar'
import Typography from '@material-ui/core/Typography'
import Delete from '@material-ui/icons/Delete'
import IconButton from '@material-ui/core/IconButton'

//------------------------------------------------------------------------------

/**
 * Set Style
 */
const styles = theme => ({
  root: {
      width: 300,
      height: 350
  },
  //avatar: {
    //  margin: 40,
      //width: 100,
    //  height: 100
  //},
  title: {
      fontWeight: 'bold',
      fontSize: '1.2em',
      marginLeft: '0px'
  },
  text: {
      fontSize: '1.0em',
      marginLeft: '0px'
  },
  iconButton: {
      marginTop: "10px"
  }
});//end styles

/**
 * Classe Fila
 */
class VerClienteNaFila extends React.Component
{
  /**
   * Construtor da classe
   */
   constructor(props)
   {
     super(props)
     this.state = { }
   }//end constructor()

   handleDelete = () =>{
       this.props.deleteItemDatabase("clientes", this.props.content.id)
   }

   //<Grid item container justify="center">
       //<Avatar src={this.props.content.imagem} className={classes.avatar} />
   //</Grid>

  /**
   * @return Construção do front
   */
  render()
  {
    const { classes } = this.props

    return(
      <div className={classes.root}>
          <Grid container direction="row" justify="center" alignItems="center">

              <Grid item>
                  <Typography variant="h6" className={classes.title}>{this.props.content.nome}</Typography>
                  <Typography variant="h6" className={classes.text}>Idade: {this.props.content.idade}</Typography>
                  <Typography variant="h6" className={classes.text}>Telefone: {this.props.content.telefone}</Typography>
                  <Typography variant="h6" className={classes.text}>Email: {this.props.content.email}</Typography>
              </Grid>
              <Grid item container justify="center">
                  <IconButton className={classes.iconButton} onClick={this.handleDelete}>
                      <Delete color="primary"/>
                  </IconButton>
              </Grid>
          </Grid>
      </div>
    );
  }//end render()
}//end class

export default withStyles(styles, { withTheme: true })(VerClienteNaFila)
