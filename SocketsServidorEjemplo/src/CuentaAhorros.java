
public class CuentaAhorros extends Cuenta{
	
	public CuentaAhorros( String usuario, String contrasenia, String saldo) {
		super("ahorros", usuario, contrasenia, saldo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int retirar(int cantidadRetirar) {
		int sal = Integer.parseInt(super.getSaldo());
		if (cantidadRetirar<=sal) {
			super.setSaldo(""+(sal-cantidadRetirar));
			return sal-cantidadRetirar;
		}
		return -1;
	}
	
	@Override
	public int depositar(int cantidadDepositar) {
		int sal = Integer.parseInt(super.getSaldo());
		super.setSaldo(""+(sal+cantidadDepositar));
		return sal+cantidadDepositar;
	}
	
	//Metodo unico para las cuentas de tipo ahorro ya que 
	//solo en estas se genera un interes a favor del cliente
	public void generarInteres() {
		int sal = Integer.parseInt(super.getSaldo());
		super.setSaldo("" +(sal+(sal*0.02)));
	}

}
