package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Doctores;
import vista.frmDoctores;



public class ctrlDoctores implements MouseListener{

    //1- Mandar a llamar a las otras dos capas (modelo, vista)
    private Doctores Modelo;
    private frmDoctores vista;
    
    //2- Crear el constructor de la clase
    public ctrlDoctores(Doctores ModeloP, frmDoctores vistaP){
        this.Modelo = ModeloP;
        this.vista = vistaP;
        
        //Siempre poner todos los botones que vamos a ocupar
        vistaP.btnGuardar.addMouseListener(this);
        vistaP.btnActualizar.addMouseListener(this);
        vistaP.btnEliminar.addMouseListener(this);
        vistaP.btnLimpiar.addMouseListener(this);
        vistaP.jtbDoctores.addMouseListener(this);
        
        Modelo.Mostrar(vistaP.jtbDoctores);
    }
    
    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        vista.txtNombre.setText("");  // Limpia el campo de nombre
        vista.txtEdad.setText("");    // Limpia el campo de edad
        vista.txtPeso.setText("");    // Limpia el campo de peso
        vista.txtCorreo.setText("");   // Limpia el campo de correo
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.btnGuardar){
         
            Modelo.setNombre_Doctor(vista.txtNombre.getText());
            Modelo.setEdad_Doctor( Integer.parseInt(vista.txtEdad.getText()));
            Modelo.setPeso_Doctor(Double.parseDouble(vista.txtPeso.getText()));
            Modelo.setCorreo_Doctor(vista.txtCorreo.getText());

            
            Modelo.Guardar();  
            Modelo.Mostrar(vista.jtbDoctores);
            limpiarCampos();
        }
        
        if(e.getSource() == vista.btnEliminar){
            Modelo.Eliminar(vista.jtbDoctores);
            Modelo.Mostrar(vista.jtbDoctores);
            limpiarCampos();
            
        }
        
        if (e.getSource() == vista.btnActualizar) { 
        Modelo.setNombre_Doctor(vista.txtNombre.getText());
        Modelo.setEdad_Doctor(Integer.parseInt(vista.txtEdad.getText()));
        Modelo.setPeso_Doctor(Double.parseDouble(vista.txtPeso.getText()));
        Modelo.setCorreo_Doctor(vista.txtCorreo.getText());
        
        Modelo.Actualizar(vista.jtbDoctores); 
        Modelo.Mostrar(vista.jtbDoctores);
        limpiarCampos();
        }
        
        if (e.getSource() == vista.btnLimpiar) { 
        limpiarCampos(); 
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
