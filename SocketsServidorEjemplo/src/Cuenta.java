
public abstract class Cuenta {
	public abstract int retirar(int cantidadRetirar);
	public abstract int depositar(int cantidadDepositar);
	private String tipoCuenta;
	private String usuario;
	private String contrasenia;
	private String saldo;
	public Cuenta(String tipoCuenta, String usuario, String contrasenia, String saldo) {
		this.tipoCuenta = tipoCuenta;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.saldo = saldo;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	
	
}
