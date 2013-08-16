package co.edu.eafit.tvl.transformation;


public class FeaturesDomainValuesBuilder implements GNUPrologBuilder {

	private static final String FD_DOMAIN_FUNCTION = GNUPrologFunctionConstants.FD_DOMAIN_FUNCTION;
	
	@Override
	public String build() {
		final String featuresDomainValues = "0, 1";
		StringBuilder sb = new StringBuilder();
		sb.append(FD_DOMAIN_FUNCTION + "(" + GNUPrologUtils.getVariableList( GNUPrologNamesContainer.getInstance().getFeaturesGNUProlog() ) + ", " + featuresDomainValues + "),");
		sb.append("\n\n");
		return sb.toString();
	}


}
