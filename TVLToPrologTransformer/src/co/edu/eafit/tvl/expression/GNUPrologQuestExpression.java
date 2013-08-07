package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ImpliesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.NotExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ParenthesesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.QuestExpression;

public class GNUPrologQuestExpression implements GNUPrologExpression {

	private QuestExpression expression;

	public GNUPrologQuestExpression(QuestExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		Expression expression1 = new ImpliesExpression( new ParenthesesExpression( expression.getExpression1() ), new ParenthesesExpression( expression.getExpression2() ) );
		Expression expression2 = new ImpliesExpression( new ParenthesesExpression( new NotExpression(expression.getExpression1())), new ParenthesesExpression( expression.getExpression3() ) );
		String nguProglogExpression = GNUPrologExpressionTransformer.transform(expression1).toArithmeticForm();
		String nguProglogExpression2 = GNUPrologExpressionTransformer.transform(expression2).toArithmeticForm();;
		return nguProglogExpression + ", " + nguProglogExpression2;
	}
}
