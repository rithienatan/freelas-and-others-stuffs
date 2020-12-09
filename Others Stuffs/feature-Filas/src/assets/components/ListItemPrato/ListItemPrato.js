import React from 'react';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import Avatar from '@material-ui/core/Avatar';
import Typography from '@material-ui/core/Typography';
import Fire from '../../utils/fire'
import { withStyles } from '@material-ui/core'

    //--Styles --//
const styles = theme =>({
    imgBig:{
        borderRadius:"0px",
        height:'250px',
        width:'100%'
    },
    imgSmall:{
        borderRadius:"0px",
        height:'100%',
        width:'100%',
        maxWidth:'100px'
    },
    boxBig:{
        height:'350px',
        maxWidth:'900px',
        width:'90vw'
    },
    boxSmall:{
        height:'100px',
        maxWidth:'900px',
        width:'90vw'
    },
    spacing:{
        padding:'10px'
    },
    smallHeight:{
        height:'100px'
    },
    bigHeight:{
        height:'350px'
    }
})
    //--FimStyles--//
class ListItemPrato extends React.Component{
    
    //construtor
    constructor(props){
        super(props);
        this.state = {
        }
    }

    componentDidMount = () =>{
        const storage = Fire.storage();
        storage.ref().child(this.props.dados.caminho).getDownloadURL().then(img=>{
            this.setState({file : img})
        })
    }

    render(){
        const{classes} = this.props
        let construir = null

        //ListItem Grande
        if(this.props.big){
            construir = (<Box className={classes.boxBig} boxShadow={3}>
                            <Grid container direction='row' justify="space-between" className={classes.bigHeight}>

                                <Grid container item xs={12}>
                                    <Avatar src={this.state.file} className={classes.imgBig}/>
                                </Grid>

                                <Grid item className={classes.spacing} xs={12}>

                                    <Typography component="h5" variant="h5">
                                        {this.props.dados.nome}
                                    </Typography>
                                
                                    <Typography >
                                        {this.props.dados.ingredientes}
                                    </Typography>
                            
                                    <Typography variant="subtitle1">
                                        {this.props.dados.preco} R$
                                    </Typography>
    
                                </Grid>

                            </Grid>
                        </Box>)
        //ListItem Pequeno
        }else{
            construir = (<Box className={classes.boxSmall} boxShadow={3}>
                            <Grid container direction='row' justify="space-between" className={classes.smallHeight}>
                                <Grid item className={classes.spacing} xs={8}>

                                    <Typography component="h5" variant="h5">
                                        {this.props.dados.nome}
                                    </Typography>
                                
                                    <Typography >
                                        {this.props.dados.ingredientes}
                                    </Typography>
                            
                                    <Typography variant="subtitle1">
                                        {this.props.dados.preco} R$
                                    </Typography>
                                
                                </Grid>
                                <Grid container item justify='flex-end' xs={4}>
                                    <Avatar src={this.state.file} className={classes.imgSmall}/>
                                </Grid>
                            </Grid>
                        </Box>)
        }
        return(
            <div>
                {construir}
            </div>
        )
    }
}

export default withStyles(styles,{withTheme: true })(ListItemPrato)
