package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.InverseImpliesExpression;

public class GNUPrologInverseImpliesExpression implements GNUPrologExpression {
	private InverseImpliesExpression expression;
	public GNUPrologInverseImpliesExpression(InverseImpliesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform(expression.getExpression2()).toArithmeticForm() + " #==> " + GNUPrologExpressionTransformer.transform(expression.getExpression1()).toArithmeticForm();
	}

}
