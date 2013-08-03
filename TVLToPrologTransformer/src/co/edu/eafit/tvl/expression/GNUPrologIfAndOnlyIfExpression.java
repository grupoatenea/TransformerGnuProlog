package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.IfAndOnlyIfExpression;

public class GNUPrologIfAndOnlyIfExpression implements GNUPrologExpression {

	private IfAndOnlyIfExpression expression;

	public GNUPrologIfAndOnlyIfExpression(IfAndOnlyIfExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform(expression.getExpression1()).toArithmeticForm() + " #<=> " + GNUPrologTransformer.transform(expression.getExpression2()).toArithmeticForm() ;
	}

}
