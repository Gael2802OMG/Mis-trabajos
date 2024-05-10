
package Modelo;

import javax.swing.table.DefaultTableModel;

public interface dbPersistenciaMostrar {  
    public DefaultTableModel listar(String sql)throws Exception; 
}
