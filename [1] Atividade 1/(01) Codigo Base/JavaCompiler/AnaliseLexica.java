import java.io.*;

enum TokenType{ NUM,SOMA, MULT,APar,FPar, EOF}

class Token{
  String lexema;								//char -> String => Armazenar 2+ caracteres
  TokenType token;

 Token (String l, TokenType t)					//Construtor, char -> String
 	{ lexema=l;token = t;}	

}

class AnaliseLexica {

	BufferedReader arquivo;

	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}

	Token getNextToken() throws Exception
	{	
		Token token;
		int eof = -1;
		char currchar;
		int currchar1;

			do{
				currchar1 =  arquivo.read();
				currchar = (char) currchar1;
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			
			if(currchar1 != eof && currchar1 !=10)
			{
								
	
				if (currchar >= '0' && currchar <= '9')
				{
					// Construir número com múltiplos dígitos
					StringBuilder numero = new StringBuilder();
					numero.append(currchar);
					
					// Continuar lendo enquanto forem dígitos
					while(true) {
						// Marcar a posição atual do arquivo para poder voltar
						arquivo.mark(1);
						currchar1 = arquivo.read();
						
						if(currchar1 == -1) // EOF
							break;
							
						currchar = (char) currchar1;
						
						if(currchar >= '0' && currchar <= '9') {
							numero.append(currchar);
						} else {
							// Não é dígito, voltar um caractere
							arquivo.reset();
							break;
						}
					}
					
					return (new Token (numero.toString(), TokenType.NUM));
				}
				else
					switch (currchar){
						case '(':
							return (new Token (String.valueOf(currchar),TokenType.APar)); //char.valueOf => String.valueOf
						case ')':
							return (new Token (String.valueOf(currchar),TokenType.FPar));
						case '+':
							return (new Token (String.valueOf(currchar),TokenType.SOMA));
						case '*':
							return (new Token (String.valueOf(currchar),TokenType.MULT));
						
						default: throw (new Exception("Caractere inválido: " + ((int) currchar)));
					}
			}

			arquivo.close();
			
		return (new Token(String.valueOf(currchar),TokenType.EOF));
		
	}
}
