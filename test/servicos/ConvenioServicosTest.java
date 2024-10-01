/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Convenio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author user
 */
public class ConvenioServicosTest {
    
    @Mock
    ConvenioServicos conv = new ConvenioServicos();
    
    ArrayList<Convenio> convenios = new ArrayList();
    
    Convenio c1 = new Convenio(1,"Convenio teste 1","20 dias","123123131");
     Convenio c2 = new Convenio(2,"Convenio teste 2","30 dias","232131");
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        convenios.add(c1);
        convenios.add(c2);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void  whenBuscarCoveniosThenReturnListConvenios() throws SQLException{
        
        //Configura um mock para buscar convenois e retornar uma lista dos convenios encontrados
        Mockito.when(conv.buscarConvenio()).thenReturn(convenios);
        
        //Cria uma lista com os convenios buscados
        ArrayList<Convenio> resposta = conv.buscarConvenio();
        
        //Verifica se a lista de convenios nao é nula
        assertNotNull(resposta);
        //Verifica se a clase da lista é uma list de convenios
        assertEquals(resposta.getClass(),convenios.getClass());
        
        //Verifica se os dados dos convenios encontrados sao o mesmo que os esperados
        assertEquals(1,resposta.get(0).getIdConvenio());
        assertEquals("Convenio teste 1",resposta.get(0).getNomeConvenio());
        assertEquals("20 dias",resposta.get(0).getTempoCarencia());
        assertEquals("123123131",resposta.get(0).getCnpj());
        
        assertEquals(2,resposta.get(1).getIdConvenio());
        assertEquals("Convenio teste 2",resposta.get(1).getNomeConvenio());
        assertEquals("30 dias",resposta.get(1).getTempoCarencia());
        assertEquals("232131",resposta.get(1).getCnpj());
        
    }
    
    @Test
    public void whenBuscarConvenioReturnSQLException() throws SQLException{
        
        //Simula uma SQLException
        Mockito.when(conv.buscarConvenio()).thenThrow(new SQLException("Erro ao buscar convênios"));
        
        //Verifica se a exceção foi lançada corretamente
        try{
            
            
         conv.buscarConvenio();
         fail("Esperado uma SQLException ser capturada");
            
        }catch(SQLException ex){
            
            //Verifica se a exceção esperada foi SQLException
            assertEquals(SQLException.class,ex.getClass());
            //Verifica se a mensagem de erro foi a esperada Erro ao buscar convênios
            assertEquals("Erro ao buscar convênios",ex.getMessage());
        }
        
    }

   
}
