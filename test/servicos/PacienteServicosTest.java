
package servicos;

import exceptions.RequiredFieldsValidator;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modelo.Paciente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import visao.GuiCadPaciente;

/**
 *
 * @author user
 */
public class PacienteServicosTest {
    
     @Mock
     PacienteServicos services = new PacienteServicos();
     
     GuiCadPaciente pac = new GuiCadPaciente();
     
     Paciente p = new Paciente();
     Paciente p2 = new Paciente();
    
    public PacienteServicosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {    
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ParseException {
        MockitoAnnotations.initMocks(this);
        startPaciente();
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void whenCadastrarPacienteThenReturnSucess() throws SQLException{
        
        Mockito.doNothing().when(services).cadastrarPaciente(p);
        
        services.cadastrarPaciente(p);
       
        //Verifica se o cadastro do paciente foi um uscesso
        Mockito.verify(services).cadastrarPaciente(p);
        //Verifica se o metodo de cadastro foi chamado apenas uma vez
        Mockito.verify(services,Mockito.times(1)).cadastrarPaciente(p);
        
    }
    
    
    @Test
    public void whenCadastrarPacienteThenReturnFail() throws SQLException{
        
         //Configura o mock para lançar uma SQLException com uma mensagem específica
         Mockito.doThrow(new SQLException("Erro ao cadastrar paciente")).when(services).cadastrarPaciente(p);
         
         try{
             
             services.cadastrarPaciente(p);
             //Se a exceção não for lançada, o teste falhou
             fail("Esperava lançar uma SQLException, mas não lançou.");
             
         }catch(SQLException e){
             
             // Verifica se a exceção capturada é realmente uma SQLException
             assertEquals(SQLException.class, e.getClass());
             //Verifica se a mensagem da exceção é a esperada
             assertEquals("Erro ao cadastrar paciente",e.getMessage());
         }
         
         //Verifica se o método cadastrarPaciente foi chamado exatamente uma vez
         Mockito.verify(services, Mockito.times(1)).cadastrarPaciente(p);
         //Verifica se nenhum outro método foi chamado no mock após a exceção
         Mockito.verifyNoMoreInteractions(services);
    }


       
        
        
    

    
  
     public void startPaciente() throws ParseException {
              
        p.setIdPaciente(1);
        p.setNome("Matheus Souza");
        p.setTelefone("(31)9123-1234");
        p.setCpf("12312312325");
        p.setEmail("matheus@gmail.com");
        p.setEndereco("bairro castelo , rua machado 291");
        p.setIdConvenio(1);
        p.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2000"));
        p.setRg("1232131231");
        
      
        p2.setIdPaciente(2);
        p2.setNome("Janaina");
        p2.setTelefone("(31)9123-4321");
        p2.setCpf("123333335");
        p2.setEmail("janaina@gmail.com");
        p2.setEndereco("bairro castelo , rua castelo de elis 300");
        p2.setIdConvenio(1);
        p2.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2020"));
        p2.setRg("1231211231");
        
    }
 
    
}
