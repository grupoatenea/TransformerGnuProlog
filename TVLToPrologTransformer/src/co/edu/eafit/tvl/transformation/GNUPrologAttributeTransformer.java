package co.edu.eafit.tvl.transformation;

import be.ac.info.fundp.TVLParser.symbolTables.AttributeSymbol;

public class GNUPrologAttributeTransformer extends GNUPrologVariableTransformer {

	private String gnuFeatureName;
	private AttributeSymbol attribute;

	public GNUPrologAttributeTransformer(String gnuFeatureName, AttributeSymbol attribute) {
		this.attribute = attribute;
		this.gnuFeatureName = gnuFeatureName;
	}
	
	public String getGNUPrologName() {
		return gnuFeatureName.concat("_").concat(attribute.getId());
	}

}
