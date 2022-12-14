package org.pizzahat.com.demo.pojo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Promozione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	@NotNull(message = "Deve esistere un titolo")
	@NotBlank(message = "Il titolo non puo' essere vuoto")
	private String titolo;
	
	@Column
	@NotNull(message = "Deve esistere una data inizio")
	private LocalDate dataInizio;
	
	@Column
	@NotNull(message = "Deve esistere una data fine")
	private LocalDate dataFine;
	
	@OneToMany(mappedBy = "promozione")
	private List<Pizza> pizze;
	
	public Promozione() { }
	public Promozione(String titolo, LocalDate dataInizio, LocalDate dataFine) {
		
		setTitolo(titolo);
		setDataInizio(dataInizio);
		setDataFine(dataFine);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	public List<Pizza> getPizze() {
		return pizze;
	}
	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

	@Override
	public String toString() {
		
		return "(" + getId() + ") " + getTitolo() + " - " + getDataInizio() + "~" + getDataFine();
	}
}
