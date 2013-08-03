package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.GreaterExpression;

public class GNUPrologGreaterExpression implements GNUPrologExpression {

	private GreaterExpression expression;

	public GNUPrologGreaterExpression(GreaterExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #> " + GNUPrologTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}

}
