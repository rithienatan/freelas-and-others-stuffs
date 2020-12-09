import React from 'react';
import { withStyles } from '@material-ui/core';
import Grow from '@material-ui/core/Grow';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import fire from '../../utils/fire';

var auth = fire.auth();

const style = theme => ({
    root: {
        height: '100vh',
        overflow: "hidden"
    },
})

class PaginaLogin extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            pass: '',
            isCarregando: false,
        }
    }

    //Altera estado com mudanca do valor
    handleChange = label => event => {
        this.setState({ [label]: event.target.value });
    }

    //Faz login
    handleFormSubmit = (event) => {
        this.setState({ isCarregando: true });
        if (this.state.email && this.state.pass) {
            auth.signInWithEmailAndPassword(this.state.email, this.state.pass)
                .then(() => {
                    this.setState({ isCarregando: false });
                    this.props.showPage("Admin");
                })
                .catch((error) => {
                    this.setState({ isCarregando: false })
                    let msg = null;

                    switch (error.code) {
                        case 'auth/user-not-found':
                            msg = "Usuário não encontrado";
                            break;
                        case 'auth/wrong-password':
                            msg = "Senha incorreta";
                            break;
                        case 'auth/invalid-email':
                            msg = "Email invalido";
                            break;
                        case 'auth/user-disabled':
                            msg = "Usuário desabilitado";
                            break;
                        default:
                            msg = "Ocorreu um erro, tente novamente mais tarde";
                            break;
                    }
                    this.props.showAlert(msg, "error");
                })
        } else {
            this.setState({ isCarregando: false })
            this.props.showAlert("Preencha todos os campos", "error");
        }
        event.preventDefault();
    }

    render() {
        const { classes } = this.props;

        let btnConcluir = null;
        switch (this.state.isCarregando) {
            default:
                btnConcluir = (<Button
                    variant="contained"
                    color="primary"
                    onClick={this.handleFormSubmit}
                    type="submit">
                    concluir
                    </Button>)
                break;
            case true:
                btnConcluir = (<Button
                    variant="contained"
                    color="primary"
                    disabled
                >
                    ...
                    </Button>)
                break;
        }

        return (
            <Grow in={true}>
                <div>
                    <form onSubmit={this.handleFormSubmit}>
                        <Grid
                            container
                            direction="column"
                            justify="center"
                            alignItems="center"
                            className={classes.root}
                            spacing={8}
                        >
                            <Grid item>
                                <Typography variant="h4">Bem vindo</Typography>
                            </Grid>
                            <Grid item>
                                <TextField
                                    label="Email"
                                    value={this.state.email}
                                    autoComplete="email"
                                    onChange={this.handleChange("email")}
                                    placeholder="Digite seu email"
                                    type="email"
                                    fullWidth
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    label="Senha"
                                    value={this.state.pass}
                                    autoComplete="password"
                                    onChange={this.handleChange("pass")}
                                    placeholder="Digite sua senha"
                                    type="password"
                                    fullWidth
                                />
                            </Grid>
                            <Grid item>
                                {btnConcluir}
                            </Grid>
                        </Grid>
                    </form>
                </div>
            </Grow>
        )
    }
}

export default withStyles(style, { withTheme: true })(PaginaLogin)
