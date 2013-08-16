package co.edu.eafit.tvl.transformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import edu.unal.utils.FileUtils;
import be.ac.info.fundp.TVLParser.TVLParser;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;


public class TVLToGNUPrologTransformerImpl extends TVLToGNUPrologTransformer {

	private static final String S_VARIABLE = "S";
	private static final String APPEND_FUNCTION = GNUPrologFunctionConstants.APPEND_FUNCTION;
	private static final String FD_LABELING_FUNCTION = GNUPrologFunctionConstants.FD_LABELING_FUNCTION;
	private static final String FEATURES_VARIABLE_NAME = "Features";
	private static final String ATTRIBUTES_VARIABLE_NAME = "Attributes";
	private static final String HEADER = "productline(" + FEATURES_VARIABLE_NAME + ", " + ATTRIBUTES_VARIABLE_NAME + "):-";
	private static final String ATTRIBUTES_ASSIGNMENT_VARIABLE = ATTRIBUTES_VARIABLE_NAME + " = ";
	private static final String FEATURES_ASSIGNMENT_VARIABLE = FEATURES_VARIABLE_NAME + " = ";
	
	private List<FeatureSymbol> features;
	private String gnuPrologOutputFile;

	public TVLToGNUPrologTransformerImpl(String tvlInputFile, String gnuPrologOutputFile) throws FileNotFoundException {
		this.gnuPrologOutputFile = gnuPrologOutputFile;
		File fTVL = new File(tvlInputFile);
		TVLParser parser = new TVLParser(fTVL);
		parser.run();
		System.out.println( parser.getNormalForm() );
		if ( !parser.isValid() ) parser.printInfo();
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		GNUPrologNamesContainer.populate(features);
		this.features = features;
	}
	
	@Override
	public String getHeader() {
		StringBuilder sb = new StringBuilder(HEADER).append("\n\n");
		return sb.toString();
	}

	@Override
	public String getFeaturesList() {
		StringBuilder sb = new StringBuilder( FEATURES_ASSIGNMENT_VARIABLE);
		sb.append( GNUPrologUtils.getVariableList( GNUPrologNamesContainer.getInstance().getFeaturesGNUProlog() ) );
		sb.append(",\n\n");
		return sb.toString();
	}

	@Override
	public String getAttributesList() {
		StringBuilder sb = new StringBuilder(ATTRIBUTES_ASSIGNMENT_VARIABLE);
		sb.append( GNUPrologUtils.getVariableList( GNUPrologNamesContainer.getInstance().getAttributesGNUProlog() ) );
		sb.append(",\n\n");
		return sb.toString();
	}

	@Override
	public String getFeaturesDomainValues() {
		return new FeaturesDomainValuesBuilder().build();
	}
	
	@Override
	public String getAttributesDomainValues() {
		return new AttributesDomainValuesBuilder( features ).build();
	}
	
	@Override
	public String getConstraints() {
		return new ConstraintBuilder(features).build();
	}

	@Override
	public String getFooter() {
		StringBuilder sb = new StringBuilder();
		sb.append(APPEND_FUNCTION + "(" + FEATURES_VARIABLE_NAME + ", " + ATTRIBUTES_VARIABLE_NAME + ", " + S_VARIABLE + "),").append("\n");
		sb.append(FD_LABELING_FUNCTION + "(" + S_VARIABLE + ").");
		sb.append("\n");
		return sb.toString();
	}
	
	@Override
	public void save(String gnuFile) throws IOException {
		FileUtils.writePrologProgram(gnuPrologOutputFile, gnuFile);
//		FileOutputStream fos = new FileOutputStream(gnuPrologOutputFile);
//		fos.write(gnuFile.getBytes());
//		fos.flush();
//		fos.close();
	}

	@Override
	public String getMandatoryOptionalRelations() {
		return new MandatoryOptionalRelationshipsBuilder(features).build();
	}

}
