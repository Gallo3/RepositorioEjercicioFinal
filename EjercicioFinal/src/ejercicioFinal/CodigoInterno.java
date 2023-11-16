package ejercicioFinal;

public enum CodigoInterno {
	LACTEO(1), REGALERIA(2);

private int Numerador;	

	CodigoInterno(int i) {
		this.Numerador = i;
	}

	public int getNumerador() {
		return Numerador;
	}

}
