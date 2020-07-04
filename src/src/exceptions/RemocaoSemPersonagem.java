package src.exceptions;

public class RemocaoSemPersonagem extends RemocaoInvalida {
	private static final long serialVersionUID = 1117127702841953702L;

	public RemocaoSemPersonagem() {
	      super();
	   }

	   public RemocaoSemPersonagem(String message) {
	      super(message);
	   }
	}
