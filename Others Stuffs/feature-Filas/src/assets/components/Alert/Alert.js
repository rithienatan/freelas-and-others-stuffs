import React from 'react';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import { green, red } from '@material-ui/core/colors';
import ErrorIcon from '@material-ui/icons/Error';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import { withStyles } from '@material-ui/styles';

const styles = theme => ({
    icon: {
        marginRight: theme.spacing(1)
    },
    message: {
        display: 'flex',
        alignItems: 'center'
    },
})

class Alert extends React.Component {
    render() {
        const { classes } = this.props;

        //Definir cor do alerta
        let alertType = null;
        switch (this.props.options.type) {
            default:
                alertType = {
                    color: green[500],
                    icon: (<CheckCircleIcon className={classes.icon} />)
                }
                break;
            case 'error':
                alertType = {
                    color: red[500],
                    icon: (<ErrorIcon className={classes.icon} />)
                }
                break;
        }
        return (
            <Snackbar
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                }}
                open={this.props.options.isOpen}
                autoHideDuration={6000}
                onClose={() => this.props.closeAlert(this.props.options)}
            >
                <SnackbarContent
                    aria-describedby="message-id"
                    style={{ backgroundColor: alertType.color }}
                    message={
                        <span id="message-id" className={classes.message}>
                            {alertType.icon}
                            {this.props.options.msg}
                        </span>
                    }
                    action={[
                        <IconButton
                            key="close"
                            aria-label="Close"
                            color="inherit"
                            onClick={() => this.props.closeAlert(this.props.options)}
                        >
                            <CloseIcon />
                        </IconButton>
                    ]}
                >
                </SnackbarContent>
            </Snackbar>
        );
    }
}

export default withStyles(styles, { withTheme: true })(Alert);