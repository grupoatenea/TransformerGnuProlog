package co.edu.eafit.tvl.transformation;

import java.util.List;
import java.util.Vector;

import be.ac.info.fundp.TVLParser.symbolTables.ConstraintSymbol;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;
import co.edu.eafit.tvl.expression.GNUPrologExpressionTransformer;

public class ConstraintBuilder implements GNUPrologBuilder {

	private List<FeatureSymbol> features;

	public ConstraintBuilder(List<FeatureSymbol> features) {
		this.features = features;
	}

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		for (FeatureSymbol feature : features) {
			Vector<ConstraintSymbol> constraints = feature.getConstraintSymbols();
			if ( constraints != null ) {
				for (ConstraintSymbol constraintSymbol : constraints) {
					sb.append( GNUPrologExpressionTransformer.transform( constraintSymbol.getExpression() ).toArithmeticForm() ).append(",\n");
				}
			}
		}
		sb.append("\n");
		return sb.toString();
	}

}
