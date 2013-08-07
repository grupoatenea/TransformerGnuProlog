package co.edu.eafit.tvl.expression;


import be.ac.info.fundp.TVLParser.SyntaxTree.MinusExpression;

public class GNUPrologMinusExpression implements GNUPrologExpression {
	
	private MinusExpression expression;

	public GNUPrologMinusExpression(MinusExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform(expression.getExpression1()).toArithmeticForm() + " - " + GNUPrologTransformer.transform(expression.getExpression2()).toArithmeticForm() ;
	}

}
