import React from 'react';
import Typography from '@material-ui/core/Typography';
import AppBar from '@material-ui/core/AppBar';
import ToolBar from '@material-ui/core/Toolbar';
import { withStyles, useScrollTrigger } from '@material-ui/core';
import IconButton from '@material-ui/core/IconButton';
import ArrowBack from '@material-ui/icons/ArrowBack';
import Grid from '@material-ui/core/Grid';
import Slide from '@material-ui/core/Slide';
import Grow from '@material-ui/core/Grow';

const styles = theme => ({
    root: {
        boxShadow: 'none'
    },
    icon: {
        color: theme.palette.primary.contrastText
    }
})

export default withStyles(styles, { withTheme: true })((props) => {
    const { classes, window } = props;

    //Detectar rolagem da tela
    const trigger = useScrollTrigger({ target: window ? window() : undefined });

    return (
        <Slide
            appear={false}
            in={!trigger}
            direction="down"
            mountOnEnter
            unmountOnExit
            timeout={800}
        >
            <AppBar className={classes.root}>
                <ToolBar>
                    <Grid
                        container
                        direction="row"
                        alignItems="center"
                    >
                        {props.isDisplayButton ?
                            (<Grid item>
                                <Grow
                                    in={props.isDisplayButton}
                                    timeout={500}
                                >
                                    <IconButton onClick={props.voltaPagina}>
                                        < ArrowBack className={classes.icon} />
                                    </IconButton>
                                </Grow>
                            </Grid>)
                            : undefined}
                        <Grid item>
                            <Typography variant="h5">{props.text}</Typography>
                        </Grid>
                    </Grid>
                </ToolBar>
            </AppBar >
        </Slide>
    )
})