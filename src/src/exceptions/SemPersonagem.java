package src.exceptions;

public class SemPersonagem extends Exception {
	private static final long serialVersionUID = -4810157270401623009L;

	public SemPersonagem() {
        super();
    }
    
    public SemPersonagem(String message) {
        super(message);
    }
}
