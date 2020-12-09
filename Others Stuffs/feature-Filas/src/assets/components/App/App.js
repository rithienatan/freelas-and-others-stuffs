import React from 'react';
import { CssBaseline, withStyles } from '@material-ui/core';
import PaginaLogin from '../PaginaLogin/PaginaLogin';
import Header from '../Header/Header';
import Alert from '../Alert/Alert';
import Dialogo from '../Dialogo/Dialogo';
import Cardapio from '../Cardapio/Cardapio'
import CadastroPrato from '../CadastroPrato/CadastroPrato'
import DialogoFullScreen from '../DialogoFullScreen/DialogoFullScreen';
import TabView from '../TabView/TabView';
import Clientes from '../Clientes/Clientes';
import CadastroClientes from '../CadastroClientes/CadastroClientes';
import fire from '../../utils/fire';
import BoxCliente from '../BoxCliente/BoxCliente';
import ListItemPrato from '../ListItemPrato/ListItemPrato'
import Grid from '@material-ui/core/Grid';

//imports para o funcionamento da fila------------------------------------------

//-------------para mostrar e deletar na fila--------------//
import Fila from '../Fila/Fila';

// Mostra todos os clientes que estão na fila
import MostrarNaFila from '../MostrarNaFila/MostrarNaFila';

//Mostra os dados de um cliente em particular e permite a opção remover da fila
 //import VerClienteNaFila from '../VerClienteNaFila/VerClienteNaFila'; <-- complemento de MostrarNaFila

//---------------para add na fila---------------//
//Mostra todos os clientes diponíveis para add na fila
import AdicionandoAFila from '../AdicionandoAFila/AdicionandoAFila';

//Mostra os dados de um cliente em particular e permite a opção de
//adicionar à fila
import SelectionFilaBox from '../SelectionFilaBox/SelectionFilaBox';
  //import AddFilaBox from '../AddFilaBox/AddFilaBox'; <-- complemento de SelectionFilaBox

//end imports fila--------------------------------------------------------------

const styles = theme => ({
    root: {
        overflowX: 'hidden',
        height: '100vh'
    },
    spacing:{
        marginBottom:'20px'
    }
})

const db = fire.firestore()

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            contentShown: null,
            lastContentShown: null,
            alert: {
                isOpen: false
            },
            dialog: {
                isOpen: false,
                conteudo: ''
            },
            dialogFullScreen: {
                isOpen: false,
                titulo: null,
                conteudo: null
            }
        }
    }

    //Mostrar dialogFullScreen -> recebe index da tab atual
    showDialogFullScreen = (number) => {
        /*Conteudo dos dialogsFullScreen
        [0] Tab da esquerda
        [1] Tab do meio
        [2] Tab da direita
        */
        let dialogFullScreenConteudo = [
            ({
                titulo: "Cadastrar Clientes",
                conteudo: <CadastroClientes closeDialogFullScreen={this.closeDialogFullScreen} showAlert={this.showAlert} updateComponentWithDatabase={this.updateComponentWithDatabase}/>
            }),
            ({
                titulo: "Cadastrar Pratos",
                conteudo: <CadastroPrato closeDialogFullScreen={this.closeDialogFullScreen} showAlert={this.showAlert} updateComponentWithDatabase={this.updateComponentWithDatabase}/>
            }),
            ({
                titulo: "Adicionar à Fila",
                conteudo: <AdicionandoAFila closeDialogFullScreen={this.closeDialogFullScreen} showAlert={this.showAlert} listaFilaClientes={this.state.listaFilaClientes} updateComponentWithDatabase={this.updateComponentWithDatabase}/>
            })
        ]
        this.setState({ dialogFullScreen: {isOpen: true, titulo: dialogFullScreenConteudo[number].titulo, conteudo: dialogFullScreenConteudo[number].conteudo} })
    }

    //Mostra dialog
    showDialog = (content) => {
        this.setState({dialog: {isOpen: true, conteudo: content}})
    }

    //Mudar pagina mostrada
    showPage = (page) => this.setState({ lastContentShown: this.state.contentShown, contentShown: page })

    //Mostrar alertas
    showAlert = (msg, type) => msg ? this.setState({ alert: { msg: msg, isOpen: true, type: type } }) : null;

    //Fecha DialogFullScreen
    closeDialogFullScreen = () => {
        this.setState({dialogFullScreen: {isOpen: false, titulo: null, conteudo: null}})
    }

    //Fecha dialog
    closeDialog = () => {
        this.setState({dialog: {isOpen: false, conteudo: ''}})
    }

    //Fechar alert
    closeAlert = (options) => {
        this.setState({ alert: { msg: options.msg, isOpen: false, type: options.type } })
    }

    //Voltar para a pagina anterior
    voltaPagina = () => {
        this.setState({ contentShown: this.state.lastContentShown, lastContentShown: this.state.contentShown })
    }

    //Atualizar componente com database
    updateComponentWithDatabase = collection =>{
        switch(collection){
            case "clientes":
                this.updateClientesWithDatabase()
                break
            case "pratos":
                this.updatePratosWithDatabase()
                break
            case "fila":
                this.updateFilaWithDatabase()
                break
            default:
                break
        }
    }
    updateClientesWithDatabase(){
        db.collection("clientes").get().then(querySnapshot => {
            this.setState({listaClientes: null})
            let listaCliente = []
            let i = 0
            querySnapshot.forEach(doc => {
                let infoCliente = {
                    id: doc.id,
                    nome: doc.data()['nome'],
                    idade: doc.data()['idade'],
                    telefone: doc.data()['telefone'],
                    email: doc.data()['email'],
                    imagem: doc.data()['imagem']
                }
                let info = infoCliente
                listaCliente.push(<BoxCliente key={i} info={info} showDialog={this.showDialog} deleteItemDatabase={this.deleteItemDatabase} updateClientesWithDatabase={this.updateClientesWithDatabase}/>)
                i++
            })
            this.setState({listaClientes: listaCliente})
        })
    }
    updatePratosWithDatabase(){
        const { classes } = this.props
        db.collection("pratos").get().then(e =>{
            this.setState({listaPratos: null})
            let pratos = []
            let key = 0
            e.forEach((element) =>{
                let dados ={
                    nome: element.data()['nome'],
                    ingredientes: element.data()['ingredientes'],
                    preco: element.data()['preco'],
                    caminho: element.data()['caminho']
                }
                if(key % 3 === 0){
                    pratos.push(
                        <Grid container item justify="center" xs={12} key={key} className={classes.spacing}>
                            <ListItemPrato dados={dados} key={key} big={true}/>
                        </Grid>)
                }else{
                    pratos.push(
                                <Grid container item justify="center" xs={12} key={key} className={classes.spacing}>
                                    <ListItemPrato dados={dados} key={key}/>
                                </Grid>)
                }
                key++
            })
            this.setState({listaPratos : pratos})
        })
    }
    updateFilaWithDatabase(){
      //reaproveitando componentes para o gerenciamento da fila
      db.collection("clientes").get().then(querySnapshot => {
          this.setState({listaFilaClientes: null})
          let listaFilaCliente = []
          let i = 0
          querySnapshot.forEach(doc => {
              let infoFilaCliente = {
                  id: doc.id,
                  nome: doc.data()['nome'],
                  idade: doc.data()['idade'],
                  telefone: doc.data()['telefone'],
                  email: doc.data()['email'],
                  imagem: doc.data()['imagem']
              }
              let info = infoFilaCliente
              listaFilaCliente.push(<SelectionFilaBox key={i} info={info} showDialog={this.showDialog} deleteItemDatabase={this.deleteItemDatabase} updateFilaWithDatabase={this.updateFilaWithDatabase}/>)
              i++
          })
          this.setState({listaFilaClientes: listaFilaCliente})
      })//end db.collection("clientes")

      //para o bd de fila
      db.collection("fila").get().then(querySnapshot => {
          this.setState({showFila: null})
          let showFila = []
          let i = 0
          querySnapshot.forEach(doc => {
              let showFila = {
                  id: doc.id,
                  nome: doc.data()['nome'],
                  idade: doc.data()['idade'],
                  telefone: doc.data()['telefone'],
                  email: doc.data()['email'],
                  //imagem: doc.data()['imagem']
              }
              let info = showFila
              showFila.push(<MostrarNaFila key={i} info={info} showDialog={this.showDialog} deleteItemDatabase={this.deleteItemDatabase} updateFilaWithDatabase={this.updateFilaWithDatabase}/>)
              i++
          })
          this.setState({showFila: showFila})
      })//end db.collection("fila")
    }//end updateFilaWithDatabase()

    //Deletar Item
    deleteItemDatabase = (collection, id) =>{
        db.collection(collection).doc(id).delete().then(()=>{
            this.showAlert("Item excluído com sucesso")
            this.closeDialog()
            this.updateClientesWithDatabase("clientes")
        }).catch(()=>{
            this.showAlert("Erro ao excluir", "error")
        })
    }

    render() {
        const { classes } = this.props;

        let content = null;
        let headerText = null;
        let isDisplayBackButton = false;

        //Vetor de paginas, coloquer as 3 paginas a serem mostradas aqui:
        let paginas = [

            (<Clientes key={1} showAlert={this.showAlert} listaClientes={this.state.listaClientes} updateComponentWithDatabase={this.updateComponentWithDatabase}/>),
            (<Cardapio key={2} showAlert={this.showAlert} listaPratos={this.state.listaPratos} updateComponentWithDatabase={this.updateComponentWithDatabase}/>),
            (<Fila key={3} showAlert={this.showAlert} showFila={this.state.showFila} updateComponentWithDatabase={this.updateComponentWithDatabase} />),
        ]

        switch (this.state.contentShown) {
            case 'Admin':
                content = (<TabView paginas={paginas} showDialogFullScreen={this.showDialogFullScreen} />);
                headerText = 'Admin';
                isDisplayBackButton = false;
                break;
            default:
                content = (<PaginaLogin showAlert={this.showAlert} showPage={this.showPage} />);
                headerText = 'Login';
                isDisplayBackButton = false;
                break;
        }

        return (
            <CssBaseline>
                <div className={classes.root}>
                    <Header text={headerText} isDisplayButton={isDisplayBackButton} voltaPagina={this.voltaPagina} />
                    {content}
                    <Alert options={this.state.alert} closeAlert={this.closeAlert} />
                    <DialogoFullScreen options={this.state.dialogFullScreen} closeDialogFullScreen={this.closeDialogFullScreen} />
                    <Dialogo options={this.state.dialog} closeDialog={this.closeDialog} />
                </div>
            </CssBaseline>
        )
    }
}

export default withStyles(styles, { withTheme: true })(App);
