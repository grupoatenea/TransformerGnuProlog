package co.edu.eafit.tvl.transformation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import be.ac.info.fundp.TVLParser.symbolTables.AttributeSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.EnumSetExpressionSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;

public class AttributesDomainValuesBuilder implements GNUPrologBuilder {

	private static final String FD_DOMAIN_FUNCTION = GNUPrologFunctionConstants.FD_DOMAIN_FUNCTION;
	
	private List<FeatureSymbol> features;
	
	public AttributesDomainValuesBuilder(List<FeatureSymbol> features){
		this.features = features;
	}
	
	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		for (FeatureSymbol feature : features) {
			String gnuPrologFeatureName = GNUPrologNamesContainer.getInstance().getFeatureName(feature);
			if ( feature.getAttributesSymbols() != null ) {
				Iterator<Map.Entry<String, AttributeSymbol>> it = feature.getAttributesSymbols().entrySet().iterator();
		        while (it.hasNext()) {
		        	AttributeSymbol attribute = it.next().getValue();
		        	String gnuPrologAttributeName = GNUPrologNamesContainer.getInstance().getAttributeName(attribute);
					if (attribute.hasASetExpressionSymbol()){
		        		if (attribute.getSetExpressionSymbol().isAnEnumSetExpressionSymbol()){
		        			EnumSetExpressionSymbol enumSetExpression = (EnumSetExpressionSymbol) attribute.getSetExpressionSymbol();
		        			appendCommentMappedAttributeDomainValues(sb, gnuPrologAttributeName, enumSetExpression);
		        			sb.append( FD_DOMAIN_FUNCTION ).append( "([").append( gnuPrologAttributeName ).append( "], 1, " + enumSetExpression.getContainedValues().size() ).append("),\n");
		        		}
		        	}
					sb.append( getAttributeConstraints(gnuPrologFeatureName, gnuPrologAttributeName) );
		        }
			}
		}
		sb.append("\n");
		return sb.toString();
	}

	private String getAttributeConstraints(String gnuPrologFeatureName, String gnuPrologAttributeName) {
		return gnuPrologFeatureName + " #> 0 #<=> " + gnuPrologAttributeName +  " #> 0,\n";
	}
	
	private void appendCommentMappedAttributeDomainValues(
			StringBuilder sb,
			String gnuPrologAttributeName,
			EnumSetExpressionSymbol enumSetExpression) {
		sb.append("% ").append( gnuPrologAttributeName ).append(" in [").append(enumSetExpression.getContainedValues().get(0).toString()).append(" = ").append( 1 );
		for (int j = 1; j < enumSetExpression.getContainedValues().size(); j++) {
			sb.append(", ").append(enumSetExpression.getContainedValues().get(j).toString()).append(" = ").append(j +1);
		} 
		sb.append("]\n");
	}


}
