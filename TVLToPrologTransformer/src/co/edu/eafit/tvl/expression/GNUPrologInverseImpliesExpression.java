package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.InverseImpliesExpression;

public class GNUPrologInverseImpliesExpression implements GNUPrologExpression {
	private InverseImpliesExpression expression;
	public GNUPrologInverseImpliesExpression(InverseImpliesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform(expression.getExpression2()).toArithmeticForm() + " #==> " + GNUPrologTransformer.transform(expression.getExpression1()).toArithmeticForm();
	}

}
