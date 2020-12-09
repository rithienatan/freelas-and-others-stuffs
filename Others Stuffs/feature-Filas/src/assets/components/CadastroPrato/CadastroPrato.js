import React from 'react';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import Avatar from '@material-ui/core/Avatar'
import Camera from '@material-ui/icons/CameraAlt'
import fire from '../../utils/fire'
import { withStyles } from '@material-ui/core'
    //--Styles--//
const styles = theme =>({
    inputImage:{
        borderRadius:"0px",
        marginTop: "50px", 
        width: "200px",
        height: "200px",
        backgroundColor: "#707070"
    },
    display:{
        display:'none'
    },
    overflow:{
        overflow:'hidden'
    },
    button:{
        padding: '20px'
    }
})
    //--Fim Styles--//

class CadastroPrato extends React.Component{
    
    //construtor
    constructor(props){
        super(props);
        this.state = {
            nome: '',
            ingredientes: '',
            preco: '',
            file: '',
            isCarregando : false,
        } 
    }

    handleChange = label => e =>{
        this.setState({ [label]: e.target.value });
    }

    verificaDados = () =>{
        let ehValido = false
        if(this.state.nome &&
        this.state.ingredientes &&
        this.state.preco && this.state.file){
            ehValido = true
        }
        return ehValido
    }

    //Limpa Campos do formulario

    handleSubmit = e => {
        const ehValido = this.verificaDados()
        if(ehValido){
            this.adicionarPrato(this.state.file);
            e.preventDefault();
            this.props.showAlert("O prato foi adicionado com sucesso!!")
            this.props.closeDialogFullScreen()
        }else{
            this.props.showAlert("Preencha todos os campos", "error")
            e.preventDefault();
        }
    }

    handleFileChange = e => {
        const img = document.getElementById('file').files[0]
        this.setState({
            file: img,
            imagem: URL.createObjectURL(img)
        })
    }

    //Adiciona pratos no banco de dados
    adicionarPrato = file => {
        const db = fire.firestore()
        const storage = fire.storage()
        this.setState({isCarregando: true})
        let dados = {
            nome: this.state.nome,
            ingredientes: this.state.ingredientes,
            preco: this.state.preco,
            caminho: "pratos/"+this.state.nome+"/fotoPrato"
        }
        db.collection("pratos").add(dados).then(()=>{ 
            storage.ref().child(dados.caminho).put(file).then(()=> {
                this.props.updateComponentWithDatabase('pratos')
            })
        }).catch(error =>{
            this.props.showAlert("Houve um erro inesperado, tente novamente", "error")
        })
        this.setState({isCarregando: false})
    }

    render(){

        const{classes} = this.props
        let cadastrar = null;

        switch(this.state.isCarregando){
            case true:
                cadastrar = (<Button
                    variant="contained"
                    color="primary"
                    disabled
                >Cadastrar</Button>)
                break;
            default:
                cadastrar = (<Button
                    variant="contained"
                    color="primary"
                    onClick ={this.handleSubmit}
                    type = "submit"
                >Cadastrar</Button>)
        }
        
        return(
              <div>
                <form>
                    <Grid
                        container
                        direction="column"
                        justify="center"
                        alignItems="center"
                        className = {classes.overflow}
                    >
                           <Grid>
                            <input
                                accept="image/*"
                                type="file"
                                className={classes.display}
                                onChange ={this.handleFileChange}
                                id="file"
                            />
                            <label htmlFor="file">
                                <Avatar 
                                    src={this.state.imagem}
                                    className={classes.inputImage}>
                                    <Camera fontSize="large"/>
                                 </Avatar>
                            </label>
                        </Grid>
                        <Grid container item xs={8} justify="center">
                            <TextField
                                label="Nome"
                                margin="normal"
                                onChange ={this.handleChange("nome")}
                                id ='nome'                                 
                            />
                        </Grid>
                        <Grid container item xs={8} justify="center">
                            <TextField
                                label="Ingredientes"
                                margin="normal"
                                onChange ={this.handleChange("ingredientes")}
                                id ='ingredientes'                                
                            />
                        </Grid>
                        <Grid container item xs={8} justify="center">
                              <TextField
                                label="Preço"                              
                                margin="normal"
                                onChange ={this.handleChange("preco")}
                                type = "number"
                                id ='preco'                                  
                              />
                        </Grid>
                        <Grid container item xs={6} justify="center">
                          {cadastrar}
                        </Grid>
                     </Grid>
                    </form>
                </div>
        );
    }
}

export default withStyles(styles,{withTheme: true })(CadastroPrato)
