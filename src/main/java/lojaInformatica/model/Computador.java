package lojainformatica.model;

/**
 *
 * @author Pedrin
 */
public class Computador {
    
    private static String marca = "Pedro Hen";
    private int Id;
    private String HD;
    private String processador;

    public Computador(){}
    
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public Computador(String HD, String processador) {
        this.HD = HD;
        this.processador = processador;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }
    
    public static String getMarca() {
        return marca;
    }

    public static void setMarca(String marca) {
        Computador.marca = marca;
    }
    
    
}
