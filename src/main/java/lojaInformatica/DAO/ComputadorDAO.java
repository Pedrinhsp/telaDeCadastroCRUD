package lojainformatica.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import lojainformatica.model.Computador;

/**
 *
 * @author Pedrin
 */
public class ComputadorDAO {

    // nome do banco: lojaComputador
    public static String URL = "jdbc:mysql://localhost:3306/lojaComputador?UseTimezone=true&serverTimezone=UTC&useSSL=false";
    public static String login = "root";
    public static String senha = "";

    
    // inserir dados no banco de dados
    public static boolean salvar(Computador p) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

//Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
            conexao = DriverManager.getConnection(URL, login, senha); // (URL, LOGIN E SENHA)
            instrucaoSQL = conexao.prepareStatement("INSERT INTO componentes (modeloHD, modeloProcessador)VALUES( ?,  ?)", Statement.RETURN_GENERATED_KEYS); //Caso queira retornar o ID
//Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, p.getHD());
            instrucaoSQL.setString(2, p.getProcessador());
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys(); //Recupero o ID do cliente
                if (generatedKeys.next()) {
                    p.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID .");
                }
            } else {
                retorno = false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {
//Libero os recursos da memória
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
            } catch (SQLException ex) {
            }
        }
        return retorno;
    }
    
    public static ArrayList<Computador> consultarClientes(String pNome)
    {
        
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Computador> listaClientes = new ArrayList<Computador>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexao = DriverManager.getConnection(URL, login, senha);
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM componentes WHERE IdCliente LIKE ?;");
            
            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1,"%" + pNome + '%' );

            rs = instrucaoSQL.executeQuery();
            
            // Enquanto tiver linhas..
            while(rs.next())
            {
                Computador objCliente = new Computador();
                objCliente.setId(rs.getInt("IdCliente"));
                objCliente.setHD(rs.getString("modeloHD"));
                objCliente.setProcessador(rs.getString("modeloProcessador"));
                listaClientes.add(objCliente);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaClientes = null;
        } finally{
            //Libero os recursos da memória
            try {
                if(rs!=null)
                    rs.close();                
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                        
              } catch (SQLException ex) {
             }
        }
        
        return listaClientes;
    }
    
    public static ArrayList<Computador> consultarClientes()
    {
        
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Computador> listaClientes = new ArrayList<Computador>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexao = DriverManager.getConnection(URL, login, senha);
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM componentes;");
            

            rs = instrucaoSQL.executeQuery();
            
            // Enquanto tiver linhas..
            while(rs.next())
            {
                Computador objCliente = new Computador();
                objCliente.setId(rs.getInt("IdCliente"));
                objCliente.setHD(rs.getString("modeloHD"));
                objCliente.setProcessador(rs.getString("modeloProcessador"));
                listaClientes.add(objCliente);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaClientes = null;
        } finally{
            //Libero os recursos da memória
            try {
                if(rs!=null)
                    rs.close();                
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
            //  GerenciadorConexao.fecharConexao();
                        
              } catch (SQLException ex) {
             }
        }
        
        return listaClientes;
    }
    
    public static boolean atualizar(Computador p)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            
            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
            //conexao = GerenciadorConexao.abrirConexao();
            
            //Passo 1
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - DriverManager para abrir a conexão
            String URL = "jdbc:mysql://localhost:3306/lojaComputador?useTimezone=true&serverTimezone=UTC&useSSL=false";
            
            conexao = DriverManager.getConnection(URL, login, senha);
            
            instrucaoSQL = conexao.prepareStatement("UPDATE componentes SET modeloHD = ?, modeloProcessador=? WHERE IdCliente =? ");
            
            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, p.getHD());
            instrucaoSQL.setString(2, p.getProcessador());
            instrucaoSQL.setInt(3, p.getId());
            
            //Mando executar a instrução SQL
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
            }
            else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            //Libero os recursos da memória
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                //GerenciadorConexao.fecharConexao();
                conexao.close();
                
              } catch (SQLException ex) {
             }
        }
        
        return retorno;
    }
    
    public static boolean excluir(int pID){
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            
            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
            //conexao = GerenciadorConexao.abrirConexao();
            
            //Passo 1
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - DriverManager para abrir a conexão
            String URL = "jdbc:mysql://localhost:3306/lojaComputador?useTimezone=true&serverTimezone=UTC&useSSL=false";
            
            conexao = DriverManager.getConnection(URL, login, senha);
            
            instrucaoSQL = conexao.prepareStatement("DELETE FROM componentes WHERE idCliente = ?");
            
            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setInt(1, pID);

            //Mando executar a instrução SQL
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
            }
            else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            //Libero os recursos da memória
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                conexao.close();
                
              } catch (SQLException ex) {
             }
        }
        
        return retorno;
    }
    
}
