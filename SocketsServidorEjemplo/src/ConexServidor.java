import java.net.*;

import java.io.*;

/**
 *
 * 
 * 
 * @author Jorge V
 * 
 */

public class ConexServidor {

	final int puerto = 2018;

	ServerSocket server;

	Socket socket;

	PrintStream salida;

	BufferedReader usuario,contrasenia,contrasenian,tipoCuenta,saldo,eleccion;

	// SERVIDOR
	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * 
	 * }
	 */

	public void initServer() throws IOException

	{

		try

		{

			server = new ServerSocket(puerto);
			socket = new Socket();

			System.out.println("Esperando por conexiones....");
			int band = 1;
			while (band != 0) {
				socket = server.accept();
				System.out.println("Conexión establecida");
				salida = new PrintStream(socket.getOutputStream());
				
				String usu,pass,tipoCu,sal;
				int auxValor;
				AccesoDatos ac = new AccesoDatos();
				Cuenta cli;
//				DataInputStream dIn = new DataInputStream(socket.getInputStream());
				
				//salida.println("Bienvenido");
				//System.out.println("Bienvenido.");
				eleccion = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				int el= Integer.parseInt(eleccion.readLine());
				switch (el) {
				//Crear una cuenta nueva
				case 1:
					//Datos para guardar del nuevo usuario
					usuario = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					usu = usuario.readLine();
					contrasenia = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					pass = contrasenia.readLine();
					tipoCuenta = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					tipoCu = tipoCuenta.readLine();
					saldo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					sal = saldo.readLine();
					if(ac.crearCuenta(usu+" "+pass+" "+tipoCu+" "+sal)) {
						salida.println("Usuario registrado");
					}else {
						salida.println("no");
					}
					break;
				//Consultar un usuario
				case 2:
					usuario = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String[] arregloEntrada1 = usuario.readLine().split(" ");
					cli =ac.consultarCuentaPersonal(arregloEntrada1[0]);
					if (cli!=null) {
						salida.println(cli.getUsuario()+" "+cli.getSaldo()+" "+cli.getTipoCuenta());
					}else {
						salida.println("no");
					}
				//Iniciar sesión
				case 3:
					usuario = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					usu = usuario.readLine();
					contrasenia = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					pass = contrasenia.readLine();
					cli =ac.consultarCuentaPersonal(usu);
					if (cli!=null && cli.getContrasenia().equals(pass)) {
						salida.println(cli.getUsuario()+" "+cli.getSaldo()+" "+cli.getTipoCuenta());
					}else {
						salida.println("no");
					}
					break;
				//Realizar un retiro
				case 4:
					saldo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String entrada = saldo.readLine();
					String[] arregloEntrada = entrada.split(" ");
					auxValor = Integer.parseInt(arregloEntrada[1]);
					cli =ac.consultarCuentaPersonal(arregloEntrada[0]);
					int retiro = cli.retirar(auxValor);
					if (retiro!=-1) {
						ac.modificarCuenta(cli);
						salida.println(cli.getSaldo());
					}else {
						salida.println("no");
					}
					break;
				//Realizar una consignación
				case 5:
					usuario = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					usu = usuario.readLine();
					saldo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					auxValor = Integer.parseInt(saldo.readLine());
					cli =ac.consultarCuentaPersonal(usu);
					if (cli!=null) {
						cli.depositar(auxValor);
						ac.modificarCuenta(cli);
						salida.println("Valor depositado");
					}else {
						salida.println("no");
					}
					break;
				// Cambiar contraseña
				case 6:
					saldo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String[] arregloEntrada2 = saldo.readLine().split(" ");
					cli =ac.consultarCuentaPersonal(arregloEntrada2[0]);
					cli.setContrasenia(arregloEntrada2[1]);
					ac.modificarCuenta(cli);
					salida.println("Contraseña actualizada");
					break;
				default:
					
					break;
				}
				
			}

			socket.close();
		} catch (Exception e) {

			System.out.println("Errorser: " + e.getMessage());
			System.out.println(e);
			server.close();
		}

	}

}