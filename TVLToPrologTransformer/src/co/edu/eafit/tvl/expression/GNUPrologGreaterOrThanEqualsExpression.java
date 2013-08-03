package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.GEQExpression;

public class GNUPrologGreaterOrThanEqualsExpression implements GNUPrologExpression {

	private GEQExpression expression;

	public GNUPrologGreaterOrThanEqualsExpression(GEQExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #>= " + GNUPrologTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}
	
	

}
