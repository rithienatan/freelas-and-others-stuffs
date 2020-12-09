import React from 'react'

import { withStyles } from '@material-ui/core'
import Dialog from '@material-ui/core/Dialog'

//---Style---
const styles = theme => ({
})

//---Dialogo---
class Dialogo extends React.Component{

    render(){
        return(
            <Dialog open={this.props.options.isOpen} onClose={() => this.props.closeDialog()}>
                {this.props.options.conteudo}
            </Dialog>
        )
    }
}

export default withStyles(styles, { withTheme: true })(Dialogo)