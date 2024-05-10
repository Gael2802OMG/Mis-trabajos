
package Modelo;

import javax.swing.DefaultComboBoxModel;


public interface dbPersistencia {  
    public void insertar(Object objecto) throws Exception;
    public void Prestamo(Object objecto) throws Exception;
    public void actualizar(Object objecto) throws Exception;
    public DefaultComboBoxModel id()throws Exception;
    public Object buscar(String codigo)throws Exception;
    public Object buscar(int codigo)throws Exception;
    public void deshabilitar(String codigo)throws Exception;
    public void Devolucion(Object objecto)throws Exception;
    public void Detallesprest(Object objecto)throws Exception;
    
}
