package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.QuestExpression;

public class GNUPrologQuestExpression implements GNUPrologExpression {

	private QuestExpression expression;

	public GNUPrologQuestExpression(QuestExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		String expression1 = "( " + GNUPrologTransformer.transform(expression.getExpression1()).toArithmeticForm() + " ) #==> ( " + GNUPrologTransformer.transform(expression.getExpression2()).toArithmeticForm() + " )";
		String expression2 = "( #\\ " + GNUPrologTransformer.transform(expression.getExpression1()).toArithmeticForm() + " ) #==> ( " + GNUPrologTransformer.transform(expression.getExpression3()).toArithmeticForm() + " )";
		return expression1 + ", " + expression2;
	}
}
