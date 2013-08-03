package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.LongIDExpression;

public class GNUPrologLongIDExpression implements GNUPrologExpression {

	private LongIDExpression expression;

	public GNUPrologLongIDExpression(LongIDExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return expression.getLongID();
	}

}
