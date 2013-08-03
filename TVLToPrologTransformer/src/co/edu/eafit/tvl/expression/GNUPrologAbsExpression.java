package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.AbsExpression;

public class GNUPrologAbsExpression implements GNUPrologExpression {
	
	private AbsExpression absExpresion;

	public GNUPrologAbsExpression(AbsExpression absExpression) {
		this.absExpresion = absExpression;
	}

	@Override
	public String toArithmeticForm() {
		return "abs(" + GNUPrologTransformer.transform( absExpresion.getExpression() ).toArithmeticForm() + ")" ;
	}


}
