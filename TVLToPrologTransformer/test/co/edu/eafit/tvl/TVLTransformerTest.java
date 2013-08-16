package co.edu.eafit.tvl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Ignore;
import org.junit.Test;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

import be.ac.info.fundp.TVLParser.TVLParser;
import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.Util.IDGenerator;
import be.ac.info.fundp.TVLParser.exceptions.UnsatisfiableModelException;
import be.ac.info.fundp.TVLParser.symbolTables.AttributeSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.ConstraintSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.EnumSetExpressionSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.Symbol;
import co.edu.eafit.tvl.expression.GNUPrologExpressionTransformer;
import co.edu.eafit.tvl.transformation.GNUPrologNamesContainer;
import co.edu.eafit.tvl.transformation.TVLFeaturesTree;


public class TVLTransformerTest {
	
	@Test
	public void testTVLParser() throws ContradictionException, UnsatisfiableModelException, IOException, TimeoutException {
		File fTVL = new File("/TVL/test1EnumBooleanForm.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		System.out.println(parser.getNormalForm());
		FeatureSymbol root = parser.getNormalizedRoot();
		assertEquals("Car", root.getID());
		assertTrue(root.isRoot());
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		GNUPrologNamesContainer.populate(features);
		for (FeatureSymbol featureSymbol : features) {
			System.out.println("Feature: " + featureSymbol.getID());
			if (featureSymbol.getAttributesSymbols() != null){
				Iterator<Map.Entry<String, AttributeSymbol>> it = featureSymbol.getAttributesSymbols().entrySet().iterator();
		        while (it.hasNext()) {
		        	AttributeSymbol attribute = it.next().getValue();
		        	System.out.println("Attribute: " + attribute.getID());
		        	System.out.println( GNUPrologNamesContainer.getInstance().getFeatureName(featureSymbol) + " #> 0 #<==> " + GNUPrologNamesContainer.getInstance().getAttributeName(attribute) +  " #> 0"); 
		        }
			}
			Vector<ConstraintSymbol> constraints = featureSymbol.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println("Constraint TVL: " + constraintSymbol.getExpression().toString());
					System.out.println("Constraint GNUProlog: " +  GNUPrologExpressionTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
	}
	
	@Test
	public void testTVLFeaturesTreeLongID() throws Exception {
		File fTVL = new File("/TVL/test5LongID.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
//		parser.printInfo();
		System.out.println(parser.getNormalForm());
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		GNUPrologNamesContainer.populate(features);
		System.out.println("--------------------------");
		
//		System.out.println(IDGenerator.getInstance().getSymbol(17).getID());
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getDIMACS_ID() + " " + feature.getID());
			Symbol symbol = IDGenerator.getInstance().getSymbol(feature.getDIMACS_ID());
			if (symbol instanceof FeatureSymbol){
				System.out.println(symbol.getDIMACS_ID() + " " + ((FeatureSymbol)symbol).getID());
			}
//			String id = feature.getFirstParentFeature() == null ? feature.getID() : feature.getFirstParentFeature().getID() + "." + feature.getID();
//			System.out.println("Feature: " + id);
			Iterator<Map.Entry<String, AttributeSymbol>> it = feature.getAttributesSymbols().entrySet().iterator();
	        while (it.hasNext()) {
	        	AttributeSymbol attribute = it.next().getValue();
	        	System.out.println(attribute.getDIMACS_ID());
	        	System.out.println("Attribute: " + attribute.getId());
				Symbol attributeSymbol = IDGenerator.getInstance().getSymbol(attribute.getDIMACS_ID());
				if (attributeSymbol instanceof AttributeSymbol){
					System.out.println(symbol.getDIMACS_ID() + " " + ((AttributeSymbol)attributeSymbol).getID());
				}
	        }
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println("Constraint TVL: " + constraintSymbol.getExpression().toString());
					System.out.println("Constraint GNUProlog: " +  GNUPrologExpressionTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
		System.out.println("--------------------------");
	}
	
	
	@Test
	@Ignore("Parsing error : Couldn't repair and continue parse in line 51, column 19")
	public void testTVLFeaturesTreeRexel_V4() throws FileNotFoundException {
		File fTVL = new File("/TVL/rexel_v4.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		parser.printInfo();
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		GNUPrologNamesContainer.populate(features);
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getID() + " " + feature.isOptionnal());
		}
	}
	
	
	@Test
	public void testTVLFeaturesTree() throws FileNotFoundException {
		File fTVL = new File("/TVL/test1EnumBooleanForm.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.run();
		parser.printInfo();
		System.out.println(parser.getNormalForm());
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		GNUPrologNamesContainer.populate(features);
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getID() + " " + feature.isOptionnal());
			if ( feature.getAttributesSymbols() != null ){
				Iterator<Map.Entry<String, AttributeSymbol>> it = feature.getAttributesSymbols().entrySet().iterator();
		        while (it.hasNext()) {
		        	AttributeSymbol attribute = it.next().getValue();
		        	System.out.println("Attribute: " + attribute.getId());
		        	if (attribute.hasASetExpressionSymbol()){
	//	        		attribute.getSetExpressionSymbol().isAnEnumSetExpressionSymbol();
		        		if (attribute.getSetExpressionSymbol().isAnEnumSetExpressionSymbol()){
		        			EnumSetExpressionSymbol enumSetExpression = (EnumSetExpressionSymbol) attribute.getSetExpressionSymbol();
		        			for (Expression expression : enumSetExpression.getContainedValues()) {
		        				System.out.println(expression.getClass().getName());
		        				System.out.println(expression.toString());
							}
		        			
		        		}
		        	}
		        	
		        }
			}
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println("Constraint TVL: " + constraintSymbol.getExpression().toString());
					System.out.println("Constraint GNUProlog: " +  GNUPrologExpressionTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
	}
	
	@Test
	public void testTVLFeaturesTreeBus() throws Exception {
		String tvl = "/TVL/testBus.tvl";
		System.out.println(tvl);
		File fTVL = new File(tvl);
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		int nbFeatures = parser.nbFeatures();
		System.out.println(nbFeatures);
		System.out.println(parser.getNormalForm());
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		GNUPrologNamesContainer.populate(features);
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getID() + " " + feature.isOptionnal());
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println( GNUPrologExpressionTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
	}
	
	@Test
	public void testTVLFeaturesTreeDeportivos() throws Exception {
		String tvl = "/TVL/testDeportivos.tvl";
		System.out.println(tvl);
		File fTVL = new File(tvl);
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		GNUPrologNamesContainer.populate(features);
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getID() + " " + feature.isOptionnal());
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println( GNUPrologExpressionTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
	}
	
}
