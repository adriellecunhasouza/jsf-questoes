package beans.livro;

import java.math.BigDecimal;
//import java.beans.Transient;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
//import javax.enterprise.inject.TransientReference;
import javax.inject.Named;

@Named ("login")
@RequestScoped
public class Livro {
		private String titulo;
		private String isbn;
		private BigDecimal preco;
		private Date dataLancamento;
		
		private transient boolean editing;
		
		
		public Livro() {
			
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public BigDecimal getPreco() {
			return preco;
		}

		public void setPreco(BigDecimal preco) {
			this.preco = preco;
		}
		
		
		public Date getDataLancamento() {
			return dataLancamento;
		}

		public void setDataLancamento(Date dataLancamento) {
			this.dataLancamento = dataLancamento;
		}
		
		public boolean getEditing() { return editing; 	}
		public void setEditing(boolean editing) { this.editing = editing; }
}