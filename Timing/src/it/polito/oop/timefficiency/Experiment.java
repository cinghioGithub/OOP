package it.polito.oop.timefficiency;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Experiment<D> {
	
	public static final int NUM_RIPETIZIONI_DEFAULT = 30;
	
	private Map<String,Consumer<D>> algoritmi = new HashMap<>();   //devo parametrizzare il consumer <D>
	private Supplier<D> generator;
	private int ripetizioni = NUM_RIPETIZIONI_DEFAULT;
	private Map<String,List<Double>> risultati;
	private int chNome;
	private int chPlot;

	public void addAlgorithm(String name, Consumer<D> algorithm) {
		this.algoritmi.put(name, algorithm);
	}
	
	public void setDataGenerator(Supplier<D> generator) {
		this.generator = generator;
	}

	public double perform(String name) {
		Consumer<D> algorithm = this.algoritmi.get(name);
		D dati = this.generator.get();
		long begin = System.nanoTime();
		algorithm.accept(dati);
		long end = System.nanoTime();
		return (end - begin)/1.0e6;
	}
	
	public int run() {
		D dati = this.generator.get();
		risultati = new HashMap<>();
		
		for(String str : this.algoritmi.keySet()) {
			risultati.put(str, new ArrayList<>(ripetizioni));
		}
		
		//IntStream.range(0,ripetizioni);    //stream di interi da 0 a ripetizioni
		
		for(int i=0; i<this.ripetizioni; ++i) {
			
			//algoritmi.entrySet().stream();    //creo uno stream per tutti gli elementi della mappa
			
			for(String str : this.algoritmi.keySet()) {
				Consumer<D> algoritmo = this.algoritmi.get(str);
				long begin = System.nanoTime();
				algoritmo.accept(dati);
				long end = System.nanoTime();
				risultati.get(str).add((end - begin)/1.0e6);
			}
		}
		return this.ripetizioni;
	}
	
	public void setRepeat(int n) {
		// TODO: unimplemented method
	}

	public int getRepeat() {
		// TODO: unimplemented method
		return -1;
	}
	
	public Map<String,List<Double>> getMeasures(){
		return this.risultati;
	}
	
	public void setPlotFormat(int nameWidth, int plotWidth) {
		// TODO: unimplemented method
	}
	
	public String plotInterval() {
		
		Map<String,DoubleSummaryStatistics> stat = new HashMap<>();
		for(String n : risultati.keySet()) {
			stat.put(n, risultati.get(n).stream().mapToDouble(x->x).summaryStatistics());
		}
		
		final DoubleSummaryStatistics globalStat = risultati.values().stream().     //così avrei uno stream di liste
		flatMap(List::stream).			//così prendo tutte la liste e ne creo uno stream così da collegarli tutti per ottenere un unico stream con tutti i valori contenuti dentro le liste
		mapToDouble(x->x).
		summaryStatistics();
		
		double globalMin = stat.values().stream().mapToDouble(dss -> dss.getMin()).min().getAsDouble();
		
		Function<Double,Integer> m = x -> (int) ((x-globalStat.getMin())/(globalStat.getMax()-globalStat.getMin()) * );
		
		return null;
	}
	
	private int mappaSuCaratteri(double x, double min, double max) {
		
		return (int) ((x-min)/(max-min) * this.chPlot);
		
	}
}
