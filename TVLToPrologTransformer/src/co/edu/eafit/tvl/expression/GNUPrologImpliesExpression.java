package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.ImpliesExpression;

public class GNUPrologImpliesExpression implements GNUPrologExpression {

	private ImpliesExpression expression;

	public GNUPrologImpliesExpression(ImpliesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform(expression.getExpression1()).toArithmeticForm() + " #==> " + GNUPrologTransformer.transform(expression.getExpression2()).toArithmeticForm();
	}
	
	

}
