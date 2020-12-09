/**
 * @version 0.0.0
 */

//imports de react, material ui e Firebase--------------------------------------

import React from 'react';
import { withStyles } from '@material-ui/styles';
import List from '@material-ui/core/List';

//------------------------------------------------------------------------------

/**
 * Set Style
 */
 const styles = theme => ({});//end styles

 /**
  * Classe AdicionandoAFila
  */
class AdicionandoAFila extends React.Component
{
  /**
   * Construtor da classe
   */
   constructor(props)
   {
     super(props)
     this.state = { }//end state
   }//end constructor()

   /**
    * Metodo de verificação da construção do componente
    */
   componentDidMount()
   {
     this.props.updateComponentWithDatabase("fila")
   }//end componentDidMount()

  /**
   * Metodo render
   * @return Construção do front
   */
  render()
  {
    const { classes } = this.props

    return(
      <List className={classes.root}>
        {this.props.listaFilaClientes}
      </List>
    );
  }//end render()
}//end class

export default withStyles(styles, { withTheme: true })(AdicionandoAFila)
