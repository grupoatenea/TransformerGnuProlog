package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.ImpliesExpression;

public class GNUPrologImpliesExpression implements GNUPrologExpression {

	private ImpliesExpression expression;

	public GNUPrologImpliesExpression(ImpliesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform(expression.getExpression1()).toArithmeticForm() + " #==> " + GNUPrologExpressionTransformer.transform(expression.getExpression2()).toArithmeticForm();
	}
	
	

}
