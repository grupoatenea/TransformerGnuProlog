package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.LEQExpression;

public class GNUPrologLowerOrThanEqualsExpression implements GNUPrologExpression {

	private LEQExpression expression;

	public GNUPrologLowerOrThanEqualsExpression(LEQExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #=< " + GNUPrologTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}
	
	

}
