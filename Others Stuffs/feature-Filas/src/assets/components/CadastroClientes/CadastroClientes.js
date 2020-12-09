///---Imports---
import React from 'react'
import { withStyles } from '@material-ui/core'
import Button from '@material-ui/core/Button'
import Grid from '@material-ui/core/Grid'
import fire from '../../utils/fire'
import CameraIcon from '@material-ui/icons/CameraAlt'
import Avatar from '@material-ui/core/Avatar'
import TextField from '@material-ui/core/TextField'
import FormControl from '@material-ui/core/FormControl'
import InputLabel from '@material-ui/core/InputLabel'
import Input from '@material-ui/core/Input'
import MaskedInput from 'react-text-mask'
import CircularProgress from '@material-ui/core/CircularProgress'

//Firebase db
var db = fire.firestore()
var storage = fire.storage()

//---Style---
const styles = theme => ({
    input: {
        display: 'none'
    },
    avatar: {
        margin: theme.spacing(4, 0),
        width: 200,
        height: 200
    },
    inputTexto: {
        maxWidth: '600px',
        width: "100%",
    },
    button: {
        margin: theme.spacing(3,0)
    }
})


//---Cadastro Clientes---
class CadastroClientes extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            telefone: '( )     -    ',
            open: false,
            infoBotao: "CADASTRAR"
        }
    }

    //Lida com mudanças nos inputs 
    handleChange = label => event =>{
        this.setState({[label]: event.target.value})
    }

    //Lida com mudança no input de foto 
    handleFileChange = event => {
        var arquivo = document.getElementById('fotoCliente').files[0]
        if(arquivo){
            this.setState({
                arquivo: arquivo,
                imagem: URL.createObjectURL(arquivo)
            })
        }
    }
    enviarEmail = () => {
        fetch("https://us-central1-app-gerenciamento-filas-83693.cloudfunctions.net/sendEmail?destinatario=" + this.state.email + "&nome=" + this.state.nome)
    }

    //Lida com o submit do formulário
    handleSubmit = event =>{
        if(!this.state.arquivo){
            this.props.showAlert("É necessário selecionar uma foto", "error")
        }else if(!this.state.nome || !this.state.email || !this.state.idade || !this.state.telefone){
            this.props.showAlert("Preencha todos os campos", "error")
        }else{
            this.setState({infoBotao: <CircularProgress color="white" />})
            this.enviarEmail()
            this.adicionarCliente(this.state.arquivo)
        }
        event.preventDefault()
    }

    //Adicionar Cliente
    //=>adiciona clientes=>alert de sucesso=>fecha o dialog
    adicionarCliente = (arquivo) => {
        if(this.state.nome && this.state.idade && this.state.email && this.state.telefone){
            var dados = {
                nome: this.state.nome,
                idade: this.state.idade,
                email: this.state.email,
                telefone: this.state.telefone
            }
            db.collection("clientes").add(dados).then(docRef =>{
                storage.ref().child("fotoClientes/" + docRef.id + "/fotoRosto").put(arquivo).then(()=>{
                    dados.imagem = "fotoClientes/" + docRef.id + "/fotoRosto"
                    db.collection("clientes").doc(docRef.id).set(dados).then(() => {
                        this.props.showAlert("Cliente cadastrado com sucesso!")
                        this.props.closeDialogFullScreen()
                        this.props.updateComponentWithDatabase("clientes")
                        this.setState({infoBotao: "CADASTRAR"})
                    })
                })
            }).catch(error => {
                this.props.showAlert("Cliente não foi cadastrado!", "error")
            })
        }
    }

    //Função mascara Telefone    
    TextMaskCustom = (props) =>{
        const { inputRef, ...other } = props;
      
        return (
          <MaskedInput
            {...other}
            ref={ref => {
              inputRef(ref ? ref.inputElement : null);
            }}
            mask={['(', /\d/, /\d/, ')', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/]}
            placeholderChar={'\u2000'}
            showMask
          />
        );
      }

    render(){
        const { classes } = this.props
        return(
            <form onSubmit={this.handleSubmit}>
                <input accept="image/*" className={classes.input} id="fotoCliente" type="file" onChange={this.handleFileChange}/>

                <Grid container direction="row" justify="center" alignItems="center">
                    <Grid container item xs={8} justify="center">
                        <label htmlFor="fotoCliente">
                        <Avatar className={classes.avatar} id="avatar" src={this.state.imagem}>
                            <CameraIcon fontSize="large"/>
                        </Avatar>
                        </label>
                    </Grid>
                </Grid>

                <Grid container direction="row" justify="center" alignItems="center">
                    <Grid container item xs={10} justify="center">
                        <TextField placeholder="Nome" margin="normal" InputLabelProps={{shrink: true}} className={classes.inputTexto} onChange={this.handleChange("nome")}/>
                    </Grid>
                
                    <Grid container item xs={10} justify="center">
                        <TextField type='number' placeholder="Idade" margin="normal" InputLabelProps={{shrink: true}} className={classes.inputTexto} onChange={this.handleChange("idade")}/>
                    </Grid>
                
                    <Grid container item xs={10} justify="center">
                        <FormControl className={classes.inputTexto}>
                            <InputLabel htmlFor="formatted-text-mask-input">Telefone</InputLabel>
                            <Input value={this.state.telefone} inputComponent={this.TextMaskCustom} className={classes.inputTexto} onChange={this.handleChange("telefone")}/>
                        </FormControl>
                    </Grid>

                    <Grid container item xs={10} justify="center">
                        <TextField type="email" placeholder="Email" margin="normal" InputLabelProps={{shrink: true}} className={classes.inputTexto} onChange={this.handleChange("email")}/>
                    </Grid>

                    <Grid container item xs={8} justify="center">
                        <Button type="submit" variant="contained" color="primary" className={classes.button}>
                            {this.state.infoBotao}
                        </Button>
                    </Grid>
                </Grid>
                
            </form>
        )
    }
}

export default withStyles(styles, { withTheme: true })(CadastroClientes)