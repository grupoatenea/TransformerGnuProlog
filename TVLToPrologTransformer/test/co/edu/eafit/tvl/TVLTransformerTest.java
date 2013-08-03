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
import be.ac.info.fundp.TVLParser.exceptions.UnsatisfiableModelException;
import be.ac.info.fundp.TVLParser.symbolTables.AttributeSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.ConstraintSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;
import co.edu.eafit.tvl.expression.GNUPrologTransformer;


public class TVLTransformerTest {
	
	@Test
	public void testTVLParser() throws ContradictionException, UnsatisfiableModelException, IOException, TimeoutException {
		File fTVL = new File("F:/EAFIT Especialización/Aproximaciones Avanzadas/test1EnumBooleanForm.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		FeatureSymbol root = parser.getNormalizedRoot();
		assertEquals("Car", root.getID());
		assertTrue(root.isRoot());
		System.out.println("Feature: " + root.getID());
		Iterator<Map.Entry<String, AttributeSymbol>> it = root.getAttributesSymbols().entrySet().iterator();
        while (it.hasNext()) {
        	System.out.println("Attribute: " + it.next().getValue().getID());
        }
		Vector<ConstraintSymbol> contraints = root.getConstraintSymbols();
		for (ConstraintSymbol constraintSymbol : contraints) {
			System.out.println("Constraint TVL: " + constraintSymbol.getExpression().toString());
			System.out.println("Constraint GNUProlog: " +  GNUPrologTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
		}
	}
	
	@Test
	public void testTVLFeaturesTreeLongID() throws Exception {
		File fTVL = new File("F:/EAFIT Especialización/Aproximaciones Avanzadas/test5LongID.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		parser.printInfo();
		System.out.println( parser.getNormalForm() );
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		System.out.println("--------------------------");
		for (FeatureSymbol feature : features) {
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println("Constraint TVL: " + constraintSymbol.getExpression().toString());
					System.out.println("Constraint GNUProlog: " +  GNUPrologTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
		System.out.println("--------------------------");
	}
	
	
	@Test
	@Ignore("Parsing error : Couldn't repair and continue parse in line 51, column 19")
	public void testTVLFeaturesTreeRexel_V4() throws FileNotFoundException {
		File fTVL = new File("F:/EAFIT Especialización/Aproximaciones Avanzadas/rexel_v4.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		parser.printInfo();
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getID() + " " + feature.isOptionnal());
		}
	}
	
	
	@Test
	public void testTVLFeaturesTree() throws FileNotFoundException {
		File fTVL = new File("F:/EAFIT Especialización/Aproximaciones Avanzadas/test1EnumBooleanForm.tvl");
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getID() + " " + feature.isOptionnal());
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println( GNUPrologTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
	}
	
	@Test
	public void testTVLFeaturesTreeBus() throws Exception {
		String tvl = "F:/EAFIT Especialización/Aproximaciones Avanzadas/testBus.tv";
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
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getID() + " " + feature.isOptionnal());
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println( GNUPrologTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
	}
	
	@Test
	public void testTVLFeaturesTreeDeportivos() throws Exception {
		String tvl = "F:/EAFIT Especialización/Aproximaciones Avanzadas/testDeportivos.tvl";
		System.out.println(tvl);
		File fTVL = new File(tvl);
		TVLParser parser = new TVLParser(fTVL);
		parser.runNormalizedFormParser();
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		for (FeatureSymbol feature : features) {
			System.out.println(feature.getID() + " " + feature.isOptionnal());
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					System.out.println( GNUPrologTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() );
				}
			}
		}
	}
	
}
