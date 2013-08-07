package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.IfAndOnlyIfExpression;

public class GNUPrologIfAndOnlyIfExpression implements GNUPrologExpression {

	private IfAndOnlyIfExpression expression;

	public GNUPrologIfAndOnlyIfExpression(IfAndOnlyIfExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform(expression.getExpression1()).toArithmeticForm() + " #<=> " + GNUPrologExpressionTransformer.transform(expression.getExpression2()).toArithmeticForm() ;
	}

}
