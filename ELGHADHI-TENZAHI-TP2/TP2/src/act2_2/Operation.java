package act2_2;

import java.io.Serializable;

// Implémentation de Serialisable pour pouvoir être converti en fichier binaire et envoyé
public class Operation implements Serializable{
	int op1, op2, resultat;
	char operation;
	
	public Operation(int op1, int op2, char operation) {
		this.op1 = op1;
		this.op2 = op2;
		this.operation = operation;
	}
	
	public void setResultat(int resultat) {
		this.resultat = resultat;
	}

}
