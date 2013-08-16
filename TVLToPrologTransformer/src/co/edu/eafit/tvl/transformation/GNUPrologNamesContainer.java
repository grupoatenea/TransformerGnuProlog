package co.edu.eafit.tvl.transformation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import be.ac.info.fundp.TVLParser.symbolTables.AttributeSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.Symbol;

public class GNUPrologNamesContainer {
	
	private static GNUPrologNamesContainer instance;
	
	private Map<Integer, String> attributesGNUProlog = new TreeMap<Integer, String>();
	private Map<Integer, String> featuresGNUProlog = new TreeMap<Integer, String>();
	private AtomicInteger reifiedVariablesCounter = new AtomicInteger(1);

	private GNUPrologNamesContainer(List<FeatureSymbol> features){
		for (FeatureSymbol feature : features) {
			String gnuFeatureName = new GNUPrologFeatureTransformer(feature).getGNUPrologName();
			featuresGNUProlog.put(feature.getDIMACS_ID(), gnuFeatureName);
			if ( feature.getAttributesSymbols() != null ){
				Iterator<Map.Entry<String, AttributeSymbol>> itAttributes = feature.getAttributesSymbols().entrySet().iterator();
		        while (itAttributes.hasNext()) {
		        	AttributeSymbol attribute = itAttributes.next().getValue();
		        	GNUPrologVariableTransformer attributeTransformer = new GNUPrologAttributeTransformer(gnuFeatureName, attribute);
		        	attributesGNUProlog.put(attribute.getDIMACS_ID(), attributeTransformer.getGNUPrologName());
		        }
			}
		}
	}
	
	public GNUPrologNamesContainer() {
	}

	public static void populate(){
		instance = new GNUPrologNamesContainer();
	}
	
	public static void populate(List<FeatureSymbol> features) {
		instance = new GNUPrologNamesContainer(features);
	}
	
	public static GNUPrologNamesContainer getInstance() {
		if ( instance == null ){
			throw new IllegalStateException("Container must be initialized");
		}
		return instance;
	}
	
	public String getFeatureName(Symbol symbol){
		return featuresGNUProlog.get(symbol.getDIMACS_ID());
	}
	
	public String getAttributeName(Symbol symbol){
		return attributesGNUProlog.get(symbol.getDIMACS_ID());
	}

	public String getName(String longID) {
		String longIDGNUPrologFormat = longID.replaceAll("\\.", "_");
		Iterator<Map.Entry<Integer, String>> itFeatures = featuresGNUProlog.entrySet().iterator();
		while (itFeatures.hasNext()) {
			String gnuPrologName = itFeatures.next().getValue();
			if ( gnuPrologName.contains( longIDGNUPrologFormat ) ) {
				return gnuPrologName;
			}
		}
		throw new RuntimeException("Identifier " + longID + " not found");
	}

	public Map<Integer, String> getAttributesGNUProlog() {
		return attributesGNUProlog;
	}

	public Map<Integer, String> getFeaturesGNUProlog() {
		return featuresGNUProlog;
	}
	
	public String getNextReifiedVariable(){
		return "R" + reifiedVariablesCounter.getAndIncrement();
	}
	
}
