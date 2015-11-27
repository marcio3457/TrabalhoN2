package br.com.projetodw.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.projetodw.model.Usuario;
import br.com.projetodw.repository.Pedidos;
import br.com.projetodw.security.UsuarioLogado;
import br.com.projetodw.security.UsuarioSistema;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private CartesianChartModel model;

	public void preRender() {
		this.model = new CartesianChartModel();
		
		adicionarSerie("Todos os pedidos", null);
		adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
	}
	
	private void adicionarSerie(String rotulo, Usuario criadoPor) {
		Map<Date, BigDecimal> valoresPorData = this.pedidos.valoresTotaisPorData(15, criadoPor);
		
		ChartSeries series = new ChartSeries(rotulo);
		
		for (Date data : valoresPorData.keySet()) {
			series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
		}
		
		this.model.addSeries(series);
	}

	public CartesianChartModel getModel() {
		return model;
	}
	
}
