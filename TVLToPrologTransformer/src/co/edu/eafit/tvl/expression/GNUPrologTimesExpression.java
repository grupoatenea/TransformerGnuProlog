package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.TimesExpression;

public class GNUPrologTimesExpression implements GNUPrologExpression  {
	private TimesExpression expression;

	public GNUPrologTimesExpression(TimesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform(expression.getExpression1()).toArithmeticForm() + " * " + GNUPrologExpressionTransformer.transform(expression.getExpression2()).toArithmeticForm() ;
	}
}
