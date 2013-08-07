package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;
import co.edu.eafit.tvl.transformation.GNUPrologNamesContainer;

public class GNUPrologLongIDExpression implements GNUPrologExpression {

	private LongIDExpression expression;

	public GNUPrologLongIDExpression(LongIDExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		if (expression.getSymbol() == null){
			return expression.getLongID();
		}
		if (expression.getSymbol() instanceof FeatureSymbol){
			return GNUPrologNamesContainer.getInstance().getFeatureName(expression.getSymbol());
		} else {
			return GNUPrologNamesContainer.getInstance().getAttributeName(expression.getSymbol());
		}
	}

}
