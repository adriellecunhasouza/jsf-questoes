package beans.livro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;


@Named
@SessionScoped

public class LivroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();
	private String nome;
	private String senha;
	private List<Livro> listLivros = new ArrayList<Livro>();

	public String adicionarLivro() {
	String titulo = livro.getTitulo();
	String isbn = livro.getIsbn();
		if (checkCampos(titulo, isbn)) {
			FacesMessage fm = new FacesMessage("Erro! Verifique seu acesso.");
		    FacesContext.getCurrentInstance().addMessage("msg", fm);			
			return "questao2.xhtml";
		}
		FacesContext context = FacesContext.getCurrentInstance();
		if (titulo.equals(null)) 
			context.addMessage(null, new FacesMessage("Falha na Autenticação. Veja seu 'username' ou senha."));
			
		listLivros.add(livro);
		String msg = "Livro adicionado: " + livro.getTitulo();	
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage("msg", fm);
		livro = new Livro();
		return "questao2";
	}

	public String remover(Livro livro) {
		listLivros.remove(livro);
		return "questao2";
	}
	
	public String editar(Livro livro) {		
		livro.setEditing(true);
		 return "questao2.xhtml";	 
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getListLivros() {
		return listLivros;
	}

	public String login() {
		if (checkLogin(nome, senha)) {
			FacesContext.getCurrentInstance().getExternalContext()
			       .getSessionMap().put("USUARIO", nome.toUpperCase());
			return "questao2.xhtml";
		}
	    FacesMessage fm = new FacesMessage("Erro! Verifique seu acesso.");
	    FacesContext.getCurrentInstance().addMessage("msg", fm);
		return "login";
	}
	
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		return "login.xhtml";
	}
	
	
	public String getNome() { return nome; 	}
	public void setNome(String nome) { this.nome = nome; }
	public String getSenha() { return senha; 	}
	public void setSenha(String senha) { this.senha = senha; }


	private boolean checkLogin(String nome2, String senha2) {
		if (nome2.equalsIgnoreCase("admin") && senha2.equalsIgnoreCase("admin") )
			return true;		
		return false;
	}
	
	private boolean checkCampos(String titulo2, String isbn2) {
		if (titulo2==null && isbn2==null)
			return true;		
		return false;
	}
	
	
	public void onRowEdit(RowEditEvent<Livro> event) {
		livro = ((Livro) event.getObject());
        FacesMessage msg = new FacesMessage("Car Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent<Livro> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent<Livro> event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }	
}




