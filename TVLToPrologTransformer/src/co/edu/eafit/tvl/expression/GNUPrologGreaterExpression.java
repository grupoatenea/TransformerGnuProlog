package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.GreaterExpression;

public class GNUPrologGreaterExpression implements GNUPrologExpression {

	private GreaterExpression expression;

	public GNUPrologGreaterExpression(GreaterExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #> " + GNUPrologExpressionTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}

}
