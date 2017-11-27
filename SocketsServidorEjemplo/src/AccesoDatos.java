import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class AccesoDatos {

	public void readFile() throws IOException {

		String filePath = "readfile.txt";

	}

	public boolean crearCuenta(String input) throws IOException {

		try {
			input = "\n" + input;
			byte[] inputBytes = input.getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(inputBytes);

			String filePath = "cuentas.txt";
			FileOutputStream fos = new FileOutputStream(filePath, true);
			FileChannel fileChannel = fos.getChannel();
			fileChannel.write(buffer);
			fileChannel.close();
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<Cuenta> consutarCuentas() throws IOException {

		String path = "cuentas.txt";
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String textRead = br.readLine();
		System.out.println("File contents: ");
		Cuenta cuen;
		ArrayList<Cuenta> registros = new ArrayList<Cuenta>();
		while (textRead != null) {
			String[] registro = textRead.split(" ");
			if (registro[2].equals("ahorro")) {
				cuen = new CuentaAhorros(registro[0], registro[1], registro[3]);
			} else {
				cuen = new CuentaCorriente(registro[0], registro[1], registro[3]);
			}

			registros.add(cuen);
			textRead = br.readLine();
		}
		fr.close();
		br.close();
		return registros;
	}

	public Cuenta consultarCuentaPersonal(String usuario) throws IOException {

		String path = "cuentas.txt";
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String textRead = br.readLine();
		System.out.println("File contents: ");
		ArrayList<Cuenta> registros = new ArrayList<Cuenta>();
		while (textRead != null) {
			String[] registro = textRead.split(" ");
			Cuenta cuen;
			if (registro[2].equals("ahorros")) {
				cuen = new CuentaAhorros(registro[0], registro[1], registro[3]);
			} else {
				cuen = new CuentaCorriente(registro[0], registro[1], registro[3]);
			}
			registros.add(cuen);
			textRead = br.readLine();
		}
		for (int i = 0; i < registros.size(); i++) {
			if (registros.get(i).getUsuario().equals(usuario)) {
				return registros.get(i);
			}
		}
		fr.close();
		br.close();
		return null;
	}

	public void modificarCuenta(Cuenta cuenta) {
		try {
			String path = "cuentas.txt";
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String textRead = br.readLine();
			System.out.println("File contents: ");
			Cuenta cuen;
			ArrayList<Cuenta> registros = new ArrayList<Cuenta>();
			while (textRead != null) {
				String[] registro = textRead.split(" ");
				if (registro[0].equals(cuenta.getUsuario())) {
					cuen = cuenta;
				}else {
					if (registro[2].equals("ahorro")) {
						cuen = new CuentaAhorros(registro[0], registro[1], registro[3]);
					} else {
						cuen = new CuentaCorriente(registro[0], registro[1], registro[3]);
					}
				}
				registros.add(cuen);
				textRead = br.readLine();
			}
			for (int i = 0; i < registros.size(); i++) {
				String input= registros.get(i).getUsuario();
				input= input+" "+registros.get(i).getContrasenia();
				input= input+" "+registros.get(i).getTipoCuenta();
				input= input+" "+registros.get(i).getSaldo();
				if (i==0) {
					byte[] inputBytes = input.getBytes();
					ByteBuffer buffer = ByteBuffer.wrap(inputBytes);

					String filePath = "cuentas.txt";
					FileOutputStream fos = new FileOutputStream(filePath, false);
					FileChannel fileChannel = fos.getChannel();
					fileChannel.write(buffer);
					fileChannel.close();
				}else {
					crearCuenta(input);
				}
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Error: "+e.toString());
		}
	}

}