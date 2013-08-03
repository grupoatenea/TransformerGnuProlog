package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.LowerExpression;

public class GNUPrologLowerExpression implements GNUPrologExpression {

	private LowerExpression expression;

	public GNUPrologLowerExpression(LowerExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #< " + GNUPrologTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}
	
	

}
