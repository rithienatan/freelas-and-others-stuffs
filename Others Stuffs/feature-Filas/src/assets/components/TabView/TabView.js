import React from 'react';
import SwipeableViews from 'react-swipeable-views';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import { withStyles } from '@material-ui/styles';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';

const styles = theme => ({
    root: {
        width: '100vw',
        padding: '6vh 0'
    },
    fab: {
        position: 'absolute',
        bottom: theme.spacing(2),
        right: theme.spacing(2)
    }
})

export default withStyles(styles, { withTheme: true })((props) => {
    const { classes, theme } = props;
    const [value, setValue] = React.useState(0);

    function handleChange(ignored, newValue) {
        setValue(newValue);
    }

    function handleChangeIndex(index) {
        setValue(index)
    }

    return (
        <div className={classes.root}>
            <AppBar position="static" color="primary">
                <Tabs
                    value={value}
                    onChange={handleChange}
                    variant="fullWidth"
                >
                    <Tab label="CLIENTES" />
                    <Tab label="CARDÁPIO" />
                    <Tab label="FILA" />
                </Tabs>
            </AppBar>
            <SwipeableViews
                axis={theme.direction === "rtl" ? "x-reverse" : "x"}
                index={value}
                onChangeIndex={handleChangeIndex}
            >
                {props.paginas}
            </SwipeableViews>

            <Fab className={classes.fab} color="primary" onClick={() => {props.showDialogFullScreen(value)}}>
                <AddIcon/>
            </Fab>
        </div>
    );
})