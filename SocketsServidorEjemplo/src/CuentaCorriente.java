
public class CuentaCorriente extends Cuenta {
	public CuentaCorriente(String usuario, String contrasenia, String saldo) {
		super("corriente", usuario, contrasenia, saldo);
		// TODO Auto-generated constructor stub
	}

	private int cheques;


	@Override
	public int retirar(int cantidadRetirar) {
		int sal = Integer.parseInt(super.getSaldo());
		if (cantidadRetirar <= sal || sal - cantidadRetirar >= -2000) {
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

	// En las cuentas corrientes se puede generar una chequera
	public void generarCheques() {
		this.cheques = 30;
	}

}
