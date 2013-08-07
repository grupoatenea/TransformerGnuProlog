package co.edu.eafit.tvl.expression;


import be.ac.info.fundp.TVLParser.SyntaxTree.MinusExpression;

public class GNUPrologMinusExpression implements GNUPrologExpression {
	
	private MinusExpression expression;

	public GNUPrologMinusExpression(MinusExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform(expression.getExpression1()).toArithmeticForm() + " - " + GNUPrologExpressionTransformer.transform(expression.getExpression2()).toArithmeticForm() ;
	}

}
