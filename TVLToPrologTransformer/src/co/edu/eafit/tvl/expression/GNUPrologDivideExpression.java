package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.DivideExpression;

public class GNUPrologDivideExpression implements GNUPrologExpression {

	private DivideExpression expression;

	public GNUPrologDivideExpression(DivideExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform(expression.getExpression1()).toArithmeticForm() + " / " + GNUPrologTransformer.transform(expression.getExpression2()).toArithmeticForm();
	}

}
