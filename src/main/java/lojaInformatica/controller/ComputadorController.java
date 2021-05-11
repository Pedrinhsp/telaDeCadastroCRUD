package lojaInformatica.controller;

import lojainformatica.DAO.ComputadorDAO;
import lojainformatica.model.Computador;

/**
 *
 * @author Pedrin
 */
public class ComputadorController {
    
    public static boolean salvar(String pHD, String pProcessador){
        Computador comp = new Computador();
        comp.setHD(pHD);
        comp.setProcessador(pProcessador);

        return ComputadorDAO.salvar(comp);
    }
    
    public static boolean atualizar(int pID,String pHD, String pProcessador){
        Computador comp = new Computador();
        comp.setId(pID);
        comp.setHD(pHD);
        comp.setProcessador(pProcessador);

        return ComputadorDAO.atualizar(comp);
    }
    
     public static boolean excluir(int pID){
        return ComputadorDAO.excluir(pID);
    }
    
}
