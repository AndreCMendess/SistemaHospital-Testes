
package visao;

import dao.ConvenioDAO;
import dao.PacienteDAO;
import exceptions.RequiredFieldsValidator;
import exceptions.ValidationException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Convenio;
import modelo.Paciente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoAssertionError;


public class GuiCadPacienteTest {
    
    @Mock
    PacienteDAO pacDAO = new PacienteDAO();
    
    @Mock
    GuiCadPaciente gPac = new GuiCadPaciente();
    
    Paciente paciente = new Paciente();
    
    @Mock
    ConvenioDAO convDAO = new ConvenioDAO();
    
    
    public GuiCadPacienteTest() {
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
    public void whenCadastrarPacienteThenReturnSucessGui() throws SQLException{
        
        //Cria uma lista de pacientes e adiciona o paciente testado
        ArrayList<Paciente> pacientesCadastrados = new ArrayList<>();
        pacientesCadastrados.add(paciente);
        
        //Configura omock para cadastrado e busca de pacientes
        Mockito.doNothing().when(pacDAO).cadastrarPaciente(paciente);
        Mockito.when(pacDAO.buscarPacienteFiltro(paciente.getNome())).thenReturn(pacientesCadastrados);
        
        
        //Valida se o paciente esta com os dados validos e todos os campos obrigatorios preenchidos
        gPac.validarCamposObrigatorios(paciente);
        gPac.validarDadosEntrada(paciente);
             
        //Configura o mock para buscar um convenio e verifica suas informações
        Mockito.when(convDAO.buscarConvenioFiltro("Convenio XYZ")).thenReturn(new Convenio(1,"Convenio Teste","30 dias","123131321"));
        
        //Cria uma ArrayList de paciente e adiciona o paciente
        ArrayList<Paciente> resposta = pacDAO.buscarPacienteFiltro(paciente.getNome());
        
        //Instancia o convenio e faça receber o convenio que foi buscado pelo filtro 
        Convenio convenioResposta = convDAO.buscarConvenioFiltro("Convenio XYZ");
        
        //Define o convenio do paciente pelo convenio que foi encontrado acima
        paciente.setConvenio(convenioResposta.getIdConvenio());
        
        //Verifica se a lista de pacientes nao é nula
        assertNotNull(resposta);   
        
        //Verifica se o convenio do paciente é o mesmo que o convenio encontrado  pela busca
        assertEquals(paciente.getIdConvenio(),convenioResposta.getIdConvenio());
        //Verifica se os dias de carencia do convenio encontrado é de 30 dias 
        assertEquals("30 dias",convenioResposta.getTempoCarencia());
        
        
        //Verifica se o nome do primeiro paciente da lista é o mesmo do paciente 
        assertEquals(paciente.getNome(),resposta.get(0).getNome());
         //Verifica se o cpf do primeiro paciente da lista é o mesmo do paciente 
        assertEquals(paciente.getCpf(),resposta.get(0).getCpf());
         //Verifica se o email do primeiro paciente da lista é o mesmo do paciente 
        assertEquals(paciente.getEmail(),resposta.get(0).getEmail());
         //Verifica se o convenio do primeiro paciente da lista é o mesmo do paciente 
        assertEquals(paciente.getConvenio(),resposta.get(0).getConvenio());
         //Verifica se o telefone do primeiro paciente da lista é o mesmo do paciente 
        assertEquals(paciente.getTelefone(),resposta.get(0).getTelefone());
         //Verifica se o rg do primeiro paciente da lista é o mesmo do paciente 
        assertEquals(paciente.getRg(),resposta.get(0).getRg());
         //Verifica se o endereço do primeiro paciente da lista é o mesmo do paciente 
        assertEquals(paciente.getEndereco(), resposta.get(0).getEndereco());
      
    }
  
    @Test
    public void whenCadastrarPacienteThenReturnRequiriedFieldValidator() throws SQLException {

        //Define os dados do paciente como null para simula a ausencia em campos obrigatorios
        paciente.setNome(null);
        paciente.setTelefone(null);

        // Configura o mock para lançar uma exceção do tipo RequiredFieldsValidator
        // quando o método validarCamposObrigatorios(paciente) for chamado.
        // Isso simula um cenário em que os campos obrigatórios não foram preenchidos.
        Mockito.doThrow(new RequiredFieldsValidator("Campos obrigatorios ausentes")).when(gPac).validarCamposObrigatorios(paciente);

        try {

            // Tenta validar os campos do paciente, o que resulta a exceção
            gPac.validarCamposObrigatorios(paciente);

            // Se a exceção não for lançada , causa falha no teste
            fail("Esperado uma exceção de RequiriedFieldValidator");

        } catch (RequiredFieldsValidator ex) {

            //Verifica se a exceção lançada é do tipo esperado
            //Isso confirma que a validação funcionou corretamente ao lançar a exceção adequada
            assertEquals(RequiredFieldsValidator.class, ex.getClass());

        }

    }
    
    @Test
    public void whenCadastrarPacienteThenReturnValidationException() throws SQLException {

        //Define os dados de entrada de forma invalida para simular dados incorretos
        paciente.setTelefone("asdasda231");

        
        Mockito.doThrow(new ValidationException("Dados devem ser validos")).when(gPac).validarDadosEntrada(paciente);

        try {

            //Tenta validar os dados de netrada , oque resulta na exceção por dados invalidos para o telefone
            gPac.validarDadosEntrada(paciente);

            //Se a exceção não for lançada , causa falha no teste
            fail("Esperado uma exceção de ValidationException");

        } catch (ValidationException ex) {

             // Verifica se a exceção lançada é do tipo esperado (ValidationException)
             // Isso confirma que a validação funcionou corretamente ao lançar a exceção adequada
            assertEquals(ValidationException.class, ex.getClass());

        }

    }


    public void startPaciente() throws ParseException {
              
        paciente.setIdPaciente(1);
        paciente.setNome("Matheus Souza");
        paciente.setTelefone("(31)9123-1234");
        paciente.setCpf("12312312325");
        paciente.setEmail("matheus@gmail.com");
        paciente.setEndereco("bairro castelo , rua machado 291");
        paciente.setIdConvenio(1);
        paciente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2000"));
        paciente.setRg("1232131231");
    }
    
}
