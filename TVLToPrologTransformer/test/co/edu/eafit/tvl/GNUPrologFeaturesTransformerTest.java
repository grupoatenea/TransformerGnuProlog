package co.edu.eafit.tvl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import be.ac.info.fundp.TVLParser.TVLParser;
import be.ac.info.fundp.TVLParser.symbolTables.AttributeSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;
import co.edu.eafit.tvl.transformation.GNUPrologAttributeTransformer;
import co.edu.eafit.tvl.transformation.GNUPrologFeatureTransformer;
import co.edu.eafit.tvl.transformation.GNUPrologVariableTransformer;
import co.edu.eafit.tvl.transformation.TVLFeaturesTree;

public class GNUPrologFeaturesTransformerTest {

	
	
	@Test
	public void testFeaturesNames() throws FileNotFoundException {
		File fTVL = new File("/TVL/test5LongID.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		
		Map<Integer, String> featuresGNUProlog = new TreeMap<Integer, String>();
		for (FeatureSymbol feature : features) {
			GNUPrologVariableTransformer featureTransformer = new GNUPrologFeatureTransformer(feature);
			featuresGNUProlog.put(feature.getDIMACS_ID(), featureTransformer.getGNUPrologName());
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<Integer, String>> it = featuresGNUProlog.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, String> entry = it.next();
			sb.append(entry.getValue()).append("\n");
		}
		
		assertEquals(
				"Vehicle\n" +
				"Vehicle_Car\n" +
				"Vehicle_Car_Family\n" +
				"Vehicle_Car_Family_Ford\n" +
				"Vehicle_Car_Family_Citroen\n" +
				"Vehicle_Car_Sports\n" +
				"Vehicle_Car_Sports_Porsche\n" +
				"Vehicle_Car_Sports_Ferrari\n" +
				"Vehicle_Truck\n" +
				"Vehicle_Truck_National\n" +
				"Vehicle_Truck_National_Ford\n" +
				"Vehicle_Truck_National_Renault\n" +
				"Vehicle_Truck_International\n" +
				"Vehicle_Truck_International_Volvo\n" +
				"Vehicle_Truck_International_Mercedes\n" +
				"Vehicle_Bus\n" +
				"Vehicle_Bus_Volkswagen\n" +
				"Vehicle_Bus_Mercedes\n", sb.toString());
			
	}
	
	@Test
	public void testAttributesNames() throws FileNotFoundException{
		File fTVL = new File("/TVL/test5LongID.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		
		Map<Integer, String> attributesGNUProlog = new TreeMap<Integer, String>();
		for (FeatureSymbol feature : features) {
			String gnuFeatureName = new GNUPrologFeatureTransformer(feature).getGNUPrologName();
			Iterator<Map.Entry<String, AttributeSymbol>> itAttributes = feature.getAttributesSymbols().entrySet().iterator();
	        while (itAttributes.hasNext()) {
	        	AttributeSymbol attribute = itAttributes.next().getValue();
	        	GNUPrologVariableTransformer attributeTransformer = new GNUPrologAttributeTransformer(gnuFeatureName, attribute);
	        	attributesGNUProlog.put(attribute.getDIMACS_ID(), attributeTransformer.getGNUPrologName());
	        }
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<Integer, String>> it = attributesGNUProlog.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, String> entry = it.next();
			sb.append(entry.getValue()).append("\n");
		}
		
		assertEquals(
				"Vehicle_weight\n" +
				"Vehicle_price\n" +
				"Vehicle_Car_weight\n" +
				"Vehicle_Car_price\n" +
				"Vehicle_Car_Family_weight\n" +
				"Vehicle_Car_Family_price\n" +
				"Vehicle_Car_Family_Ford_weight\n" +
				"Vehicle_Car_Family_Ford_price\n" +
				"Vehicle_Car_Family_Citroen_weight\n" +
				"Vehicle_Car_Family_Citroen_price\n" +
				"Vehicle_Car_Sports_weight\n" +
				"Vehicle_Car_Sports_price\n" +
				"Vehicle_Car_Sports_Porsche_weight\n" +
				"Vehicle_Car_Sports_Porsche_price\n" +
				"Vehicle_Car_Sports_Ferrari_weight\n" +
				"Vehicle_Car_Sports_Ferrari_price\n" +
				"Vehicle_Truck_weight\n" +
				"Vehicle_Truck_price\n" +
				"Vehicle_Truck_National_weight\n" +
				"Vehicle_Truck_National_price\n" +
				"Vehicle_Truck_National_Ford_weight\n" +
				"Vehicle_Truck_National_Ford_price\n" +
				"Vehicle_Truck_National_Renault_weight\n" +
				"Vehicle_Truck_National_Renault_price\n" +
				"Vehicle_Truck_International_weight\n" +
				"Vehicle_Truck_International_price\n" +
				"Vehicle_Truck_International_Volvo_weight\n" +
				"Vehicle_Truck_International_Volvo_price\n" +
				"Vehicle_Truck_International_Mercedes_weight\n" +
				"Vehicle_Truck_International_Mercedes_price\n" +
				"Vehicle_Bus_weight\n" +
				"Vehicle_Bus_price\n" +
				"Vehicle_Bus_Volkswagen_weight\n" +
				"Vehicle_Bus_Volkswagen_price\n" +
				"Vehicle_Bus_Mercedes_weight\n" +
				"Vehicle_Bus_Mercedes_price\n", sb.toString());
			
	}

}
