package co.edu.eafit.tvl.transformation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import be.ac.info.fundp.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.info.fundp.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.info.fundp.TVLParser.exceptions.SharedFeatureUsingParentSelectorException;
import be.ac.info.fundp.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.info.fundp.TVLParser.symbolTables.AttributeSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.FeaturesSymbolTable;
import be.ac.info.fundp.TVLParser.symbolTables.Symbol;

public class GNUPrologNamesContainer {
	
	private static GNUPrologNamesContainer instance;
	
	private Map<Integer, String> attributesGNUProlog = new TreeMap<Integer, String>();
	private Map<Integer, String> featuresGNUProlog = new TreeMap<Integer, String>();

	private FeaturesSymbolTable featuresSymbolTable;
	
	private GNUPrologNamesContainer(){
		
	}
	
	private GNUPrologNamesContainer(FeaturesSymbolTable featuresSymbolTable, List<FeatureSymbol> features){
		this.featuresSymbolTable = featuresSymbolTable;
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
	
	public static void init(){
		if ( instance == null ){
			instance = new GNUPrologNamesContainer();
		}
	}
	
	public static void init(FeaturesSymbolTable featuresSymbolTable, List<FeatureSymbol> features) {
		if ( instance == null ){
			instance = new GNUPrologNamesContainer(featuresSymbolTable, features);
		}
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
		if ( featuresSymbolTable == null ) return longID;
		try {
			if (featuresSymbolTable.containsSymbol(longID)){
				Symbol symbol = (Symbol) featuresSymbolTable.getSymbol(longID).get(0);
				if (symbol instanceof FeatureSymbol ){
					return getFeatureName(featuresSymbolTable.getFeatureSymbol(longID));
				}
				return getAttributeName(symbol) ;
			}
			return longID;
		} catch (AmbiguousReferenceException e) {
			throw new RuntimeException("Identifier " + longID + "is ambiguous");
		} catch (SymbolNotFoundException e) {
			throw new RuntimeException("Identifier " + longID + "cannot be found");
		} catch (ChildrenFeatureNotFoundException e) {
			throw new RuntimeException("Identifier " + longID + "cannot be found");
		} catch (SharedFeatureUsingParentSelectorException e) {
			throw new RuntimeException("Identifier " + longID + "cannot be found");
		}
	}
	
}
