package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.NotExpression;

public class GNUPrologNotExpression implements GNUPrologExpression {

	private NotExpression expression;

	public GNUPrologNotExpression(NotExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return "#\\ " + GNUPrologTransformer.transform(expression.getExpression()).toArithmeticForm();
	}
	
	

}
