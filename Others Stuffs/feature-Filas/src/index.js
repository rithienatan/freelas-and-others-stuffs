import React from 'react'
import ReactDOM from 'react-dom';
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';
import App from './assets/components/App/App';

//Require dotenv
require('dotenv').config();

//Definir tema do app
const theme = createMuiTheme({
    palette: {
        primary: {
            light: '#A16246',
            main: '#9B4B28',
            dark: '#6E2828',
            contrastText: '#ffffff'
        },
        secondary: {
            light: '#667E60',
            main: '#4B5F46',
            dark: '#2E372C',
            contrastText: '#ffffff'
        },
    },
    typography: {
        useNextVariants: true,
    },
});

ReactDOM.render(
    <MuiThemeProvider theme={theme}>
        <App />
    </MuiThemeProvider>
    , document.getElementById('root')
);
