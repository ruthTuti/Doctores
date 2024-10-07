package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Doctores {
    
    public String getUUID_Doctor() {
        return UUID_Doctor;
    }

    public void setUUID_Doctor(String UUID_Doctor) {
        this.UUID_Doctor = UUID_Doctor;
    }

    public String getNombre_Doctor() {
        return Nombre_Doctor;
    }

    public void setNombre_Doctor(String Nombre_Doctor) {
        this.Nombre_Doctor = Nombre_Doctor;
    }

    public int getEdad_Doctor() {
        return Edad_Doctor;
    }

    public void setEdad_Doctor(int Edad_Doctor) {
        this.Edad_Doctor = Edad_Doctor;
    }

    public double getPeso_Doctor() {
        return Peso_Doctor;
    }

    public void setPeso_Doctor(double Peso_Doctor) {
        this.Peso_Doctor = Peso_Doctor;
    }

    public String getCorreo_Doctor() {
        return Correo_Doctor;
    }

    //1- Parametros
    public void setCorreo_Doctor(String Correo_Doctor) {
        this.Correo_Doctor = Correo_Doctor;
    }
    String UUID_Doctor;
    String Nombre_Doctor;
    int Edad_Doctor;
    double Peso_Doctor;
    String Correo_Doctor;
    
    
    
      //Ingresar
    public void Guardar() {
    Connection conexion = claseConexion.getConexion();
    if (conexion == null) {
        System.out.println("Error: La conexión es nula.");
        return;
    }

    try {
        
        PreparedStatement addDoctor = conexion.prepareStatement("INSERT INTO tbDoctor(UUID_Doctor, Nombre_Doctor, Edad_Doctor, Peso_Doctor, Correo_Doctor) VALUES (?, ?, ?, ?, ?)");
        
        // Establecer los parámetros
        addDoctor.setString(1, UUID.randomUUID().toString());  
        addDoctor.setString(2, getNombre_Doctor()); 
        addDoctor.setInt(3, getEdad_Doctor()); 
        addDoctor.setDouble(4, getPeso_Doctor()); 
        addDoctor.setString(5, getCorreo_Doctor()); 
        
        
        addDoctor.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("este es el error en el modelo: metodo guardar " + ex);
    }
}

    
    //MOSTRAR
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = claseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDoctores = new DefaultTableModel();
        
        modeloDoctores.setColumnIdentifiers(new Object[]{"UUID_Doctor", "Nombre_Doctor", "Edad_Doctor", "Peso_Doctor", "Correo_Doctor"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbDoctor");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDoctores.addRow(new Object[]{rs.getString("UUID_Doctor"), 
                    rs.getString("Nombre_Doctor"), 
                    rs.getInt("Edad_Doctor"), 
                    rs.getString("Peso_Doctor"),
                    rs.getString("Correo_Doctor")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDoctores);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    //ACTUALIZAR
    
    public void Actualizar(JTable tabla) {
    // Creamos una variable igual a ejecutar el método de la clase de conexión
    Connection conexion = claseConexion.getConexion();
    // Obtenemos que fila seleccionó el usuario
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        // Obtenemos el UUID de la fila seleccionada
        String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        try { 
            // Ejecutamos la Query para actualizar la tabla tbDoctor
            PreparedStatement updateDoctor = conexion.prepareStatement("UPDATE tbDoctor SET Nombre_Doctor = ?, Edad_Doctor = ?, Peso_Doctor = ?, Correo_Doctor = ? WHERE UUID_Doctor = ?");
            
            // Establecer los valores a actualizar
            updateDoctor.setString(1, getNombre_Doctor()); // Asegúrate de que este método esté definido
            updateDoctor.setInt(2, getEdad_Doctor()); // Asegúrate de que este método esté definido
            updateDoctor.setDouble(3, getPeso_Doctor()); // Asegúrate de que este método esté definido
            updateDoctor.setString(4, getCorreo_Doctor()); // Asegúrate de que este método esté definido
            updateDoctor.setString(5, miUUID); // Usamos el UUID de la fila seleccionada
            
            // Ejecutar la actualización
            updateDoctor.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error en el método de actualizar: " + e);
        }
    } else {
        System.out.println("No se seleccionó ninguna fila para actualizar.");
    }
}

    
    
    //ELIMINAR
    
     public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbDoctor where UUID_Doctor = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
     
     
        
}
