import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class servidor {
    public static void main(String[] args) {
        try {
            interfaz objetoRemoto = new implementacionInterfaz();

            Registry registro = LocateRegistry.createRegistry(1099);

            // Registrar la implementación en el registro RMI
            registro.rebind("ClienteRemoto", objetoRemoto);

            System.out.println("Servidor de Calculadora listo.");
        } catch (Exception e) {
            System.err.println("Error en el servidor de Calculadora: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
