import React from 'react'
import Dialog from '@material-ui/core/Dialog'
import AppBar from '@material-ui/core/AppBar'
import Typography from '@material-ui/core/Typography'
import Toolbar from '@material-ui/core/Toolbar'
import IconButton from '@material-ui/core/IconButton'
import LeftArrowIcon from '@material-ui/icons/KeyboardArrowLeft'
import Slide from '@material-ui/core/Slide'
import { withStyles } from '@material-ui/core'

//---Style---
const styles = theme => ({
    appBar: {
        position: 'relative'
    },
    title: {
        marginLeft: theme.spacing(2),
        flex: 1
    }
})

//---Dialogo---
class DialogoFullScreen extends React.Component{

    Transition = React.forwardRef(function Transition(props, ref) {
        return <Slide direction="up" ref={ref} {...props} />;
    });

    render(){
        const { classes } = this.props
        return(
            <Dialog fullScreen open={this.props.options.isOpen} onClose={() => this.props.closeDialogFullScreen()} TransitionComponent={this.Transition}>
                <AppBar className={classes.appBar}>
                    <Toolbar>
                        <IconButton edge="start" color="inherit" onClick={() => this.props.closeDialogFullScreen()} aria-label="Close">
                            <LeftArrowIcon />
                        </IconButton>
                        <Typography variant="h6" className={classes.title}>
                            {this.props.options.titulo}
                        </Typography>
                    </Toolbar>
                </AppBar>
                {this.props.options.conteudo}
            </Dialog>
        )
    }
}

export default withStyles(styles, { withTheme: true })(DialogoFullScreen)