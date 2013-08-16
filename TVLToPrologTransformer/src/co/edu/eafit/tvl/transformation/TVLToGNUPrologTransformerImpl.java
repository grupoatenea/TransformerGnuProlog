package co.edu.eafit.tvl.transformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import be.ac.info.fundp.TVLParser.TVLParser;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;
import co.edu.eafit.tvl.exception.TransformationException;
import edu.unal.utils.FileUtils;


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
	
	private StringBuilder gnuPrologProgramBuffer = new StringBuilder();

	public TVLToGNUPrologTransformerImpl(String tvlInputFile, String gnuPrologOutputFile) throws FileNotFoundException, TransformationException {
		this.gnuPrologOutputFile = gnuPrologOutputFile;
		File fTVL = new File(tvlInputFile);
		TVLParser parser = parseTVLFile(fTVL);
		FeatureSymbol root = parser.getNormalizedRoot();
		TVLFeaturesTree tree = new TVLFeaturesTree(root);
		List<FeatureSymbol> features = tree.toList();
		GNUPrologNamesContainer.populate(features);
		this.features = features;
	}

	private TVLParser parseTVLFile(File fTVL) throws FileNotFoundException, TransformationException {
		TVLParser parser = new TVLParser(fTVL);
		parser.run();
		if ( !parser.isValid() ) { 
			if ( parser.getSyntaxError() != null){
				throw new TransformationException("Feature model invalid, syntax error:\n" + parser.getSyntaxError().getMessage(), parser.getSyntaxError());
			}
			if ( parser.getTypeError() != null){
				throw new TransformationException("Feature model invalid, type error:\n" + parser.getTypeError().getMessage(), parser.getTypeError());
			}
		}
		return parser;
	}
	
	@Override
	public void buildHeader() {
		StringBuilder sb = new StringBuilder(HEADER).append("\n\n");
		gnuPrologProgramBuffer.append( sb.toString() );
	}

	@Override
	public void buildFeaturesList() {
		StringBuilder sb = new StringBuilder( FEATURES_ASSIGNMENT_VARIABLE);
		sb.append( GNUPrologUtils.getVariableList( GNUPrologNamesContainer.getInstance().getFeaturesGNUProlog() ) );
		sb.append(",\n\n");
		gnuPrologProgramBuffer.append( sb.toString() );
	}

	@Override
	public void buildAttributesList() {
		StringBuilder sb = new StringBuilder(ATTRIBUTES_ASSIGNMENT_VARIABLE);
		sb.append( GNUPrologUtils.getVariableList( GNUPrologNamesContainer.getInstance().getAttributesGNUProlog() ) );
		sb.append(",\n\n");
		gnuPrologProgramBuffer.append( sb.toString() );
	}

	@Override
	public void buildFeaturesDomainValues() {
		gnuPrologProgramBuffer.append( new FeaturesDomainValuesBuilder().build() );
	}
	
	@Override
	public void buildAttributesDomainValues() {
		gnuPrologProgramBuffer.append( new AttributesDomainValuesBuilder( features ).build() );
	}
	
	@Override
	public void buildConstraints() {
		gnuPrologProgramBuffer.append( new ConstraintBuilder(features).build() );
	}
	
	@Override
	public void buildMandatoryOptionalRelations() {
		gnuPrologProgramBuffer.append( new MandatoryOptionalRelationshipsBuilder(features).build() );
	}

	@Override
	public void buildFooter() {
		StringBuilder sb = new StringBuilder();
		sb.append(APPEND_FUNCTION + "(" + FEATURES_VARIABLE_NAME + ", " + ATTRIBUTES_VARIABLE_NAME + ", " + S_VARIABLE + "),").append("\n");
		sb.append(FD_LABELING_FUNCTION + "(" + S_VARIABLE + ").");
		sb.append("\n");
		gnuPrologProgramBuffer.append( sb.toString() );
	}
	
	@Override
	public void save() throws IOException {
		FileUtils.writePrologProgram(gnuPrologOutputFile, gnuPrologProgramBuffer.toString());
	}

}
