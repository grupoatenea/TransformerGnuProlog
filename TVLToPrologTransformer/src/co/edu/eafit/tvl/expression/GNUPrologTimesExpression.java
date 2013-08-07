package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.TimesExpression;

public class GNUPrologTimesExpression implements GNUPrologExpression  {
	private TimesExpression expression;

	public GNUPrologTimesExpression(TimesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform(expression.getExpression1()).toArithmeticForm() + " * " + GNUPrologTransformer.transform(expression.getExpression2()).toArithmeticForm() ;
	}
}