import java.io.IOException;

public class MainServidor {

	public static void main(String[] args) throws IOException {
		
		ConexServidor conser= new ConexServidor();
		conser.initServer();
		
//		AccesoDatos accesoDatos = new AccesoDatos();
//		Cuenta cuenta = accesoDatos.consultarCuentaPersonal("usu5");
//		int retiro = cuenta.retirar(100000);
//		if (retiro!=-1) {
//			System.out.println(cuenta.getSaldo());
//		}else {
//			System.out.println("No tienes el monto especificado para retirar");
//		}
//		accesoDatos.modificarCuenta(cuenta);
	}

}
