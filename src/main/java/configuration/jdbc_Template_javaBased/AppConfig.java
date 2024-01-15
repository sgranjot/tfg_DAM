package configuration.jdbc_Template_javaBased;

import servicios.implementacion.ServicioDireccion;
import repositorios.implementacion.DireccionDao;
import repositorios.implementacion.ArrendatarioDao;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorArrendatario;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorContrato;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorDireccion;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorGasto;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorMensualidad;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorPropiedad;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorTipoGasto;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorTipoPropiedad;
import configuration.jdbc_Template_javaBased.rowmappers.MapeadorUsuario;
import servicios.implementacion.ServicioArrendatario;
import servicios.implementacion.ServicioContrato;
import servicios.implementacion.ServicioGasto;
import servicios.implementacion.ServicioMensualidad;
import servicios.implementacion.ServicioPropiedad;
import servicios.implementacion.ServicioTipoPropiedad;
import repositorios.implementacion.UsuarioDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import servicios.implementacion.ServicioUsuario;
import repositorios.implementacion.ContratoDao;
import repositorios.implementacion.GastoDao;
import repositorios.implementacion.MensualidadDao;
import repositorios.implementacion.PropiedadDao;
import repositorios.implementacion.TipoGastoDao;
import repositorios.implementacion.TipoPropiedadDao;
import servicios.implementacion.ServicioTipoGasto;

@Configuration
public class AppConfig {
    
    @Bean
    public DriverManagerDataSource miDataSource (){
        DriverManagerDataSource dmds  = new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost/tfg?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true");
        dmds.setUsername("root");
        dmds.setPassword("root"); 
        return dmds;
    }
    
    @Bean
    public JdbcTemplate template(){                
        return new JdbcTemplate(miDataSource());             
    }
      
    //USUARIO
    @Bean
    public MapeadorUsuario mapeadorUsuario (){ 
        return new MapeadorUsuario ();
    }
    
    @Bean
    public UsuarioDao usuarioDao (){
        return new UsuarioDao (template(), mapeadorUsuario());   
    }
    
    @Bean
    public ServicioUsuario servicioUsuario(){
        return new ServicioUsuario(usuarioDao(), template(), mapeadorUsuario());
    }
    
    
    
    //ARRENDATARIO
    @Bean
    public MapeadorArrendatario mapeadorArrendatario (){ 
        return new MapeadorArrendatario ();
    }
    
    @Bean
    public ArrendatarioDao arrendatarioDao (){
        return new ArrendatarioDao (template(), mapeadorArrendatario());   
    }
    
    @Bean
    public ServicioArrendatario servicioArrendatario(){
        return new ServicioArrendatario(arrendatarioDao(), template(), mapeadorArrendatario());
    }
    
    
    
    //DIRECCION
    @Bean
    public MapeadorDireccion mapeadorDireccion (){ 
        return new MapeadorDireccion ();
    }
    
    @Bean
    public DireccionDao direccionDao (){
        return new DireccionDao (template(), mapeadorDireccion());   
    }
    
    @Bean
    public ServicioDireccion servicioDireccion(){
        return new ServicioDireccion(direccionDao(), template(), mapeadorDireccion());
    }  
   
    
    
    //PROPIEDAD
    @Bean
    public MapeadorPropiedad mapeadorPropiedad (){ 
        return new MapeadorPropiedad ();
    }
    
    @Bean
    public PropiedadDao propiedadDao (){
        return new PropiedadDao (template(), mapeadorPropiedad());   
    }
    
    @Bean
    public ServicioPropiedad servicioPropiedad(){
        return new ServicioPropiedad(propiedadDao(), template(), mapeadorPropiedad());
    }  
    
    
    
    //CONTRATO
    @Bean
    public MapeadorContrato mapeadorContrato (){ 
        return new MapeadorContrato ();
    }
    
    @Bean
    public ContratoDao contratoDao (){
        return new ContratoDao (template(), mapeadorContrato());   
    }
    
    @Bean
    public ServicioContrato servicioContrato(){
        return new ServicioContrato(contratoDao(), template(), mapeadorContrato());
    }  

    
    
    //MENSUALIDAD
    @Bean
    public MapeadorMensualidad mapeadorMensualidad (){ 
        return new MapeadorMensualidad ();
    }
    
    @Bean
    public MensualidadDao mensualidadDao (){
        return new MensualidadDao (template(), mapeadorMensualidad());   
    }
    
    @Bean
    public ServicioMensualidad servicioMensualidad(){
        return new ServicioMensualidad(mensualidadDao(), template(), mapeadorMensualidad());
    } 
    
    
    
    //GASTO
    @Bean
    public MapeadorGasto mapeadorGasto (){ 
        return new MapeadorGasto ();
    }
    
    @Bean
    public GastoDao gastoDao (){
        return new GastoDao (template(), mapeadorGasto());   
    }
    
    @Bean
    public ServicioGasto servicioGasto(){
        return new ServicioGasto(gastoDao(), template(), mapeadorGasto());
    } 
    
    
    //TIPO PROPIEDAD
    @Bean
    public MapeadorTipoPropiedad mapeadorTipoPropiedad (){ 
        return new MapeadorTipoPropiedad ();
    }
    
    @Bean
    public TipoPropiedadDao tipoPropiedadDao (){
        return new TipoPropiedadDao (template(), mapeadorTipoPropiedad());   
    }
    
    @Bean
    public ServicioTipoPropiedad servicioTipoPropiedad(){
        return new ServicioTipoPropiedad(tipoPropiedadDao(), template(), mapeadorTipoPropiedad());
    } 
    
    
    //TIPO GASTO
    @Bean
    public MapeadorTipoGasto mapeadorTipoGasto (){ 
        return new MapeadorTipoGasto ();
    }
    
    @Bean
    public TipoGastoDao tipoGastoDao (){
        return new TipoGastoDao (template(), mapeadorTipoGasto());   
    }
    
    @Bean
    public ServicioTipoGasto servicioTipoGasto(){
        return new ServicioTipoGasto(tipoGastoDao(), template(), mapeadorTipoGasto());
    } 
}
